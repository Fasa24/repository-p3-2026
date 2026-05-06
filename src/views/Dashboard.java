package views;

import javax.swing.*;
import java.awt.*;
import utils.AppFont;

public class Dashboard extends JFrame {
	private CardLayout cardLayout;
	private JPanel container;
	public JMenuItem exit;
	public JButton btnHome, btnUsers;
	public UsersView usersPanel;

	public static final String HOME_VIEW = "HOME";
	public static final String USERS_VIEW = "USERS";

	public Dashboard() {
		setTitle("eManza - Dashboard");
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		initializeUI();
	}

	private void initializeUI() {
		setLayout(new BorderLayout());

		JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
		btnHome = new JButton("Home");
		btnUsers = new JButton("See Users");
		navbar.add(btnHome);
		navbar.add(btnUsers);
		add(navbar, BorderLayout.NORTH);

		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);

		JPanel homePanel = new JPanel(new GridBagLayout());
		JLabel welcomeLabel = new JLabel("Welcome to the Dashboard!", SwingConstants.CENTER);
		welcomeLabel.setFont(AppFont.title());
		homePanel.add(welcomeLabel);

		usersPanel = new UsersView();

		container.add(homePanel, HOME_VIEW);
		container.add(usersPanel, USERS_VIEW);

		add(container, BorderLayout.CENTER);

		setJMenuBar(createMenuBar());
	}

	private JMenuBar createMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("Session");
		exit = new JMenuItem("Log out");
		fileMenu.add(exit);
		mb.add(fileMenu);
		return mb;
	}

	/*
	public void setWindowSize(int width, int height) {
		setSize(width, height);
	}
	 */

	/*
	public void setWindowLocation(int x, int y) {
		setLocation(x, y);
	}
	 */

	public void showView(String viewName) {
		cardLayout.show(container, viewName);
	}
}
