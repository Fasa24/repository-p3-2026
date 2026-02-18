package views;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class LoginWindow extends JFrame {
	public LoginWindow() {
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setResizable(false);
		setTitle("Online Store v1");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icon = tk.getImage("src/img/icon.jpg");
		setIconImage(icon);
		
		/*
		ImageIcon cursorImage = new ImageIcon("src/img/icon.jpg");
		Cursor myCursor = tk.createCustomCursor(cursorImage.getImage(), new Point(0, 0), "Cursor");
		setCursor(myCursor);
		*/
		
		LoginView littlePanel = new LoginView();
		add(littlePanel);
		
		setVisible(true);
	}
}
