package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {
    private ConnectionFactory() { }

    public static Connection getConnection() throws SQLException {
        String url = System.getenv().getOrDefault("CONDOMINIO_DB_URL",
                "jdbc:mysql://localhost:3306/Condominio?useSSL=false&serverTimezone=UTC");
        String user = System.getenv().getOrDefault("CONDOMINIO_DB_USER", "root");
        String password = System.getenv().getOrDefault("CONDOMINIO_DB_PASSWORD", "");
        return DriverManager.getConnection(url, user, password);
    }
}
