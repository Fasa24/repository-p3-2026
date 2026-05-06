package controllers;

import java.awt.*;
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
import javax.swing.JFrame;
import utils.Config;

public class DashboardController {
	private Dashboard view;
	private UserRepository repository;
	private UserController userController;

	public DashboardController(Dashboard view) {
		this.view = view;
		this.repository = new UserRepository();

		//loadWindowPreferences();

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

	/*
	private void saveWindowPreferences() {
		Dimension size = view.getSize();
		Point point = view.getLocation();

		Config.set("registration.window.width",
				String.valueOf(size.width));

		Config.set("registration.window.height",
				String.valueOf(size.height));

		Config.set("registration.window.x",
				String.valueOf(point.x));

		Config.set("registration.window.y",
				String.valueOf(point.y));

	}

	 */

	/*
	private void loadWindowPreferences()
	{
		int width = Integer.parseInt(
				Config.get("registration.window.width"
						, "500"));

		int height = Integer.parseInt(
				Config.get("registration.window.height"
						, "500"));

		String xValue = Config.get("registration.window.x"
				, "");

		String yValue = Config.get("registration.window.y"
				, "");

		if(!xValue.isBlank() && !yValue.isBlank()) {
			view.setWindowLocation(Integer.parseInt(xValue), Integer.parseInt(yValue));
		}else {
			view.setLocationRelativeTo(null);
		}

		view.setWindowSize(width, height);
	}

	 */

}
