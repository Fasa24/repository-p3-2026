package repository;

import models.User;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final String FILE_PATH = "src/assets/users.csv";
    private final String HEADER = "Name,E-mail,Password,Address,P.C.,Gender,ToS";

    public void save(User user) throws IOException {
        File file = new File(FILE_PATH);
        boolean isNewFile = !file.exists() || file.length() == 0;
        boolean needsNewline = false;

        if (!isNewFile) {
            try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                raf.seek(file.length() - 1);
                byte lastByte = raf.readByte();
                if (lastByte != '\n' && lastByte != '\r') {
                    needsNewline = true;
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FILE_PATH, true), StandardCharsets.UTF_8))) {
            if (isNewFile) {
                writer.write(HEADER);
                writer.newLine();
            } else if (needsNewline) {
                writer.newLine();
            }

            writer.write(user.toCsv());
            writer.newLine();
        }
    }

    public List<User> getUsers() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);

        if(!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(FILE_PATH), StandardCharsets.UTF_8))) {

            String line = reader.readLine();

            if (line != null && !line.equals(HEADER)) {
                users.add(User.fromCsv(line));
            }

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    users.add(User.fromCsv(line));
                }
            }
        }
        return users;
    }

    public void updateAll(List<User> users) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(FILE_PATH), StandardCharsets.UTF_8))) {

            for (User user : users) {
                writer.write(user.toCsv());
                writer.newLine();
            }
        }
    }

    public void update(int index, User updatedUser) throws IOException {
        List<User> users = getUsers();
        users.set(index, updatedUser);
        updateAll(users);
    }
}
