package edd.dev.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

    private static Connection myConnection;

    public static Connection getInstance() throws SQLException {
        if (myConnection == null) {
            myConnection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return myConnection;
    }
}
