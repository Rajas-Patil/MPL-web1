import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin {

    public static boolean login(String username, String password) throws SQLException {
        Connection connection = DBConnection.getConnection();

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password); // Hash password before storing (see security section)
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Return true if a user is found, false otherwise
        }
    }
}