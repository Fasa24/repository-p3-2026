package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatDarkLaf;

import views.LoginWindow;

public class Main {
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		LoginWindow loginWindow = new LoginWindow();
		showOnScreen(0, loginWindow);
	}

	public static void showOnScreen(int screen, JFrame frame) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();

		if (screen > -1 && screen < gd.length) {
			int width = gd[screen].getDefaultConfiguration().getBounds().width;
			int height = gd[screen].getDefaultConfiguration().getBounds().height;

			frame.setLocation (
					((width / 2) - (frame.getSize().width / 2)) +
							gd[screen].getDefaultConfiguration().getBounds().x,
					((height / 2) - (frame.getSize().height / 2)) +
							gd[screen].getDefaultConfiguration().getBounds().y
			);

		} else { throw new RuntimeException("Display not found."); }
	}
}
