package edd.dev.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    static void main(String[] args) {
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;

        try{
            myConnection = DriverManager.getConnection(
                    "",
                    "",
                    ""
            );
            myStatement = myConnection.createStatement();
            myResultSet = myStatement.executeQuery("SELECT * FROM employees");

            while (myResultSet.next()) {
                System.out.println(myResultSet.getString("first_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo establecer la conexión...");
        }
    }
}
