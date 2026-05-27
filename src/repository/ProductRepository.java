package repository;

import config.DatabaseConnection;
import models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    public List<Product> getCatalog() {
        List<Product> list = new ArrayList<>();

        String sql = """
        SELECT 
            pcv.product_name,
            pcv.category_name,
            pcv.price,
            pcv.stock,
            pcv.rating,
            COUNT(r.product_id) AS reviews_count
        FROM product_catalog_view pcv
        LEFT JOIN Reviews r 
            ON pcv.product_id = r.product_id
        GROUP BY 
            pcv.product_id,
            pcv.product_name,
            pcv.category_name,
            pcv.price,
            pcv.stock,
            pcv.rating
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Product(
                        rs.getString("product_name"),
                        rs.getString("category_name"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getDouble("rating"),
                        rs.getInt("reviews_count")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Object[]> getPendingOrders() {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT order_id, customer_name, shipping_address, total, order_date FROM pending_orders_view";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Object[]{
                        rs.getInt("order_id"),
                        rs.getString("customer_name"),
                        rs.getString("shipping_address"),
                        "$" + rs.getDouble("total"),
                        rs.getTimestamp("order_date")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Object[]> getSalesReport() {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT c.category_name AS category, " +
                     "SUM(od.quantity) AS units_sold, " +
                     "SUM(od.subtotal) AS revenue_generated " +
                     "FROM Categories c " +
                     "INNER JOIN Products p ON c.category_id = p.category_id " +
                     "INNER JOIN Order_Details od ON p.product_id = od.product_id " +
                     "GROUP BY c.category_name " +
                     "ORDER BY revenue_generated DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Object[]{
                        rs.getString("category"),
                        rs.getInt("units_sold"),
                        rs.getDouble("revenue_generated")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getCategoryNames() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT category_name FROM Categories ORDER BY category_name ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categories.add(rs.getString("category_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Product> getProductsByCategory(String categoryName) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT product_name, category_name, price, stock, rating, reviews_count FROM product_catalog_view WHERE category_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoryName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Product(
                            rs.getString("product_name"),
                            rs.getString("category_name"),
                            rs.getDouble("price"),
                            rs.getInt("stock"),
                            rs.getDouble("rating"),
                            rs.getInt("reviews_count")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean createOrder(int userId, Map<Product, Integer> cartItems) {
        String insertOrder = "INSERT INTO Orders (user_id, status, total) VALUES (?, 'Pending', 0.00)";
        String insertDetail = "INSERT INTO Order_Details (order_id, product_id, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";
        String queryProductId = "SELECT product_id FROM Products WHERE name = ?";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); 

            int orderId = -1;
            try (PreparedStatement psOrder = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
                psOrder.setInt(1, userId);
                psOrder.executeUpdate();
                
                try (ResultSet rs = psOrder.getGeneratedKeys()) {
                    if (rs.next()) {
                        orderId = rs.getInt(1);
                    }
                }
            }

            if (orderId == -1) throw new SQLException("Failed to retrieve generated Order ID.");

            try (PreparedStatement psLookUp = conn.prepareStatement(queryProductId);
                 PreparedStatement psDetail = conn.prepareStatement(insertDetail)) {
                 
                for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                    Product p = entry.getKey();
                    int qty = entry.getValue();
                    double subtotal = p.getPrice() * qty;

                    psLookUp.setString(1, p.getName());
                    int productId = -1;
                    try (ResultSet rsProd = psLookUp.executeQuery()) {
                        if (rsProd.next()) {
                            productId = rsProd.getInt("product_id");
                        }
                    }

                    if (productId == -1) {
                        throw new SQLException("Product reference security check failed for: " + p.getName());
                    }

                    psDetail.setInt(1, orderId);
                    psDetail.setInt(2, productId);
                    psDetail.setInt(3, qty);
                    psDetail.setDouble(4, p.getPrice());
                    psDetail.setDouble(5, subtotal);
                    psDetail.addBatch(); 
                }
                psDetail.executeBatch(); 
            }

            conn.commit(); 
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
    }
}
