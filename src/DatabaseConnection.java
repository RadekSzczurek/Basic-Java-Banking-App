import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/bankJava";
    private static final String user = "root";
    private static final String password = "";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("Błąd połączenia z bazą danych.");
            }
        }
        return connection;
    }
}
