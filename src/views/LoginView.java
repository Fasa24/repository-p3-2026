package views;

import javax.swing.*;
import java.awt.*;
import components.RoundButton;

public class LoginView extends JPanel {
	public LoginView() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		createNorth();
		createCenter();
		createSouth();
	}
	
	private void createNorth() {
		JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		
		ImageIcon icon = new ImageIcon("src/img/icon.jpg");
		Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		
		JLabel lblLogo = new JLabel(scaledIcon);
		
		JLabel lblWelcome = new JLabel("WELCOME!");
		lblWelcome.setFont(new Font("Georgia", Font.BOLD, 26));
		
		panelNorth.add(lblLogo);
		panelNorth.add(lblWelcome);
		
		add(panelNorth, BorderLayout.NORTH);
	}
	
	private void createCenter() {
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Georgia", Font.BOLD, 14));
		JTextField txtEmail = new JTextField();
		JLabel lblEmailError = new JLabel("Please enter your E-mail.");
		lblEmailError.setForeground(Color.RED);
		lblEmailError.setFont(new Font("Georgia", Font.PLAIN, 13));
		
		panelCenter.add(lblEmail);
		panelCenter.add(txtEmail);
		panelCenter.add(lblEmailError);
		
		panelCenter.add(Box.createVerticalStrut(15));
		
		JLabel lblPassword = new JLabel("Passphrase:");
		lblPassword.setFont(new Font("Georgia", Font.BOLD, 14));
		JPasswordField txtPassword = new JPasswordField();
		JLabel lblPasswordError = new JLabel("Enter your passphrase");
		lblPasswordError.setForeground(Color.RED);
		lblPasswordError.setFont(new Font("Georgia", Font.PLAIN, 13));
		
		panelCenter.add(lblPassword);
		panelCenter.add(txtPassword);
		panelCenter.add(lblPasswordError);
		
		add(panelCenter, BorderLayout.CENTER);
	}
	
	private void createSouth() {
		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		RoundButton loginBtn = new RoundButton("Login");
		loginBtn.setForeground(Color.BLACK);
		loginBtn.setBackground(Color.GREEN);
		
		panelSouth.add(loginBtn);
		
		add(panelSouth, BorderLayout.SOUTH);
	}
}
