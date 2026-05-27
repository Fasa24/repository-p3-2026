package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.DatabaseConnection;
import models.User;
import utils.PasswordUtils;

public class LoginRepository {

    public User login(String email, String passhphrase) {

        String sql = "SELECT user_id, email, passphrase, user_role, username FROM Users WHERE email = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                String hashedPassword = rs.getString("passphrase");
                boolean passwordMatches = false;

                if (passhphrase.equals(hashedPassword)) {
                    passwordMatches = true;
                } else {
                    try {
                        if (PasswordUtils.checkPassword(passhphrase, hashedPassword)) {
                            passwordMatches = true;
                        }
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Note: The password format in the DB does not require BCrypt for: " + email);
                    }
                }

                if (!passwordMatches) { 
                    return null; 
                }

                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("username"));
                user.setRole(rs.getString("user_role"));

                return user;
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
