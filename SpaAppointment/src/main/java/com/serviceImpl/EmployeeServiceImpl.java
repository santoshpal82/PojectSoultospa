package com.serviceImpl;

import java.util.List;

import com.dao.EmployeeDao;
import com.daoImpl.EmployeeDaoImpl;
import com.entity.Employee;
import com.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public Employee createEmployee(Employee employee) {
        // Invoke dao method to save employee object
        return employeeDao.createEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        // Retrieve all employees
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        // Retrieve employee by ID
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee updatedEmployee) {
        // Update employee details
        return employeeDao.updateEmployee(employeeId, updatedEmployee);
    }

    @Override
    public String deleteEmployee(int employeeId) {
        // Delete employee by ID
        return employeeDao.deleteEmployee(employeeId);
    }
}
