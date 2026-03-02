package views;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class LoginWindow extends JFrame {
	public LoginWindow() {
		setTitle("eManza - Login");
		setSize(660, 390);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon("src/img/appleLogo.jpg");
		setIconImage(icon.getImage());
		
		//add(new LoginView());
		add(new FormularyRegistry());
		//add(new MainView());
		setVisible(true);
	}
}
