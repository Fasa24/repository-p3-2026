package main;

import java.awt.Color;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	
	public MyPanel() {
		//Esta opción utiliza un color predefinido
		//setBackground(Color.BLUE);
		
		//Esta opción genera un color a partir de RGB
		setBackground(new Color(210,165,35));
	}
	
}
