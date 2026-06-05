package views;

import models.Product;
import models.Review;
import repository.ProductRepository;
import utils.AppFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReviewsDialog extends JDialog {

    private final ProductRepository repository = new ProductRepository();
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");

    public ReviewsDialog(Window parent, Product product) {
        super(parent, "Reviews: " + product.getName(), ModalityType.APPLICATION_MODAL);

        setSize(500, 460);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 12));
        mainPanel.setBorder(new EmptyBorder(16, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout(0, 4));
        headerPanel.setOpaque(false);

        JLabel lblName = new JLabel(product.getName());
        lblName.setFont(new Font("Dialog", Font.BOLD, 16));

        double rating = product.getRating();
        int reviewsCount = product.getReviewsCount();
        StringBuilder stars = buildStars((int) Math.round(rating));

        JLabel lblRating = new JLabel(stars + "  " + rating + " / 5.0  —  " + reviewsCount + " reviews");
        lblRating.setFont(new Font("Dialog", Font.PLAIN, 13));
        lblRating.setForeground(new Color(241, 196, 15));

        headerPanel.add(lblName, BorderLayout.NORTH);
        headerPanel.add(lblRating, BorderLayout.CENTER);
        headerPanel.add(new JSeparator(), BorderLayout.SOUTH);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        reviewsPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(reviewsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton btnClose = new JButton("Close");
        btnClose.setFont(AppFont.normal());
        btnClose.putClientProperty("JButton.buttonType", "roundRect");
        btnClose.addActionListener(e -> dispose());

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setOpaque(false);
        footer.add(btnClose);
        mainPanel.add(footer, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        loadReviews(reviewsPanel, product.getName());
    }

    private void loadReviews(JPanel reviewsPanel, String productName) {
        JLabel lblLoading = new JLabel("Loading reviews...");
        lblLoading.setFont(AppFont.normal());
        lblLoading.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLoading.setEnabled(false);
        reviewsPanel.add(lblLoading);

        SwingWorker<List<Review>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Review> doInBackground() {
                return repository.getReviewsByProductName(productName);
            }

            @Override
            protected void done() {
                reviewsPanel.removeAll();
                try {
                    List<Review> reviews = get();

                    if (reviews.isEmpty()) {
                        JLabel lblEmpty = new JLabel("No reviews yet for this product.");
                        lblEmpty.setFont(AppFont.normal());
                        lblEmpty.setEnabled(false);
                        lblEmpty.setAlignmentX(Component.CENTER_ALIGNMENT);
                        reviewsPanel.add(Box.createVerticalGlue());
                        reviewsPanel.add(lblEmpty);
                        reviewsPanel.add(Box.createVerticalGlue());
                    } else {
                        for (Review r : reviews) {
                            reviewsPanel.add(createReviewEntry(r));
                            reviewsPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                        }
                    }
                } catch (Exception e) {
                    JLabel lblError = new JLabel("Error loading reviews.");
                    lblError.setForeground(Color.RED);
                    lblError.setAlignmentX(Component.CENTER_ALIGNMENT);
                    reviewsPanel.add(lblError);
                    e.printStackTrace();
                }

                reviewsPanel.revalidate();
                reviewsPanel.repaint();
            }
        };

        worker.execute();
    }

    private JPanel createReviewEntry(Review review) {
        JPanel entry = new JPanel(new BorderLayout(6, 4));
        entry.setOpaque(false);
        entry.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIManager.getColor("Component.borderColor"), 1, true),
                new EmptyBorder(10, 12, 10, 12)
        ));
        entry.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JPanel topRow = new JPanel(new BorderLayout());
        topRow.setOpaque(false);

        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        leftSide.setOpaque(false);

        JLabel lblStars = new JLabel(buildStars(review.getRating()).toString());
        lblStars.setForeground(new Color(241, 196, 15));
        lblStars.setFont(new Font("Dialog", Font.PLAIN, 13));

        JLabel lblUser = new JLabel(review.getUsername());
        lblUser.setFont(new Font("Dialog", Font.BOLD, 12));

        leftSide.add(lblStars);
        leftSide.add(lblUser);

        JLabel lblDate = new JLabel(dateFormatter.format(review.getReviewDate()));
        lblDate.setFont(AppFont.small());
        lblDate.setEnabled(false);

        topRow.add(leftSide, BorderLayout.WEST);
        topRow.add(lblDate, BorderLayout.EAST);

        String commentText = review.getComment() != null && !review.getComment().isBlank()
                ? review.getComment()
                : "No comment provided.";

        JLabel lblComment = new JLabel("<html><body style='width:380px'>" + commentText + "</body></html>");
        lblComment.setFont(new Font("Dialog", Font.PLAIN, 12));

        entry.add(topRow, BorderLayout.NORTH);
        entry.add(lblComment, BorderLayout.CENTER);

        return entry;
    }

    private StringBuilder buildStars(int fullStars) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append(i <= fullStars ? "★" : "☆");
        }
        return sb;
    }
}