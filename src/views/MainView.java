package views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainView extends JFrame{
	public MainView() {
		setSize(500, 500);
		setTitle("sadsad");
		
		setMenu();
	}
	
	public void setMenu() {
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		
		JMenuItem archivo = new JMenuItem("Archivo");
		mb.add(archivo);
		
		JMenuItem abrir = new JMenuItem("Abrir");
		mb.add(abrir);
		
		JMenuItem guardar = new JMenuItem("Guardar");
		mb.add(guardar);
		
		
		
		JMenuItem salir = new JMenuItem("Salir");
		mb.add(salir);
		
		JMenu otraOpcion = new JMenu("otra opcion");
		mb.add(otraOpcion);
		
		JMenuItem opcion1 = new JMenuItem("opcion 1");
		otraOpcion.add(opcion1);
		
	}
}
