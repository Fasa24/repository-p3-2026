package controllers;

import models.User;
import repository.UserRepository;
import views.Dashboard;
import views.LoginWindow;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class DashboardController {
	private Dashboard view;
	private UserRepository repository;

	public DashboardController(Dashboard view) {
		this.view = view;
		this.repository = new UserRepository();
		init();
	}

	public void init() {
		view.setExitListener(e -> exitToLogin());
		view.btnViewUsers.addActionListener(e -> listUsers());
		view.showWindow();
	}

	private void listUsers() {
		try {
			List<User> users = repository.getUsers();
			if (users.isEmpty()) {
				JOptionPane.showMessageDialog(view, "There are no registered users yet.");
				return;
			}

			System.out.println("=== LIST OF REGISTERED USERS ===");
			for (User u : users) {
				System.out.println(u);
				System.out.println("------------------------------------");
			}
			JOptionPane.showMessageDialog(view, "List sent to the system console.");

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(view, "ERROR reading users: " + ex.getMessage());
		}
	}

	private void exitToLogin() {
		int option = JOptionPane.showConfirmDialog(
				view, "Do you want to return to login?",
				"Exit Dashboard", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			view.dispose();
			new LoginWindow();
		}
	}
}
