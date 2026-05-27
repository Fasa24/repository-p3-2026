package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Map;
import models.Product;
import utils.AppFont;

public class CartDialog extends JDialog {
    private JTable table;
    private DefaultTableModel model;
    private JLabel lblTotal;
    public JButton btnCheckout, btnClear;
    private double totalAmount = 0.0;
    private final DecimalFormat priceFormatter;

    public CartDialog(JFrame parent, Map<Product, Integer> cartItems) {
        super(parent, "Your Shopping Cart", true);
        this.priceFormatter = new DecimalFormat("$#,##0.00");
        
        setSize(600, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // Header Section
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        JLabel title = new JLabel("Review your order items");
        title.setFont(AppFont.title());
        headerPanel.add(title);
        add(headerPanel, BorderLayout.NORTH);

        // Breakdown Table Configuration
        String[] columns = {"Product", "Unit Price", "Qty", "Subtotal"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        table.setRowHeight(25);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        add(scroll, BorderLayout.CENTER);

        // Bottom Dashboard Actions Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(0, 15, 15, 15));

        lblTotal = new JLabel("Order Total: $0.00");
        lblTotal.setFont(new Font("Dialog", Font.BOLD, 16));
        bottomPanel.add(lblTotal, BorderLayout.WEST);

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnClear = new JButton("Clear");
        btnClear.setFont(AppFont.normal());
        
        btnCheckout = new JButton("Place your order");
        btnCheckout.setFont(AppFont.normal());
        btnCheckout.putClientProperty("JButton.buttonType", "accent");

        actionsPanel.add(btnClear);
        actionsPanel.add(btnCheckout);
        bottomPanel.add(actionsPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        calculateAndFill(cartItems);
    }

    private void calculateAndFill(Map<Product, Integer> cartItems) {
        model.setRowCount(0);
        totalAmount = 0.0;

        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            double subtotal = p.getPrice() * qty;
            totalAmount += subtotal;

            model.addRow(new Object[]{
                p.getName(),
                priceFormatter.format(p.getPrice()),
                qty,
                priceFormatter.format(subtotal)
            });
        }

        lblTotal.setText("Order Total: " + priceFormatter.format(totalAmount));
        btnCheckout.setEnabled(totalAmount > 0);
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
