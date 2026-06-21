import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/car_rental";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("CAR_RENTAL_DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        if (PASSWORD == null) {
            throw new RuntimeException("Database password not set. Run: export CAR_RENTAL_DB_PASSWORD=yourpassword");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}