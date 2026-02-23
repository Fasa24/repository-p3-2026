package views;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
	public LoginWindow() {
		setTitle("ONLINE STORE");
		setSize(450, 330);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon("src/img/appleLogo.jpg");
		setIconImage(icon.getImage());
		
		add(new LoginView());
		setVisible(true);
	}
}
