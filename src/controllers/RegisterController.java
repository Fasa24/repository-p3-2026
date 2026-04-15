package controllers;

import models.*;
import views.*;
import main.AppNavigator;
import repository.UserRepository;

import javax.swing.*;
import java.io.IOException;
import java.util.Map;

public class RegisterController {
    private FormularyRegistry view;
    private UserRepository repository;

    public RegisterController(FormularyRegistry view) {
        this.view = view;
        this.repository = new UserRepository();
        init();
    }

    private void init() {
        view.setRegisterListener(e -> register());
        view.setReturnListener(e -> goBack());
    }

    private void register() {
        User user = view.getUserFromForm();
        Map<String, String> errors = UserValidator.validate(user);

        view.clearErrors();

        if (errors.isEmpty()) {
            try {
                repository.save(user);
                JOptionPane.showMessageDialog(view, "User successfully saved in CSV!");
                AppNavigator.openLogin(view);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Error saving: " + ex.getMessage(),
                        "File Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            view.showErrors(errors);
        }
    }

    private void goBack() {
        int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to return?");
        if (option == JOptionPane.YES_OPTION) {
            AppNavigator.openLogin(view);
        }
    }
}
