package repository;

import config.DatabaseConnection;
import models.User;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UserRepository {

    public void save(User user) throws IOException {
        List<User> users = getUsers();
        users.add(user);
        //updateAll(users);
    }

    public List<User> getUsers() throws IOException {
        List<User> users = new ArrayList<User>();

        try(
                Connection connection = DatabaseConnection.getConnection();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Users");
        ) {

            while(rs.next()) {

                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("passphrase"),
                        rs.getString("address"),
                        rs.getString("postal_code"),
                        rs.getString("gender"),
                        rs.getString("user_role"),
                        true
                );
                users.add(user);
            }

        }catch(SQLException ex ) {
            ex.printStackTrace();
        }
        return users;
    }

    public boolean delete(int id){
        String sql = "DELETE FROM Users WHERE user_id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, id);
            int affectedRows = pst.executeUpdate();
            if(affectedRows > 0) {
                System.out.println("Se eliminó");
                return true;
            }

        }catch(SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean update(int index, User updatedUser) throws IOException {
        //List<User> users = getUsers();
        //users.set(index, updatedUser);

        String sql = "UPDATE Users SET username = ?, email = ?, passphrase = ?,"
                + " address = ?, postal_code = ?, gender = ?, user_role = ? "
                + "WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setString(1, updatedUser.getName());
            pst.setString(2, updatedUser.getEmail());
            pst.setString(3, updatedUser.getPassword());
            pst.setString(4, updatedUser.getAddress());
            pst.setString(5, updatedUser.getPostalCode());
            pst.setString(6, updatedUser.getGender());
            pst.setString(7, updatedUser.getRole());
            pst.setInt(8, updatedUser.getId());

            int affectedRows = pst.executeUpdate();

            if(affectedRows > 0) {
                return true;
            }


        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}