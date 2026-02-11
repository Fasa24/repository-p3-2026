package practica1;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class MiPanel extends JPanel{
	
	public MiPanel() {
		Font fuente = new Font("Georgia", Font.BOLD, 20);
		setBackground(Color.GRAY); //rgb
		setLayout(null);
		
		formulario(fuente);
		boton();
		
		JLabel saludo = new JLabel("Bienvenido!");
		saludo.setForeground(Color.PINK);
		
	}
	
	private void formulario(Font fuente) {
		JLabel lblSaludo = new JLabel("Bienvenido!");
		lblSaludo.setForeground(Color.BLACK);
		lblSaludo.setFont(fuente);
		lblSaludo.setBounds(190,0,200,40);
		add(lblSaludo);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(fuente);
		lblEmail.setBounds(10,100,200,40);
		add(lblEmail);
		
		JTextField texto = new JTextField();
		texto.setForeground(Color.BLUE);
		texto.setFont(fuente);
		texto.setBounds(150,100,200,40);
		add(texto);
		
		JLabel lblAlertaEmail = new JLabel("Ingresa un email");
		lblAlertaEmail.setForeground(Color.BLACK);
		lblAlertaEmail.setFont(fuente);
		lblAlertaEmail.setBounds(150,130,200,40);
		add(lblAlertaEmail);
		
		JLabel lblContrasena = new JLabel("Contraseña: ");
		lblContrasena.setForeground(Color.BLACK);
		lblContrasena.setFont(fuente);
		lblContrasena.setBounds(10,200,200,40);
		add(lblContrasena);
		
		JPasswordField contrasena = new JPasswordField();
		contrasena.setForeground(Color.BLUE);
		contrasena.setFont(fuente);
		contrasena.setBounds(150,200,200,40);
		add(contrasena);
		
		JLabel lblAlertaPassword = new JLabel("Contrasena incorrecta");
		lblAlertaPassword.setForeground(Color.BLACK);
		lblAlertaPassword.setFont(fuente);
		lblAlertaPassword.setBounds(150,200,1000,100);
		add(lblAlertaPassword);
	}
	
	public void boton() {
		JButton boton = new JButton("Iniciar sesion");
		boton.setBounds(150,300,200,30); //x,y,ancho,altura
		boton.setBackground(Color.GREEN);
		boton.setForeground(Color.BLACK);
		boton.setToolTipText("fasewvniefcn");
		boton.setFont(new Font("Arial", Font.ITALIC, 20));
		boton.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3, true));
		boton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GREEN, Color.YELLOW, Color.PINK, Color.ORANGE));
		boton.setBorder(BorderFactory.createDashedBorder(Color.GREEN, 3, 5, 1, true));
		boton.setToolTipText("Soy un tooltip");
		
		imagen(boton);
		
		add(boton);
	}
	
	public void imagen(JButton boton){
		try {
			Image icono = ImageIO.read(getClass().getResource("../img/iconoLogin.png"));
			icono = icono.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			boton.setIcon(new ImageIcon(icono));
		}
		catch(Exception ex){
			System.out.println("No se encontro la imagen");
		}
	}
	
	private void crearLogo() {
		JLabel lblSaludo = new JLabel();
		lblSaludo.setBounds(125, 50, 50, 50);
		//lblSaludo.Icon(new ImageIcon("src/img/roblo.png"));
	}
}
