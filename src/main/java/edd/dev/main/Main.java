package edd.dev.main;

import java.sql.*;

public class Main {
    static void main(String[] args) throws SQLException {
        final String URL = "";
        final String USER = "";
        final String PASSWORD = "";

        try (Connection myConnection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement myStatement = myConnection.createStatement();
             ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM employees");) { // try with resources, better than finally

            while (myResultSet.next()) {
                System.out.println(myResultSet.getString("first_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo establecer la conexión...");
        }
        /* finally {
            if (myResultSet != null) {
                myResultSet.close();
            }
            if (myStatement != null) {
                myResultSet.close();
            }
            if (myConnection != null) {
                myResultSet.close();
            }
        }*/
    }
}
