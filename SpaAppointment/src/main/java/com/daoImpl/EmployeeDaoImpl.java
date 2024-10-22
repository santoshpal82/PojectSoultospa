package com.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.EmployeeDao;
import com.entity.Employee;
import com.exception.ResourceNotFoundException;
import com.util.HibernateUtil;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public Employee createEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            return employee;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error creating employee: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            return query.list();
        } catch (HibernateException e) {
            System.err.println("Error retrieving employees: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Employee.class, employeeId);
        } catch (HibernateException e) {
            System.err.println("Error retrieving employee: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee updatedEmployee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                employee.setEmployeeName(updatedEmployee.getEmployeeName());
                employee.setEmail(updatedEmployee.getEmail());
                employee.setPhoneNumber(updatedEmployee.getPhoneNumber());
                employee.setPosition(updatedEmployee.getPosition());
                session.update(employee);
                transaction.commit();
                return employee;
            } else {
                throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating employee: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteEmployee(int employeeId) {
        Transaction transaction = null;
        String message;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                session.delete(employee);
                transaction.commit();
                message = "Employee deleted successfully";
            } else {
                message = "Employee not found with ID: " + employeeId;
                transaction.rollback();
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            message = "Error deleting employee: " + e.getMessage();
            System.err.println(message);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Unexpected error: " + e.getMessage();
            System.err.println(message);
        }
        return message;
    }
}
