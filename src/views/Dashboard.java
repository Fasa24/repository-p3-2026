package views;

import javax.swing.*;
import java.awt.*;
import utils.AppFont;

public class Dashboard extends JFrame {
	private JMenuItem exit;
	public JButton btnViewUsers;

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

		JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

		JLabel label = new JLabel("Welcome to eManza Dashboard", SwingConstants.CENTER);
		label.setFont(AppFont.title());
		mainPanel.add(label, BorderLayout.CENTER);

		btnViewUsers = new JButton("Consult registered users.");
		btnViewUsers.setFont(AppFont.normal());
		btnViewUsers.setPreferredSize(new Dimension(300, 40));

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(btnViewUsers);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(mainPanel);
	}

	public void setExitListener(java.awt.event.ActionListener l) {
		if (exit != null) {
			exit.addActionListener(l);
		}
	}

	public void showWindow() { setVisible(true); }
}
