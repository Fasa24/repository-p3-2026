package models;

public class LoginModel {
    public boolean authenticate(String email, String password) {
        return email.equals("memo61@mangos.com") &&
                password.equals("67");
    }

    public String validateEmail(String email) {
        if (email.trim().isEmpty())
            return "An e-mail is required.";
        return null;
    }

    public String validatePassword(String password) {
        if (password.trim().isEmpty())
            return "A password is required.";
        return null;
    }
}
