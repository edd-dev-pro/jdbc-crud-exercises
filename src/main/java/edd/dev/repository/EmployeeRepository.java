package edd.dev.repository;

import edd.dev.model.Employee;
import edd.dev.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getInstance();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();

        try (Statement myStatement = this.getConnection().createStatement();
             ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM employees");) {

            while (myResultSet.next()) {
                Employee employee = createEmployee(myResultSet);
                employees.add(employee);
            }
        }
        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;

        try (PreparedStatement myStatement = getConnection().prepareStatement("SELECT * FROM employees WHERE id = ?")) {
            myStatement.setInt(1, id);

            try (ResultSet myResultSet = myStatement.executeQuery()) {
                if (myResultSet.next()) {
                   employee = createEmployee(myResultSet);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) throws SQLException {
        String sqlQuery = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary) VALUES (?,?,?,?,?)";

        try (PreparedStatement myStatement = getConnection().prepareStatement(sqlQuery)) {
            myStatement.setString(1, employee.getFirst_name());
            myStatement.setString(2, employee.getPa_surname());
            myStatement.setString(3, employee.getMa_surname());
            myStatement.setString(4, employee.getEmail());
            myStatement.setDouble(5, employee.getSalary());
            myStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) {

    }

    private Employee createEmployee(ResultSet myResultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(myResultSet.getInt("id"));
        employee.setFirst_name(myResultSet.getString("first_name"));
        employee.setPa_surname(myResultSet.getString("pa_surname"));
        employee.setMa_surname(myResultSet.getString("ma_surname"));
        employee.setEmail(myResultSet.getString("email"));
        employee.setSalary(myResultSet.getDouble("salary"));
        return employee;
    }
}
