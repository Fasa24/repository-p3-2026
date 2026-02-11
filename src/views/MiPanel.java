package views;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JPanel;

public class MiPanel extends JPanel{
	
	public MiPanel() {
		Font fuente = new Font("Impact", Font.BOLD, 20);
		setBackground(new Color(177, 26, 242)); //rgb
		setLayout(null);
		
		JButton boton = new JButton("Mi boton");
		boton.setBounds(50,50,200,30); //x,y,ancho,altura
		boton.setBackground(Color.GREEN);
		boton.setForeground(Color.RED);
		boton.setToolTipText("fasewvniefcn");
		boton.setFont(new Font("Arial", Font.ITALIC, 20));
		
		try {
			Image icono = ImageIO.read(getClass().getResource("../img/roblo.png"));
			icono = icono.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			boton.setIcon(new ImageIcon(icono));
		}
		catch(Exception ex){
			System.out.println("No se encontro la imagen");
		}
		
		add(boton);
		
		JLabel saludo = new JLabel("Bienvenido!");
		saludo.setForeground(Color.PINK);
		
	}
}
