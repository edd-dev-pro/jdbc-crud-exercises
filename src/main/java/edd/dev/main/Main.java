package edd.dev.main;

import edd.dev.model.Employee;
import edd.dev.repository.EmployeeRepository;
import edd.dev.repository.Repository;
import edd.dev.util.DataBaseConnection;

import java.sql.*;

public class Main {
    static void main(String[] args) throws SQLException {

        try (Connection myConnection = DataBaseConnection.getInstance();) { // try-with-resources, better than finally
            Repository<Employee> repository = new EmployeeRepository();

            repository.findAll().forEach(System.out::println);
        }
    }
}
