package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import components.RoundButton;
import utils.AppFont;

public class RegistryFormulary extends JFrame{
	public RegistryFormulary() {
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Registro");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/appleLogo.jpg");
		setIconImage(icono);
		
		inicializarComponentes();
		
		setVisible(true);		
	}
	
	public void inicializarComponentes() {
		
		JLabel lblTitulo = new JLabel("Registro");
		lblTitulo.setFont(AppFont.title());
		add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		JLabel lblName = new JLabel("Nombre");
		panelComponentes.add(lblName);
		JTextField txtName = new JTextField();
		panelComponentes.add(txtName);
		
		JLabel lblEmail = new JLabel("Email");
		panelComponentes.add(lblEmail);
		JTextField txtEmail = new JTextField();
		panelComponentes.add(txtEmail);
		
		JLabel lblPassword = new JLabel("Password");
		panelComponentes.add(lblPassword);
		JTextField txtPassword = new JTextField(20);
		panelComponentes.add(txtPassword);
		
		JLabel lblAddress = new JLabel("Address");
		panelComponentes.add(lblAddress);
		JTextField txtAddress = new JTextField(20);
		panelComponentes.add(txtAddress);
		
		RoundButton loginBtn = new RoundButton("Registrar");
		loginBtn.setForeground(Color.BLACK);
		loginBtn.setBackground(Color.GREEN);
		loginBtn.setHorizontalAlignment(SwingConstants.CENTER);
		add(loginBtn, BorderLayout.SOUTH);
		
		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
	}
}
