package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BorderPanel extends JPanel {
	public BorderPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.BLUE);
		//setBackground(Color.BLUE);
		Border emptyBorder = BorderFactory.createEmptyBorder(20,10,20,10);
		setBorder(emptyBorder);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.GREEN);
	}
}
