package controllers;

import views.Dashboard;
import views.LoginWindow;

import javax.swing.*;

public class DashboardController {
	private Dashboard view;
	
	public DashboardController(Dashboard view) {
		this.view = view;
		init();
	}
	
	public void init() {
		view.setExitListener(e -> exitToLogin());
		view.showWindow();
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
