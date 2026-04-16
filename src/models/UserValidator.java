package models;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {
    public static Map<String, String> validate(User user) {
        Map<String, String> errors = new HashMap<>();

        if (user.getName().trim().isEmpty())
            errors.put("name", "Name is required.");

        if (user.getEmail().trim().isEmpty())
            errors.put("email", "E-mail is required.");

        if (user.getPassword().trim().isEmpty())
            errors.put("password", "Password is required.");

        if (user.getAddress().trim().isEmpty())
            errors.put("address", "Address is required.");

        if (user.getPostalCode().trim().isEmpty())
            errors.put("postalCode", "Postal code is required.");

        if (!user.isTermsAccepted())
            errors.put("terms", "You must accept terms.");

        if (user.getGender().equals("Select"))
            errors.put("gender", "Gender is required.");

        return errors;
    }
}
