package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import utils.AppFont;

public class LoginView extends JPanel {
	private LoginWindow window;

	private JButton loginBtn;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JLabel lblEmailError;
	private JLabel lblPasswordError;
	private JLabel lblRegister;

	public LoginView(LoginWindow window) {
		this.window = window;

		setLayout(new BorderLayout(10, 10));
		setOpaque(false);

		createLeftPanel();
		createRightPanel();
	}

	private void createLeftPanel() {
		JPanel panelLeft = new JPanel(new GridBagLayout());
		panelLeft.setOpaque(false);
		panelLeft.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		panelNorth.setOpaque(false);

		ImageIcon icon = new ImageIcon("src/img/appleLogo.jpg");
		Image scaled = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(scaled));

		JLabel lblWelcome = new JLabel("WELCOME!");
		lblWelcome.setFont(AppFont.title().deriveFont(26f));

		panelNorth.add(lblLogo);
		panelNorth.add(lblWelcome);

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelLeft.add(panelNorth, gbc);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(AppFont.normal());

		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(200, 30));
		txtEmail.setFont(AppFont.normal());

		lblEmailError = new JLabel(" ");
		lblEmailError.setForeground(Color.RED);
		lblEmailError.setFont(AppFont.small());

		gbc.gridy = 1;
		panelLeft.add(lblEmail, gbc);

		gbc.gridy = 2;
		panelLeft.add(txtEmail, gbc);

		gbc.gridy = 3;
		panelLeft.add(lblEmailError, gbc);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(AppFont.normal());

		txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(200, 30));
		txtPassword.setFont(AppFont.normal());

		lblPasswordError = new JLabel(" ");
		lblPasswordError.setForeground(Color.RED);
		lblPasswordError.setFont(AppFont.small());

		gbc.gridy = 4;
		panelLeft.add(lblPassword, gbc);

		gbc.gridy = 5;
		panelLeft.add(txtPassword, gbc);

		gbc.gridy = 6;
		panelLeft.add(lblPasswordError, gbc);

		lblRegister = new JLabel("No account? Create one.");
		lblRegister.setFont(AppFont.small());
		lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

		gbc.gridy = 7;
		panelLeft.add(lblRegister, gbc);

		loginBtn = new JButton("Login");
		loginBtn.setFont(AppFont.normal());
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground(new Color(0, 128, 0));
		loginBtn.setFocusPainted(false);
		loginBtn.setPreferredSize(new Dimension(120, 35));

		gbc.gridy = 8;
		panelLeft.add(loginBtn, gbc);

		add(panelLeft, BorderLayout.WEST);
	}

	private void createRightPanel() {
		JPanel panelRight = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					Image img = ImageIO.read(new File("src/img/fruitPatternBg.jpg"));
					g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
				} catch (IOException e) {
					System.out.println("Image not found.");
				}
			}
		};

		panelRight.setOpaque(false);
		add(panelRight, BorderLayout.CENTER);
	}

	public void setLoginListener(java.awt.event.ActionListener l) {
		loginBtn.addActionListener(l);
	}

	public void setRegisterNavigation(java.awt.event.MouseListener l) {
		lblRegister.addMouseListener(l);
	}

	public void addInputListeners(Runnable r) {
		txtEmail.getDocument().addDocumentListener(new SimpleDocumentListener(r));
		txtPassword.getDocument().addDocumentListener(new SimpleDocumentListener(r));
	}

	public String getEmail() { return txtEmail.getText(); }
	public String getPassword() { return new String(txtPassword.getPassword()); }
	public LoginWindow getWindow() { return window; }

	public void setEmailError(String msg) {
		lblEmailError.setText(msg == null ? " " : msg);
	}

	public void setPasswordError(String msg) {
		lblPasswordError.setText(msg == null ? " " : msg);
	}

	public void clearErrors() {
		setEmailError(null);
		setPasswordError(null);
	}
}
