package views;

import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import components.RoundButton;

@SuppressWarnings("serial")
public class LoginView extends JPanel {
	LoginWindow window;
	private JButton loginBtn;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JLabel lblEmailError;
	private JLabel lblPasswordError;
	
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
        panelLeft.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLogoAndWelcome(panelLeft, gbc);
        addLoginFields(panelLeft, gbc);
        addRegisterButton(panelLeft, gbc);

        add(panelLeft, BorderLayout.WEST);
    }

    private void addLogoAndWelcome(JPanel panelLeft, GridBagConstraints gbc) {
        JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelNorth.setOpaque(false);

        ImageIcon icon = new ImageIcon("src/img/appleLogo.jpg");
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(scaledImage));

        JLabel lblWelcome = new JLabel("WELCOME!");
        lblWelcome.setFont(new Font("Roboto Mono", Font.BOLD, 26));

        panelNorth.add(lblLogo);
        panelNorth.add(lblWelcome);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelLeft.add(panelNorth, gbc);
    }

    private void addLoginFields(JPanel panelLeft, GridBagConstraints gbc) {
        // E-MAIL
        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));

        lblEmailError = new JLabel("An e-mail is required.");
        lblEmailError.setForeground(Color.RED);
        lblEmailError.setFont(new Font("Arial", Font.PLAIN, 13));
        lblEmailError.setText(" ");

        gbc.insets = new Insets(2, 2, 2, 2);
        
        // PASSWORD
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));

        lblPasswordError = new JLabel("A password is required.");
        lblPasswordError.setForeground(Color.RED);
        lblPasswordError.setFont(new Font("Arial", Font.PLAIN, 13));
        lblPasswordError.setText(" ");
 
        // CHECKBOX
        JCheckBox chkRememberEmail = new JCheckBox("Remember e-mail");
        chkRememberEmail.setEnabled(true);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelLeft.add(lblEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelLeft.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelLeft.add(lblEmailError, gbc);

        panelLeft.add(Box.createVerticalStrut(15));

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelLeft.add(lblPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelLeft.add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panelLeft.add(lblPasswordError, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelLeft.add(chkRememberEmail, gbc);
    }
    
    private void addRegisterButton(JPanel panelLeft, GridBagConstraints gbc) {
        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSouth.setOpaque(false);

        JLabel lblRegister = new JLabel("No account? Create one.");
        lblRegister.setFont(new Font("Arial", Font.BOLD, 11));

        loginBtn = new JButton("Login");
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(new Color(0, 128, 0));
        loginBtn.setFocusPainted(false);
        loginBtn.setPreferredSize(new Dimension(100, 35));
        
        loginBtn.addActionListener(e-> handleRegistration());

        panelSouth.add(lblRegister);
        panelSouth.add(loginBtn);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelLeft.add(panelSouth, gbc);
    }
    
    private boolean verification() {
    	int cont = 0;
		// EMAIL
		if(txtEmail.getText().trim().isEmpty()) {
			lblEmailError.setText("An e-mail is required.");
		}
		else {
			lblEmailError.setText(" ");
			System.out.println("text");
			cont++;
		}
		
		// PASSWORD
		if(String.valueOf(txtPassword.getPassword()).trim().isEmpty()) {
			lblPasswordError.setText("A password is required.");
		}
		else {
			System.out.println("pass");
			lblPasswordError.setText(" ");
			cont++;
		}
		
    	if(cont == 2) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    private void handleRegistration() {
    	if(verification()) {
    		JOptionPane.showMessageDialog(null, "Login successful!", "eManza", JOptionPane.INFORMATION_MESSAGE);
    		
    		new FormularyRegistry();
    		window.dispose();
    	}
    }
    
    private void createRightPanel() {
        JPanel panelRight = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                try {
                    Image windowBackground = ImageIO.read(new File("src/img/fruitPatternBg.jpg"));
                    g2.drawImage(windowBackground, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException ex) {
                    System.out.println("File not found.");
                }
            }
        };
        panelRight.setOpaque(false);
        add(panelRight, BorderLayout.CENTER);
    }
}
