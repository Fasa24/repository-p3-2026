package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyPanel extends JPanel{
	
	// A singular constructor or method must not have all of the contents, make sure to divide the code into
	// smaller chunks.
	public MyPanel() {
		Font mainFont = new Font("Georgia", Font.BOLD, 20);
		setBackground(Color.GRAY);
		setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setFont(mainFont);
		textField.setBounds(100,100,300,30);
		add(textField);
		
		JPasswordField password = new JPasswordField();
		password.setFont(mainFont);
		password.setBounds(100,140,300,30);
		add(password);
		
		JButton myButton = new JButton("Log in");
		myButton.setBounds(100,180,150,30);
		myButton.setBackground(Color.GREEN);
		myButton.setForeground(Color.BLACK);
		myButton.setToolTipText("Enter the page!");
		myButton.setFont(mainFont);
		
		try {
			Image icon = ImageIO.read(getClass().getResource("C:\\Users\\Work\\eclipse-workspace\\JavaFour\\src\\img\\loginIcon.png"));
			icon = icon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			myButton.setIcon(new ImageIcon(icon));
		} catch(Exception ex) {
			System.out.println("Image not found.");
		}
		add(myButton);
		
		JLabel greeting = new JLabel("Welcome!");
		greeting.setForeground(Color.WHITE);
		greeting.setBackground(Color.BLACK);
		greeting.setFont(mainFont);
		greeting.setBounds(60,60,150,30);
		add(greeting);
		
	}
	
}
