package com.MainOperation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import com.entity.Employee; // Ensure this import matches your package structure
import com.util.HibernateUtil;

public class EmployeeOperation {
    public static void main(String[] args) {
        // Obtain a Hibernate SessionFactory
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            // Open a new session
            session = factory.openSession();
            // Begin a transaction
            transaction = session.beginTransaction();

            // Create a new Employee object
            Employee employee = new Employee(1, "John Doe", "john.doe@example.com", "1234567890", "Manager");

            // Save the employee to the database
            session.save(employee);

            // Commit the transaction
            transaction.commit();

            // Retrieve the employee from the database
            Employee retrievedEmployee = session.get(Employee.class, employee.getEmployeeId());
            // Display the retrieved employee
            System.out.println(retrievedEmployee);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Log the exception
        } finally {
            if (session != null) session.close();
            factory.close();
        }
    }
}
