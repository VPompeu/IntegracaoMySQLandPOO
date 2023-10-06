import java.sql.*;

public class DTO {
    private Connection connection;

    public DTO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/intellij",
                    "root",
                    "3236"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
