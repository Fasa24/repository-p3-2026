package controllers;

import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import models.User;
import repository.UserRepository;
import tablemodels.UserTableModel;
import views.Dashboard;
import views.LoginWindow;

public class DashboardController {
	private Dashboard view;
	private UserRepository repository;

	public DashboardController(Dashboard view) {
		this.view = view;
		this.repository = new UserRepository();
		init();
	}

	public void init() {
		view.exit.addActionListener(e -> exitToLogin());
		view.btnHome.addActionListener(e -> view.showView(Dashboard.HOME_VIEW));
		view.btnUsers.addActionListener(e -> loadUserTable());
		view.setVisible(true);
	}

	private void loadUserTable() {
		try {
			List<User> users = repository.getUsers();
			UserTableModel model = new UserTableModel(users);
			view.usersPanel.setTableModel(model);
			view.showView(Dashboard.USERS_VIEW);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(view, "Error loading CSV: " + ex.getMessage());
		}
	}

	private void exitToLogin() {
		int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to close the session?", "Exit", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			view.dispose();
			new LoginWindow();
		}
	}
}
