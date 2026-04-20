package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import components.*;
import utils.AppFont;

public class LoginView extends JPanel {
	private LoginWindow window;

	private RoundedButton loginBtn;
	private RoundedField txtEmail;
	private RoundedPasswordField txtPassword;
	private JLabel lblEmailError;
	private JLabel lblPasswordError;
	private JLabel lblRegister;

	private final Border defaultBorder = new RoundedBorder(10, Color.GRAY, 1f);
	private final Border focusBorder = new RoundedBorder(10, new Color(65, 116, 255), 3f);
	private final Border errorBorder = new RoundedBorder(10, Color.RED, 2f);

	private final ImageIcon warningIcon = ScaleIcon.scale("src/img/warningIcon.png", 16, 16);
	private Image background;

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

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(AppFont.normal());

		txtEmail = new RoundedField(10);
		txtEmail.setPreferredSize(new Dimension(200, 30));
		txtEmail.setFont(AppFont.normal());
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setCaretColor(Color.BLACK);
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setOpaque(false);
		txtEmail.setBorder(defaultBorder);

		lblEmailError = new JLabel(" ");
		lblEmailError.setForeground(Color.RED);
		lblEmailError.setFont(AppFont.small());

		applyFocusBorder(txtEmail, lblEmailError);

		gbc.gridy = 1;
		panelLeft.add(lblEmail, gbc);

		gbc.gridy = 2;
		panelLeft.add(txtEmail, gbc);

		gbc.gridy = 3;
		panelLeft.add(lblEmailError, gbc);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(AppFont.normal());

		txtPassword = new RoundedPasswordField(10);
		txtPassword.setPreferredSize(new Dimension(200, 30));
		txtPassword.setFont(AppFont.normal());
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setCaretColor(Color.BLACK);
		txtPassword.setForeground(Color.BLACK);
		txtPassword.setOpaque(false);
		txtPassword.setBorder(defaultBorder);

		lblPasswordError = new JLabel(" ");
		lblPasswordError.setForeground(Color.RED);
		lblPasswordError.setFont(AppFont.small());

		applyFocusBorder(txtPassword, lblPasswordError);

		gbc.gridy = 4;
		panelLeft.add(lblPassword, gbc);

		gbc.gridy = 5;
		panelLeft.add(txtPassword, gbc);

		gbc.gridy = 6;
		panelLeft.add(lblPasswordError, gbc);

		lblRegister = new JLabel("<html>No account? Create one.</html>");

		lblRegister.setFont(AppFont.small());
		lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblRegister.setOpaque(true);

		gbc.gridy = 7;
		panelLeft.add(lblRegister, gbc);

		loginBtn = new RoundedButton("Login", 10);
		loginBtn.setFont(AppFont.normal());
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground(new Color(0, 128, 0));
		loginBtn.setPreferredSize(new Dimension(120, 35));
		loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		setHoverEffect();

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
					background = ImageIO.read(new File("src/img/fruitPatternBg.jpg"));
					g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
				} catch (IOException e) {
					System.out.println("Image not found.");
				}
			}
		};

		panelRight.setOpaque(false);
		add(panelRight, BorderLayout.CENTER);
	}

	public void setHoverEffect(){
		Color loginNormal = new Color(0, 128, 0);
		Color loginHover = new Color(0, 180, 0);
		Color labelNormal = new Color(255, 255, 255);
		Color labelHover = new Color(45, 106, 239);

		loginBtn.setBackground(loginNormal);
		lblRegister.setForeground(labelNormal);

		loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				loginBtn.setBackground(loginHover);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				loginBtn.setBackground(loginNormal);
			}
		});

		lblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblRegister.setText("<html><u>No account? Create one.</u></html>");
				lblRegister.setForeground(labelHover);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				lblRegister.setText("<html>No account? Create one.</html>");
				lblRegister.setForeground(labelNormal);
			}
		});
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
		txtEmail.setBorder(msg == null ? defaultBorder : errorBorder);
		lblEmailError.setText(msg == null ? " " : msg);
		lblEmailError.setIcon(msg == null ? null : warningIcon);
	}

	public void setPasswordError(String msg) {
		txtPassword.setBorder(msg == null ? defaultBorder : errorBorder);
		lblPasswordError.setText(msg == null ? " " : msg);
		lblPasswordError.setIcon(msg == null ? null : warningIcon);
	}

	public void clearErrors() {
		setEmailError(null);
		setPasswordError(null);
	}

	private void applyFocusBorder(JComponent field, JLabel errorLabel) {
		field.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (errorLabel.getIcon() == null) {
					field.setBorder(focusBorder);
				}
			}
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (errorLabel.getIcon() == null) {
					field.setBorder(defaultBorder);
				}
			}
		});
	}
}
