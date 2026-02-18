package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class BorderPanel extends JPanel{
	Font myFont, greetingFont;
	public BorderPanel() {
		setLayout(new BorderLayout());
		Border emptyBorder = BorderFactory.createEmptyBorder(40,10,20,10);
		setBorder(emptyBorder);
		
		myFont = new Font("Georgia", Font.PLAIN, 14);
		greetingFont = new Font("Georgia", Font.ITALIC, 23);
		setLayout(null);
		
		initializeComponents();
	}
	
	private void initializeComponents() {
		createButtons();
		createLogo();
		createFormulary();
	}
	
	private void createButtons() {
		JButton button = new JButton("Log in!");
		button.setBounds(250, 320, 120, 30);
		button.setToolTipText("Click here to enter.");
		button.setFont(myFont);
		button.setBackground(Color.GREEN);
		add(button);
	}
	
	private void createLogo() {
		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(145, 50, 100, 100);
		lblLogo.setIcon(loadIcon("/img/icon.jpg", 100, 100));
		add(lblLogo);
	}
	
	private void createFormulary() {
		JLabel lblGreeting = new JLabel("Welcome!");
		lblGreeting.setFont(greetingFont);
		lblGreeting.setBounds(10, 0, 200, 40);
		add(lblGreeting);
		
		int lblX = 10, y = 170, txtX = 150;
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(myFont);
		lblEmail.setBounds(lblX, y, 200, 40);
		add(lblEmail);
		
		JTextField txtEmail = new JTextField();
		txtEmail.setFont(myFont);
		txtEmail.setBounds(txtX, y, 200, 40);
		add(txtEmail);
		
		JLabel lblEmailRequired = new JLabel("The email is required.");
		lblEmailRequired.setBounds(txtX, y+35, 200, 30);
		lblEmailRequired.setFont(new Font("Arial", Font.BOLD, 10));
		lblEmailRequired.setForeground(Color.RED);
		add(lblEmailRequired);
		
		y += 70;
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(myFont);
		lblPassword.setBounds(lblX, y, 200, 40);
		add(lblPassword);
		
		JPasswordField password = new JPasswordField();
		password.setFont(myFont);
		password.setBounds(txtX, y, 200, 40);
		add(password);
	}
	
	private ImageIcon loadIcon(String route, int w, int h) {
		try {
			Image icon = ImageIO.read(getClass().getResource(route));
			icon = icon.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return new ImageIcon(icon);
		} catch (Exception ex) {
			System.out.println("Icon image not found.");
		}
		return null;
	}
	
	public void crearPanelCentro() {
		JPanel panelCentro = new JPanel(new BorderLayout());
		panelCentro.setBackground(Color.RED);
		
		JPanel panelCentroSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelCentro.add(panelCentroSur, BorderLayout.SOUTH);
		panelCentroSur.setBackground(Color.GREEN);
		
		JButton btnInicio = new JButton("iniciar sesion");
		panelCentroSur.add(btnInicio);
		
		JButton btnCancelar = new JButton("Cancel");
		panelCentroSur.add(btnCancelar);
		
		add(panelCentro, BorderLayout.CENTER);
	}
}
