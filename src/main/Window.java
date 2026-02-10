package main;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

import views.MyPanel;

public class Window extends JFrame{
	
	public Window() {
		setSize(600,400); //Establece el tamaño
		
		// Termina la ejecución del programa al cerrar la ventana.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Establece el lugar donde aparecerá la ventana
		setLocation(100,100); 
		
		// Establece la ubicación y el tamaño de la ventana 
		// setBounds(100,100,500,500);
		
		// Establece si la ventana puede redimensionarse
		setResizable(false);
		
		// Cambia el título de la ventana
		setTitle("Store");
		
		// Coloca la ventana al centro de la pantalla
		setLocationRelativeTo(null);
		
		// Permite la opción de agregar un icono para la ventana.
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image Icon = tk.getImage("C:\\Users\\Work\\eclipse-workspace\\JavaFour\\src\\img\\storeIcon.png");
		setIconImage(Icon);
		
		MyPanel customPanel = new MyPanel();
		add(customPanel);
		
		 // Establece visibilidad (Recomendable que siempre se encuentre al final).
		setVisible(true);
	}
	
}
