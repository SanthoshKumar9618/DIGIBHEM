

import java.sql.*;

public class AdminDao {
    private Connection conn;

    public AdminDao(Connection conn) {
        this.conn = conn;
    }

    public boolean loginAdmin(String username, String password) {
        // Simple admin login validation
        return username.equals("admin") && password.equals("admin123");
    }

    // Methods to manage movies, theaters, etc.
}
