package views;

import javax.swing.*;
import java.awt.*;
import utils.AppFont;

public class Dashboard extends JFrame {
	private JMenuItem exit;

	public Dashboard() {
		setTitle("eManza - Dashboard");
		setSize(800, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initializeUI();
	}

	private void initializeUI() {
		JMenuBar mb = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(AppFont.normal());

		exit = new JMenuItem("Exit");
		exit.setFont(AppFont.normal());

		fileMenu.add(exit);
		mb.add(fileMenu);

		setJMenuBar(mb);

		JLabel label = new JLabel("Welcome to eManza Dashboard", SwingConstants.CENTER);
		label.setFont(AppFont.title());

		add(label, BorderLayout.CENTER);
	}

	public void setExitListener(java.awt.event.ActionListener l) { exit.addActionListener(l); }
	public void showWindow() { setVisible(true); }
}
