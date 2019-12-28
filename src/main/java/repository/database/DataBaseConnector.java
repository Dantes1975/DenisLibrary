package repository.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    private static final DataBaseConnector INSTANCE = new DataBaseConnector();

    private static final String DATABASE_DRIVER = "org.h2.Driver";
    private static final String DATABASE_URL = "jdbc:h2:file:D:/database";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";

    static {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error load Driver " + e);
            throw new RuntimeException("Error load DataBase Driver - " + DATABASE_DRIVER, e);
        }
    }

    private DataBaseConnector() {
    }

    public static DataBaseConnector getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

}
