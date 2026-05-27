package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import models.LoginModel;
import models.User;
import repository.LoginRepository;
import utils.Session;
import views.Dashboard;
import views.LoginView;
import main.AppNavigator;

public class LoginController {

	private LoginView view;
	private LoginModel model;
	private LoginRepository repository;

	public LoginController(LoginView view, LoginModel model) {
		this.view = view;
		this.model = model;
		this.repository = new LoginRepository();
		init();
	}

	private void init() {
		view.setLoginListener(e -> handleLogin());

		view.setRegisterNavigation(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleRegistration();
			}
		});

		view.addInputListeners(() -> validateLive());

		KeyAdapter enterListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					handleLogin();
				}
			}
		};

		view.getEmailField().addKeyListener(enterListener);
		view.getPasswordField().addKeyListener(enterListener);
	}

	private boolean validateCredentials(String email, String password) {
		view.clearErrors();

		boolean valid = true;

		String emailError = model.validateEmail(email);
		String passwordError = model.validatePassword(password);

		if (email.trim().isEmpty()) {
			view.setEmailError("Email is required");
			valid = false;
		} else if (emailError != null) {
			view.setEmailError(emailError);
			valid = false;
		}

		if (password.trim().isEmpty()) {
			view.setPasswordError("Password is required");
			valid = false;
		} else if (passwordError != null) {
			view.setPasswordError(passwordError);
			valid = false;
		}

		return valid;
	}

	private void handleLogin() {
		String email = view.getEmail();
		String password = view.getPassword();

		if (!validateCredentials(email, password)) {
			return;
		}

		User user = repository.login(email, password);

		if (user == null) {
			view.setPasswordError("Incorrect credentials");
			return;
		}

		Session.login(user);

		new DashboardController(new Dashboard());
		view.getWindow().dispose();
	}

	private void handleRegistration() {
		AppNavigator.openRegister(view.getWindow());
	}

	private void validateLive() {
		view.setEmailError(model.validateEmail(view.getEmail()));
		view.setPasswordError(model.validatePassword(view.getPassword()));
	}
}
