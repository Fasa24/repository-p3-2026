package controllers;

import models.LoginModel;
import views.*;
import main.AppNavigator;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginController {
	private LoginView view;
	private LoginModel model;

	public LoginController(LoginView view, LoginModel model) {
		this.view = view;
		this.model = model;
		init();
	}

	private void init() {
		view.setLoginListener(e -> login());

		view.setRegisterNavigation(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { goRegister(); }
		});
		view.addInputListeners(() -> validateLive());
	}

	private void login() {
		String email = view.getEmail();
		String password = view.getPassword();

		view.clearErrors();

		String emailError = model.validateEmail(email);
		String passwordError = model.validatePassword(password);

		if (emailError != null) view.setEmailError(emailError);
		if (passwordError != null) view.setPasswordError(passwordError);

		if (emailError == null && passwordError == null) {
			if (model.authenticate(email, password)) {
				JOptionPane.showMessageDialog(view, "Login successful!");
				AppNavigator.openDashboard(view.getWindow());
			} else {
				view.setEmailError("Invalid credentials!");
				view.setPasswordError("Invalid credentials!");
			}
		}
	}

	private void validateLive() {
		view.setEmailError(model.validateEmail(view.getEmail()));
		view.setPasswordError(model.validatePassword(view.getPassword()));
	}

	private void goRegister() { AppNavigator.openRegister(view.getWindow()); }
}
