package utils;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import config.DatabaseConnection;

public class MigratePasswords {
    public static void main(String[] args) {
        String select = "SELECT user_id, passphrase FROM Users";
        String update = "UPDATE Users SET passphrase = ? WHERE user_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement selectStmt = conn.prepareStatement(select);
                PreparedStatement updateStmt = conn.prepareStatement(update);
                ResultSet rs = selectStmt.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String pass = rs.getString("passphrase");

                if (pass != null && !pass.startsWith("$2")) {
                    String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
                    updateStmt.setString(1, hashed);
                    updateStmt.setInt(2, id);
                    updateStmt.executeUpdate();
                    System.out.println("Migrate user_id " + id);
                }
            }
            System.out.println("Migrate complete.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
