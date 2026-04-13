package views;

import javax.swing.*;
import java.awt.*;

import controllers.LoginController;
import models.LoginModel;
import utils.AppFont;

public class LoginWindow extends JFrame {
	public LoginWindow() {
		setTitle("eManza - Login");

		setPreferredSize(new Dimension(720, 420));
		setMinimumSize(new Dimension(660, 390));

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		ImageIcon icon = new ImageIcon("src/img/appleLogo.jpg");
		setIconImage(icon.getImage());

		LoginView view = new LoginView(this);
		LoginModel model = new LoginModel();
		new LoginController(view, model);

		add(view);

		pack();
		setVisible(true);
	}
}
