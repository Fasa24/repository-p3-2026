package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import views.LoginWindow;

public class Main {

	public static void main(String[] args) {
		LoginWindow littleWindow = new LoginWindow();
		showOnScreen(0, littleWindow);
	}
	
	public static void showOnScreen(int screen, JFrame frame) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();
		int width = 0, height = 0;
		
		if (screen > -1 && screen < gd.length) {
			width = gd[screen].getDefaultConfiguration().getBounds().width;
			height = gd[screen].getDefaultConfiguration().getBounds().height;
			frame.setLocation(
		            ((width / 2) - (frame.getSize().width / 2)) + gd[screen].getDefaultConfiguration().getBounds().x, 
		            ((height / 2) - (frame.getSize().height / 2)) + gd[screen].getDefaultConfiguration().getBounds().y
		        );
		} else {
			throw new RuntimeException("Display not found.");
		}
	}
}
