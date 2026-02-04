package main;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window() {
		setSize(500,500); //Establece el tamaño
		
		//Termina la ejecución del programa al cerrar la ventana.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Establece el lugar donde aparecerá la ventana
		setLocation(100,100); 
		
		//Establece la ubicación y el tamaño de la ventana 
		//setBounds(100,100,500,500);
		
		//Establece si la ventana puede redimensionarse
		setResizable(false);
		
		//Cambia el título de la ventana
		setTitle("My App");
		
		//Coloca la ventana al centro de la pantalla
		setLocationRelativeTo(null);
		
		setVisible(true); //Establece visibilidad (Recomendable que siempre se encuentre al final).
	}
	
}
