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

            System.out.println("---------- Show list of employees ----------");
            repository.findAll().forEach(System.out::println);
            // System.out.println(repository.getById(2));

            System.out.println("\nAdding or updating an employee...");

            Employee employee = new Employee();
            employee.setId(7);
            employee.setFirst_name("Josh");
            employee.setPa_surname("Parker");
            employee.setMa_surname("Nicols");
            employee.setEmail("josh.parker@example.com");
            employee.setSalary((double)30000);
            repository.save(employee);

            System.out.println("\n---------- Show list of employees updated ----------");
            repository.findAll().forEach(System.out::println);

            System.out.println("\nRemove employee...");
            repository.delete(7);

            System.out.println("\n---------- Show list of employees updated ----------");
            repository.findAll().forEach(System.out::println);
        }
    }
}
