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

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        JLabel title = new JLabel("Review your order items");
        title.setFont(AppFont.title());
        headerPanel.add(title);
        add(headerPanel, BorderLayout.NORTH);

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

    private void showPaymentDialog(Runnable onConfirmed) {
        JDialog paymentDialog = new JDialog(this, "Payment Method", true);
        paymentDialog.setSize(380, 320);
        paymentDialog.setLocationRelativeTo(this);
        paymentDialog.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 16));
        mainPanel.setBorder(new EmptyBorder(20, 24, 20, 24));

        JLabel lblTitle = new JLabel("Select a payment method");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 15));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        optionsPanel.setOpaque(false);

        ButtonGroup group = new ButtonGroup();
        JRadioButton rbCredit = new JRadioButton("Credit Card");
        JRadioButton rbDebit  = new JRadioButton("Debit Card");
        JRadioButton rbPaypal = new JRadioButton("PayPal");
        JRadioButton rbApple  = new JRadioButton("Apple Pay");

        for (JRadioButton rb : new JRadioButton[]{rbCredit, rbDebit, rbPaypal, rbApple}) {
            rb.setFont(AppFont.normal());
            group.add(rb);
            optionsPanel.add(rb);
        }
        rbCredit.setSelected(true);
        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        JButton btnConfirm = new JButton("Confirm Payment  " + priceFormatter.format(totalAmount));
        btnConfirm.setFont(AppFont.normal());
        btnConfirm.putClientProperty("JButton.buttonType", "accent");
        btnConfirm.addActionListener(ev -> {
            String method = rbCredit.isSelected() ? "Credit Card"
                    : rbDebit.isSelected()  ? "Debit Card"
                      : rbPaypal.isSelected() ? "PayPal"
                        : "Apple Pay";
            paymentDialog.dispose();

            onConfirmed.run();
        });

        mainPanel.add(btnConfirm, BorderLayout.SOUTH);
        paymentDialog.setContentPane(mainPanel);
        paymentDialog.setVisible(true);
    }

    public void triggerPaymentFlow(Runnable onConfirmed) {
        showPaymentDialog(onConfirmed);
    }

    public void showSuccessAndClose() {
        JDialog successDialog = new JDialog(this, "Order Confirmed", true);
        successDialog.setSize(360, 240);
        successDialog.setLocationRelativeTo(this);
        successDialog.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(0, 14));
        panel.setBorder(new EmptyBorder(28, 28, 20, 28));

        JLabel lblMsg = new JLabel("Transaction Successful!", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Dialog", Font.BOLD, 16));
        lblMsg.setForeground(new Color(46, 125, 50));

        JLabel lblSub = new JLabel(
                "<html><center>Paid " + priceFormatter.format(totalAmount) +
                        ".<br>Thank you for your purchase!</center></html>",
                SwingConstants.CENTER
        );
        lblSub.setFont(AppFont.normal());

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 6));
        textPanel.setOpaque(false);
        textPanel.add(lblMsg);
        textPanel.add(lblSub);

        panel.add(textPanel, BorderLayout.CENTER);

        JButton btnDone = new JButton("Done");
        btnDone.setFont(AppFont.normal());
        btnDone.putClientProperty("JButton.buttonType", "roundRect");
        btnDone.addActionListener(e -> {
            successDialog.dispose();
            dispose();
        });

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setOpaque(false);
        footer.add(btnDone);
        panel.add(footer, BorderLayout.SOUTH);

        successDialog.setContentPane(panel);
        successDialog.setVisible(true);
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
