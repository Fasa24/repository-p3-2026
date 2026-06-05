package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import utils.AppFont;

public class StoreFrontView extends JPanel {
    private JPanel categoriesPanel;
    private JPanel productsGrid;
    private JScrollPane scrollPane;
    public JButton btnViewCart; 
    private final DecimalFormat priceFormatter;

    public StoreFrontView() {
        this.priceFormatter = new DecimalFormat("$#,##0.00");

        setLayout(new BorderLayout(0, 15));
        setBorder(new EmptyBorder(10, 15, 15, 15));

        JPanel topHeaderPanel = new JPanel();
        topHeaderPanel.setLayout(new BoxLayout(topHeaderPanel, BoxLayout.Y_AXIS));

        JPanel titleRow = new JPanel(new BorderLayout());
        titleRow.setOpaque(false);
        
        JLabel lblSection = new JLabel("Store Catalog");
        lblSection.setFont(AppFont.title());
        
        btnViewCart = new JButton("View Cart (0)");
        btnViewCart.setFont(AppFont.normal());
        btnViewCart.putClientProperty("JButton.buttonType", "roundRect");
        
        titleRow.add(lblSection, BorderLayout.WEST);
        titleRow.add(btnViewCart, BorderLayout.EAST);

        categoriesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        categoriesPanel.setOpaque(false);

        topHeaderPanel.add(titleRow);
        topHeaderPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topHeaderPanel.add(categoriesPanel);
        
        add(topHeaderPanel, BorderLayout.NORTH);

        JPanel gridWrapper = new JPanel(new BorderLayout());
        productsGrid = new JPanel(new GridLayout(0, 3, 15, 15)); 
        gridWrapper.add(productsGrid, BorderLayout.NORTH); 
        
        scrollPane = new JScrollPane(gridWrapper);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(18); 
        
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateCartButtonText(int itemCount) {
        btnViewCart.setText("View Cart (" + itemCount + ")");
    }

    public void renderCategories(List<String> categories, java.awt.event.ActionListener listener) {
        categoriesPanel.removeAll();

        JButton btnAll = new JButton("All Products");
        btnAll.setFont(AppFont.normal());
        btnAll.setActionCommand("ALL");
        btnAll.addActionListener(listener);
        categoriesPanel.add(btnAll);

        for (String catName : categories) {
            JButton btnCat = new JButton(catName);
            btnCat.setFont(AppFont.normal());
            btnCat.setActionCommand(catName);
            btnCat.addActionListener(listener);
            categoriesPanel.add(btnCat);
        }

        categoriesPanel.revalidate();
        categoriesPanel.repaint();
    }

    public void renderProductCards(List<models.Product> products, java.awt.event.ActionListener buyListener) {
        productsGrid.removeAll();

        if (products.isEmpty()) {
            JPanel emptyPanel = new JPanel(new GridBagLayout());
            JLabel lblEmpty = new JLabel("No products available in this department.");
            lblEmpty.setFont(AppFont.normal());
            lblEmpty.setEnabled(false);
            emptyPanel.add(lblEmpty);
            
            productsGrid.setLayout(new BorderLayout());
            productsGrid.add(emptyPanel, BorderLayout.CENTER);
        } else {
            productsGrid.setLayout(new GridLayout(0, 3, 15, 15));
            for (models.Product p : products) {
                productsGrid.add(createProductCard(p, buyListener));
            }

            if (products.size() < 3) {
                int dummiesNeeded = 3 - products.size();
                for (int i = 0; i < dummiesNeeded; i++) {
                    productsGrid.add(new Box.Filler(new Dimension(0,0), new Dimension(0,0), new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE)));
                }
            }
        }

        productsGrid.revalidate();
        productsGrid.repaint();
    }

    private JPanel createProductCard(models.Product product, java.awt.event.ActionListener buyListener) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(0, 10));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIManager.getColor("Component.borderColor"), 1, true),
            new EmptyBorder(12, 12, 12, 12)
        ));
        card.putClientProperty("FlatLaf.style", "background: $Layer.background");

        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setPreferredSize(new Dimension(0, 135));
        imagePanel.setBackground(UIManager.getColor("Button.hoverBackground"));
        imagePanel.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Component.borderColor"), 1));

        String imagePath = "/resources/img/" + product.getId() + ".jpg";
        java.net.URL imgURL = getClass().getResource(imagePath);

        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaled = icon.getImage().getScaledInstance(-1, 131, Image.SCALE_SMOOTH);
            JLabel lblImg = new JLabel(new ImageIcon(scaled));
            imagePanel.add(lblImg);
        } else {
            JLabel lblImgPlaceholder = new JLabel("Image Placeholder");
            lblImgPlaceholder.setFont(AppFont.small());
            lblImgPlaceholder.setEnabled(false);
            imagePanel.add(lblImgPlaceholder);
        }

        card.add(imagePanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel lblName = new JLabel(product.getName());
        lblName.setFont(new Font("Dialog", Font.BOLD, 14));
        lblName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblCat = new JLabel(product.getCategory().toUpperCase());
        lblCat.setFont(new Font("Dialog", Font.PLAIN, 10));
        lblCat.setEnabled(false);
        lblCat.setAlignmentX(Component.LEFT_ALIGNMENT);

        final double rating = product.getRating();
        final int reviews = product.getReviewsCount();
        final StringBuilder starsBuilder = new StringBuilder();
        
        int fullStars = (int) Math.round(rating); 
        for (int i = 1; i <= 5; i++) {
            if (i <= fullStars) {
                starsBuilder.append("★");
            } else {
                starsBuilder.append("☆");
            }
        }
        
        JLabel lblReviews = new JLabel(starsBuilder.toString() + " " + rating + " (" + reviews + " reviews)");
        lblReviews.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblReviews.setForeground(new Color(241, 196, 15));
        lblReviews.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblReviews.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblReviews.setToolTipText("Ver reseñas de " + product.getName());

        lblReviews.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Window parentWindow = SwingUtilities.getWindowAncestor(lblReviews);
                ReviewsDialog dialog = new ReviewsDialog(parentWindow, product);
                dialog.setVisible(true);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                lblReviews.setText("<html><u>" + starsBuilder + " " + rating + " (" + reviews + " reviews)</u></html>");
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                lblReviews.setText(starsBuilder.toString() + " " + rating + " (" + reviews + " reviews)");
            }
        });

        JLabel lblPrice = new JLabel(priceFormatter.format(product.getPrice()));
        lblPrice.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPrice.setForeground(new Color(46, 125, 50));
        lblPrice.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblStock = new JLabel("Stock: " + product.getStock() + " units");
        lblStock.setFont(AppFont.small());
        lblStock.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (product.getStock() <= 0) {
            lblStock.setForeground(Color.RED);
            lblStock.setText("Out of Stock");
            lblStock.setFont(new Font("Dialog", Font.BOLD, 11));
        } else if (product.getStock() <= 5) {
            lblStock.setForeground(new Color(210, 50, 50));
            lblStock.setText("Only " + product.getStock() + " left in stock!");
            lblStock.setFont(new Font("Dialog", Font.BOLD, 11));
        }

        infoPanel.add(lblCat);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        infoPanel.add(lblName);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        infoPanel.add(lblReviews); 
        infoPanel.add(Box.createRigidArea(new Dimension(0, 6)));
        infoPanel.add(lblPrice);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        infoPanel.add(lblStock);

        card.add(infoPanel, BorderLayout.CENTER);

        JButton btnAdd = new JButton("Add to Cart");
        btnAdd.setFont(AppFont.normal());
        btnAdd.setPreferredSize(new Dimension(0, 32));
        btnAdd.setActionCommand(product.getName());
        btnAdd.addActionListener(buyListener);
        btnAdd.putClientProperty("JButton.buttonType", "accent");
        
        if (product.getStock() <= 0) {
            btnAdd.setEnabled(false);
            btnAdd.setText("Out of Stock");
        }

        card.add(btnAdd, BorderLayout.SOUTH);

        return card;
    }
}
