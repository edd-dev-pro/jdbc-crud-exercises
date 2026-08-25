package edd.dev.main;

import edd.dev.model.Employee;
import edd.dev.repository.EmployeeRepository;
import edd.dev.repository.Repository;
import edd.dev.util.DataBaseConnection;

import java.sql.*;

public class Main {
    static void main(String[] args) throws SQLException {
        try (Connection myConnection = DataBaseConnection.getInstance()) {
            if (myConnection.getAutoCommit()) {
                myConnection.setAutoCommit((false));
            }

            try {
               Repository<Employee> repository = new EmployeeRepository(myConnection);
               System.out.println("----- Add new employee -----");

               Employee employee = new Employee();
               /* employee.setFirst_name("America");
               employee.setPa_surname("Alpes");
               employee.setMa_surname("Villa");
               employee.setEmail("america.alp@example.com");
               employee.setSalary((double)30000);
               employee.setCurp("ALVA940312LK8KII76");*/

                employee.setFirst_name("Kenia");
                employee.setPa_surname("Alpes");
                employee.setMa_surname("Villa");
                employee.setEmail("america.alp@example.com");
                employee.setSalary((double)50000);
                employee.setCurp("ALVA940312LK8KII76"); // Since we are using a duplicate curp value, the employee cannot be saved

               repository.save(employee);

               myConnection.commit();
            } catch (SQLException e) {
                myConnection.rollback();

                throw new RuntimeException(e);
            }
        }
    }
}
