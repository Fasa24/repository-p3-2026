package views;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Dashboard extends JFrame {

	JMenuItem salir;
	
	public Dashboard() {
		
		setSize(500,500);
		setTitle("eManza - Dashboard");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMenu();
		assignMenuListeners();
		setVisible(true);
		
	}
	
	public void setMenu() {
		
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		
		JMenu archivo = new JMenu("Archive");
		archivo.setMnemonic(KeyEvent.VK_A);
		mb.add(archivo);
		
		JMenuItem abrir = new JMenuItem("Open");
		abrir.setMnemonic(KeyEvent.VK_B);
		archivo.add(abrir);
		
		JMenuItem guardar = new JMenuItem("Save");
		guardar.setMnemonic(KeyEvent.VK_G);
		archivo.add(guardar);
		
		archivo.addSeparator();
		
		salir = new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_S);
		archivo.add(salir);
		
		JMenu otraOpcion = new JMenu("Other categories");
		otraOpcion.setMnemonic(KeyEvent.VK_O);
		mb.add(otraOpcion);
		
		JMenu opcion1 = new JMenu("Food");
		otraOpcion.add(opcion1);
		
		JMenuItem opcion3 = new JMenuItem("Mango");
		opcion1.add(opcion3);
		
		JMenuItem opcion2 = new JMenuItem("Electronics");
		otraOpcion.add(opcion2);
		
	}
	
	public void assignMenuListeners() {
		salir.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(this, "¿Are you sure you want to go back? All data will be lost.", "¿Are you sure?", JOptionPane.YES_NO_OPTION);
			
			if(option == JOptionPane.YES_OPTION) {
				new LoginWindow();
				dispose();
			}
		});
	}
	
}
