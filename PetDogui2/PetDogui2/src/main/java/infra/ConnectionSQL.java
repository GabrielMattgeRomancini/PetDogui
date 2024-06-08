package infra;

import exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/PetDogui";
    private static final String JDBC_USERNAME = "postgres";
    private static final String JDBC_PASSWORD = "123456";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to connect to the database", e);
        }
    }
}
