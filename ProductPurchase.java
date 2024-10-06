import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductPurchase {

    public static boolean buyNow(int userId, int productId, int quantity) throws SQLException {
        Connection connection = DBConnection.getConnection();

        // Check product availability (optional)
        String checkStockSql = "SELECT stock FROM products WHERE id = ?";
        try (PreparedStatement checkStockPs = connection.prepareStatement(checkStockSql)) {
            checkStockPs.setInt(1, productId);
            ResultSet stockRs = checkStockPs.executeQuery();
            if (!stockRs.next() || stockRs.getInt("stock") < quantity) {
                return false; // Insufficient stock
            }
        }

        // Create order
        String createOrderSql = "INSERT INTO orders (user_id, total_amount) VALUES (?, ?)";
        double totalPrice = getProductPrice(productId) * quantity;
        try (PreparedStatement createOrderPs = connection.prepareStatement(createOrderSql)) {
            createOrderPs.setInt(1, userId);
            createOrderPs.setDouble(2, totalPrice);
            createOrderPs.executeUpdate();
        }

        // Get last inserted order ID
        int orderId = getLastOrderId(connection);

        // Create order item
        String createOrderItemSql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement createOrderItemPs = connection.prepareStatement(createOrderItemSql)) {
            createOrderItemPs.setInt(1, orderId);
            createOrderItemPs.setInt(2, productId);
            createOrderItemPs.setInt(3, quantity);
            createOrderItemPs.executeUpdate();
        }

        // Update product stock (optional - consider optimistic locking)
        String updateStockSql = "UPDATE products SET stock = stock - ? WHERE id = ?";
        try (PreparedStatement updateStockPs = connection.prepareStatement(updateStockSql)) {
            updateStockPs.setInt(1, quantity);
            updateStockPs.setInt(2, productId);
            updateStockPs.executeUpdate();
        }

        return true;
    }

    private static double getProductPrice(int productId) throws SQLException {
        Connection connection = DBConnection.getConnection();

        String getPriceSql = "SELECT price FROM products WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(getPriceSql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            } else {
                throw new SQLException("Product not found");
            }
        }
    }

    private static int getLastOrderId(Connection connection) throws SQLException {
        String getLastOrderIdSql = "SELECT LAST_INSERT_ID()";
        try (PreparedStatement ps = connection.prepareStatement(getLastOrderIdSql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Failed to get last order ID");
            }
        }
    }
}