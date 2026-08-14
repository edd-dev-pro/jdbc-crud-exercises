package edd.dev.main;

import edd.dev.util.DataBaseConnection;

import java.sql.*;

public class Main {
    static void main(String[] args) throws SQLException {
        try (Connection myConnection = DataBaseConnection.getInstance();
             Statement myStatement = myConnection.createStatement();
             ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM employees");) { // try-with-resources, better than finally

            while (myResultSet.next()) {
                System.out.println(myResultSet.getString("first_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo establecer la conexión...");
        }
    }
}
