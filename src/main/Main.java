package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

import com.formdev.flatlaf.FlatDarkLaf;

import views.LoginWindow;
import views.MainView;
import views.FormularyRegistry;

public class Main {
	public static void main(String[] args) {
		FlatDarkLaf.setup();
		// LoginWindow littleWindow = new LoginWindow();
		FormularyRegistry formulary = new FormularyRegistry();
		// MainView principal = new MainView();
		showOnScreen(0, formulary);
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
