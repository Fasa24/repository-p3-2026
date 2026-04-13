package controllers;

import models.*;
import views.*;
import main.AppNavigator;

import javax.swing.*;
import java.util.Map;

public class RegisterController {
    private FormularyRegistry view;

    public RegisterController(FormularyRegistry view) {
        this.view = view;
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
            JOptionPane.showMessageDialog(view, "Registration successful!");
        } else { view.showErrors(errors); }
    }

    private void goBack() {
        int option = JOptionPane.showConfirmDialog(view, "Are you sure?");
        if (option == JOptionPane.YES_OPTION) {
            AppNavigator.openLogin(view);
        }
    }
}
