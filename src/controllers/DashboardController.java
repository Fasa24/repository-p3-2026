package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private UserController userController;

	public DashboardController(Dashboard view) {
		this.view = view;
		this.repository = new UserRepository();
		init();
	}

	public void init() {
		view.exit.addActionListener(e -> exitToLogin());
		view.btnHome.addActionListener(e -> {
			view.showView(Dashboard.HOME_VIEW);
			updateMenuState(Dashboard.HOME_VIEW);
		});

		view.btnUsers.addActionListener(e -> {
			showUsers();
			updateMenuState(Dashboard.USERS_VIEW);
		});

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

	public void registerListeners( ) {

		view.exit.addActionListener(e -> handleClose());

		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});

		view.btnUsers.addActionListener(e -> {
			showUsers();
		});

		view.btnHome.addActionListener(e -> {
			view.showView(Dashboard.HOME_VIEW);
			updateMenuState(Dashboard.HOME_VIEW);
		});

	}

	private void showUsers() {
		if(userController == null) {
			userController = new UserController(view.usersPanel);
		}

		userController.loadUsers();

		view.showView(Dashboard.USERS_VIEW);
		updateMenuState(Dashboard.USERS_VIEW);

	}

	private void handleClose() {
		view.dispose();
	}
	private void updateMenuState(String viewName) {
		view.btnUsers.setEnabled(!viewName.equals(Dashboard.USERS_VIEW));
		view.btnHome.setEnabled(!viewName.equals(Dashboard.HOME_VIEW));
	}
}
