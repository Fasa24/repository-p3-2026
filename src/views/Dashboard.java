package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import utils.AppFont;
import utils.Session;

public class Dashboard extends JFrame {
    private CardLayout cardLayout;
    private JPanel container;
    
    public JMenuItem exit;
    public JButton btnHome, btnCatalog, btnOrders, btnUsers, btnReports;
    public UsersView usersPanel;
    public StoreFrontView storeFrontPanel; 
    
    public AdminPanelMock catalogPanel;
    public AdminPanelMock ordersPanel;
    public AdminPanelMock reportsPanel;

    public static final String HOME_VIEW = "HOME";
    public static final String CATALOG_VIEW = "CATALOG";
    public static final String ORDERS_VIEW = "ORDERS";
    public static final String USERS_VIEW = "USERS";
    public static final String REPORTS_VIEW = "REPORTS";

    public Dashboard() {
        setTitle("eManza - Online Store Dashboard");
        setSize(1100, 750); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // --- CARGA Y CONFIGURACIÓN DEL LOGO ---
        ImageIcon rawIcon = new ImageIcon("src/resources/img/appleLogo.png");
        
        // REQUERIMIENTO 3B: Cambiar el icono pequeño de la barra de título de la ventana
        if (rawIcon.getImage() != null) {
            setIconImage(rawIcon.getImage());
        }

        // --- 1. BARRA DE NAVEGACIÓN SUPERIOR ESTILO AMAZON ---
        JPanel amazonNavbar = new JPanel(new BorderLayout(15, 0));
        amazonNavbar.setBackground(new Color(20, 20, 20)); 
        amazonNavbar.setBorder(new EmptyBorder(10, 20, 10, 20));

        // REQUERIMIENTO 3A: Escalar y agregar el logotipo al lado del texto "eManza"
        JLabel lblBrand;
        if (rawIcon.getIconWidth() > 0) {
            Image scaledLogo = rawIcon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
            ImageIcon navIcon = new ImageIcon(scaledLogo);
            lblBrand = new JLabel("eManza", navIcon, JLabel.LEFT);
            lblBrand.setIconTextGap(8);
        } else {
            lblBrand = new JLabel("🛒 eManza");
        }
        
        lblBrand.setFont(new Font("Dialog", Font.BOLD, 22));
        lblBrand.setForeground(Color.WHITE);
        amazonNavbar.add(lblBrand, BorderLayout.WEST);

        // Center: System navegation buttons
        JPanel systemNavPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        systemNavPanel.setOpaque(false);

        btnHome = createNavbarButton("StoreFront");
        btnCatalog = createNavbarButton("Products");
        btnOrders = createNavbarButton("Orders");
        btnReports = createNavbarButton("Sales Metrics");
        btnUsers = createNavbarButton("Users");

        systemNavPanel.add(btnHome);
        systemNavPanel.add(btnCatalog);
        systemNavPanel.add(btnOrders);
        systemNavPanel.add(btnReports);

        if (Session.isLoggedIn() && Session.getRole().equals("ADMIN")) {
            systemNavPanel.add(btnUsers);
        }
        amazonNavbar.add(systemNavPanel, BorderLayout.CENTER);

        add(amazonNavbar, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        container.setBorder(new EmptyBorder(10, 10, 10, 10));

        storeFrontPanel = new StoreFrontView();
        usersPanel = new UsersView();
        catalogPanel = new AdminPanelMock("Products Inventory Maintenance");
        ordersPanel = new AdminPanelMock("Pending Customer Orders");
        reportsPanel = new AdminPanelMock("Category Performance Reports");

        container.add(storeFrontPanel, HOME_VIEW);
        container.add(catalogPanel, CATALOG_VIEW);
        container.add(ordersPanel, ORDERS_VIEW);
        container.add(usersPanel, USERS_VIEW);
        container.add(reportsPanel, REPORTS_VIEW);

        add(container, BorderLayout.CENTER);

        setJMenuBar(createMenuBar());
    }

    private JButton createNavbarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(AppFont.normal());
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(new Color(0, 255, 0)); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.WHITE);
            }
        });
        return btn;
    }

    private JMenuBar createMenuBar() {
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("Account");
        exit = new JMenuItem("Log out");
        fileMenu.add(exit);
        mb.add(fileMenu);
        return mb;
    }

    public void showView(String viewName) {
        cardLayout.show(container, viewName);
    }

    public static class AdminPanelMock extends JPanel {
        private javax.swing.table.DefaultTableModel internalModel;
        private JTable internalTable;

        public AdminPanelMock(String title) {
            setLayout(new BorderLayout(10, 10));
            JLabel lbl = new JLabel(title);
            lbl.setFont(AppFont.title());
            add(lbl, BorderLayout.NORTH);

            internalModel = new javax.swing.table.DefaultTableModel(new String[]{"Data Field 1", "Data Field 2", "Data Field 3", "Data Field 4"}, 0);
            internalTable = new JTable(internalModel);
            add(new JScrollPane(internalTable), BorderLayout.CENTER);
        }
        public void clearData() { internalModel.setRowCount(0); }
        public void addRow(Object[] row) { internalModel.addRow(row); }
    }
}
