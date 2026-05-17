package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.DatabaseConnection;
import models.User;
import utils.PasswordUtils;

public class LoginRepository {

    public User login(String email, String passhphrase) {

		String sql = "SELECT user_id, email, passphrase, user_role, username FROM Users " +
                "WHERE email = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            stmt.setString(1, email);
            //stmt.setString(2, passhphrase);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                String hashedPassword = rs.getString("passphrase");
                System.out.println(hashedPassword);

                if(!PasswordUtils.checkPassword(passhphrase, hashedPassword)) { return null; }

                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("username"));
                user.setRole(rs.getString("user_role"));

                return user;
            }

        }catch(SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}