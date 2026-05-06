package repository;

import models.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UserRepository {
    //private final String FILE_PATH = "src/assets/files/users.json";
    private final String FILE_PATH = "."
            + File.separator
            + "data"
            + File.separator
            + "users.json";

    private final ObjectMapper mapper =
            new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public void save(User user) throws IOException {
        List<User> users = getUsers();
        users.add(user);
        updateAll(users);
    }

    public List<User> getUsers() throws IOException {
        File file = new File(FILE_PATH);

        file.getParentFile().mkdirs();

        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        return mapper.readValue(file, new TypeReference<List<User>>() {});
    }

    public void updateAll(List<User> users) throws IOException {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdir();

        mapper.writeValue(file, users);
    }

    public void update(int index, User updatedUser) throws IOException {
        List<User> users = getUsers();
        users.set(index, updatedUser);
        updateAll(users);
    }
}