package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import components.RoundButton;
import utils.AppFont;

public class RegistryFormulary extends JFrame{
	public RegistryFormulary() {
		setSize(300, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Sign up");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/appleLogo.jpg");
		setIconImage(icono);
		
		inicializarComponentes();
		
		setVisible(true);		
	}
	
	public void inicializarComponentes() {
		JLabel lblTitulo = new JLabel("Sign up");
		lblTitulo.setFont(AppFont.title());
		add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panelComponentes.setOpaque(false);
		
		JLabel lblName = new JLabel("Name");
		lblName.setAlignmentX(CENTER_ALIGNMENT);
		panelComponentes.add(lblName);
		JTextField txtName = new JTextField();
		panelComponentes.add(txtName);
		panelComponentes.add(new JLabel(" "));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setAlignmentX(CENTER_ALIGNMENT);
		panelComponentes.add(lblEmail);
		JTextField txtEmail = new JTextField();
		panelComponentes.add(txtEmail);
		panelComponentes.add(new JLabel(" "));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setAlignmentX(CENTER_ALIGNMENT);
		panelComponentes.add(lblPassword);
		JPasswordField txtPassword = new JPasswordField();
		panelComponentes.add(txtPassword);
		panelComponentes.add(new JLabel(" "));
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setAlignmentX(CENTER_ALIGNMENT);
		panelComponentes.add(lblAddress);
		JTextArea txtAddress = new JTextArea(5, 20);
		JScrollPane scrollArea = new JScrollPane(txtAddress);
		scrollArea.setHorizontalScrollBar(null);
		panelComponentes.add(scrollArea);
		panelComponentes.add(new JLabel(" "));
		
		JLabel lblCp = new JLabel("Postal Code");
		lblCp.setAlignmentX(CENTER_ALIGNMENT);
		panelComponentes.add(lblCp);
		JTextField txtCp = new JTextField(20);
		panelComponentes.add(txtCp);
		panelComponentes.add(new JLabel(" "));
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setAlignmentX(CENTER_ALIGNMENT);
		panelComponentes.add(lblGender);
		String gender[] = {"Male", "Female", "Non-binary", "Prefer not to say"};
		JComboBox<String> comboGender = new JComboBox<String>(gender);
		comboGender.setSelectedIndex(0);
		panelComponentes.add(comboGender);
		
		RoundButton loginBtn = new RoundButton("Create Account");
		loginBtn.setForeground(Color.BLACK);
		loginBtn.setBackground(Color.GREEN);
		loginBtn.setHorizontalAlignment(SwingConstants.CENTER);
		add(loginBtn, BorderLayout.SOUTH);
		
		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		add(scroll);
	}
}
