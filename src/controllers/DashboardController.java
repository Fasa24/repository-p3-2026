package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import models.Product;
import repository.UserRepository;
import repository.ProductRepository;
import utils.Session;
import views.Dashboard;
import views.LoginWindow;
import views.CartDialog;

public class DashboardController {
    private Dashboard view;
    private UserRepository repository;
    private ProductRepository productRepository;
    private UserController userController;

    private Map<Product, Integer> shoppingCart;
    private String currentCategoryFilter = "ALL";

    public DashboardController(Dashboard view) {
        this.view = view;
        this.repository = new UserRepository();
        this.productRepository = new ProductRepository();
        this.shoppingCart = new HashMap<>();

        init();
        registerListeners();
    }

    public void init() {
        view.exit.addActionListener(e -> exitToLogin());

        view.btnHome.addActionListener(e -> {
            view.showView(Dashboard.HOME_VIEW);
            updateMenuState(Dashboard.HOME_VIEW);
        });

        view.btnCatalog.addActionListener(e -> {
            loadCatalogData();
            view.showView(Dashboard.CATALOG_VIEW);
            updateMenuState(Dashboard.CATALOG_VIEW);
        });

        view.btnOrders.addActionListener(e -> {
            loadOrdersData();
            view.showView(Dashboard.ORDERS_VIEW);
            updateMenuState(Dashboard.ORDERS_VIEW);
        });

        if (view.btnUsers != null) {
            view.btnUsers.addActionListener(e -> {
                showUsers();
                updateMenuState(Dashboard.USERS_VIEW);
            });
        }

        view.btnReports.addActionListener(e -> {
            loadReportsData();
            view.showView(Dashboard.REPORTS_VIEW);
            updateMenuState(Dashboard.REPORTS_VIEW);
        });

        view.storeFrontPanel.btnViewCart.addActionListener(e -> openCartCheckout());

        initStoreFront();
        view.setVisible(true);
    }

    private void initStoreFront() {
        List<String> categories = productRepository.getCategoryNames();
        view.storeFrontPanel.renderCategories(categories, e -> {
            currentCategoryFilter = e.getActionCommand();
            refreshStoreFrontProducts();
        });

        refreshStoreFrontProducts();
    }

    private void refreshStoreFrontProducts() {
        if (currentCategoryFilter.equals("ALL")) {
            view.storeFrontPanel.renderProductCards(productRepository.getCatalog(), getBuyAction());
        } else {
            view.storeFrontPanel.renderProductCards(productRepository.getProductsByCategory(currentCategoryFilter), getBuyAction());
        }
        
        int totalItems = shoppingCart.values().stream().mapToInt(Integer::intValue).sum();
        view.storeFrontPanel.updateCartButtonText(totalItems);
    }

    private java.awt.event.ActionListener getBuyAction() {
        return e -> {
            String productName = e.getActionCommand();
            List<Product> databaseCatalog = productRepository.getCatalog();
            Product target = databaseCatalog.stream()
                    .filter(p -> p.getName().equals(productName))
                    .findFirst().orElse(null);

            if (target != null) {
                int currentInCart = shoppingCart.getOrDefault(target, 0);
                
                if (currentInCart + 1 > target.getStock()) {
                    JOptionPane.showMessageDialog(view, 
                        "Cannot add more units. Database inventory limit reached (" + target.getStock() + " available).", 
                        "Out of Stock", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                shoppingCart.put(target, currentInCart + 1);
                refreshStoreFrontProducts();
            }
        };
    }

    private void openCartCheckout() {
        CartDialog dialog = new CartDialog(view, shoppingCart);

        dialog.btnClear.addActionListener(e -> {
            shoppingCart.clear();
            dialog.dispose();
            refreshStoreFrontProducts();
        });

        dialog.btnCheckout.addActionListener(e -> {
            dialog.triggerPaymentFlow(() -> {
                int userId = Session.getCurrentUser().getId();
                boolean success = productRepository.createOrder(userId, shoppingCart);

                if (success) {
                    shoppingCart.clear();
                    refreshStoreFrontProducts();
                    dialog.showSuccessAndClose(); // Muestra confirmación  cierra
                } else {
                    JOptionPane.showMessageDialog(dialog,
                            "Transaction error. Database rollback executed.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        });

        dialog.setVisible(true);
    }

    private void loadCatalogData() {
        view.catalogPanel.setColumns("Product", "Category", "Price", "Stock");
        view.catalogPanel.clearData();
        List<Product> products = productRepository.getCatalog();
        for (Product p : products) {
            view.catalogPanel.addRow(new Object[]{p.getName(), p.getCategory(), "$" + p.getPrice(), p.getStock()});
        }
    }

    private void loadOrdersData() {
        view.ordersPanel.setColumns("ID", "Name", "Address", "Total");
        view.ordersPanel.clearData();
        List<Object[]> orders = productRepository.getPendingOrders();
        for (Object[] row : orders) {
            view.ordersPanel.addRow(row);
        }
    }

    private void loadReportsData() {
        view.reportsPanel.setColumns("Category", "Amount", "Total", "");
        view.reportsPanel.clearData();
        List<Object[]> reportRows = productRepository.getSalesReport();
        for (Object[] row : reportRows) {
            view.reportsPanel.addRow(new Object[]{row[0], row[1], "$" + row[2]});
        }
    }

    private void showUsers() {
        if (userController == null) {
            userController = new UserController(view.usersPanel);
        }
        userController.loadUsers();
        view.showView(Dashboard.USERS_VIEW);
    }

    private void exitToLogin() {
        int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to close the session?", "Log Out", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            view.dispose();
            new LoginWindow();
        }
    }

    public void registerListeners() {
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                view.dispose();
            }
        });
    }

    private void updateMenuState(String viewName) {
        view.btnHome.setEnabled(!viewName.equals(Dashboard.HOME_VIEW));
        view.btnCatalog.setEnabled(!viewName.equals(Dashboard.CATALOG_VIEW));
        view.btnOrders.setEnabled(!viewName.equals(Dashboard.ORDERS_VIEW));
        if (view.btnUsers != null) {
            view.btnUsers.setEnabled(!viewName.equals(Dashboard.USERS_VIEW));
        }
        view.btnReports.setEnabled(!viewName.equals(Dashboard.REPORTS_VIEW));
    }
}
