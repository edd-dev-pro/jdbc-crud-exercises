package edd.dev.repository;

import edd.dev.model.Employee;
import edd.dev.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private Connection myConnection;

    public EmployeeRepository(Connection myConnection) { // Now we use a single connection across all methods
        this.myConnection = myConnection;
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();

        try (Statement myStatement = myConnection.createStatement();
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

        try (PreparedStatement myStatement = myConnection.prepareStatement("SELECT * FROM employees WHERE id = ?")) {
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
        String sqlQuery;

        if (employee.getId() != null && employee.getId() > 0) {
            sqlQuery = "UPDATE employees SET first_name=?, pa_surname=?, ma_surname=?, email=?, salary=?, curp=? WHERE id=?";
        } else {
            sqlQuery = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary, curp) VALUES (?,?,?,?,?,?)";
        }

        try (PreparedStatement myStatement = myConnection.prepareStatement(sqlQuery)) {
            myStatement.setString(1, employee.getFirst_name());
            myStatement.setString(2, employee.getPa_surname());
            myStatement.setString(3, employee.getMa_surname());
            myStatement.setString(4, employee.getEmail());
            myStatement.setDouble(5, employee.getSalary());
            myStatement.setString(6, employee.getCurp());
            if (employee.getId() != null && employee.getId() > 0) {
                myStatement.setInt(7, employee.getId());
            }
            myStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement myStatement = myConnection.prepareStatement("DELETE FROM employees WHERE id=?")) {
            myStatement.setInt(1, id);
            myStatement.executeUpdate();
        }
    }

    private Employee createEmployee(ResultSet myResultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(myResultSet.getInt("id"));
        employee.setFirst_name(myResultSet.getString("first_name"));
        employee.setPa_surname(myResultSet.getString("pa_surname"));
        employee.setMa_surname(myResultSet.getString("ma_surname"));
        employee.setEmail(myResultSet.getString("email"));
        employee.setSalary(myResultSet.getDouble("salary"));
        employee.setCurp(myResultSet.getString("curp"));
        return employee;
    }
}
