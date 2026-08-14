package edd.dev.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/customer_management";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    private static Connection myConnection;

    public static Connection getInstance() throws SQLException {
        if (myConnection == null) {
            myConnection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return myConnection;
    }
}
