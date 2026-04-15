package repository;

import models.User;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final String FILE_PATH = "src/assets/users.csv";

    public void save(User user) throws IOException {
        File file = new File(FILE_PATH);
        boolean needsNewline = false;

        if (file.exists() && file.length() > 0) {
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
            if (needsNewline) {
                writer.newLine();
            }
            writer.write(user.toCsv());
            writer.newLine();
        }
    }

    public List<User> getUsers() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) return users;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    users.add(User.fromCsv(line));
                }
            }
        }
        return users;
    }
}
