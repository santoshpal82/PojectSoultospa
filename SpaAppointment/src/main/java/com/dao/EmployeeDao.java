package com.dao;

import java.util.List;
import com.entity.Employee;

public interface EmployeeDao {
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int employeeId);
    Employee updateEmployee(int employeeId, Employee updatedEmployee);
    String deleteEmployee(int employeeId);
}
