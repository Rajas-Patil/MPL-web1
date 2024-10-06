import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/agrinest"; // Replace with your database URL
    private static final String DB_USERNAME = "root"; // Replace with your database username
    private static final String DB_PASSWORD = "password"; // Replace with your database password

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC driver (adjust for your version)
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC Driver not found: " + e.getMessage());
            }
        }
        return connection;
    }
}