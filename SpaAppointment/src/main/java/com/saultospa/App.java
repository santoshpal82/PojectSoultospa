/*package com.saultospa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.util.HibernateUtil;
import com.entity.Customer;
import com.entity.Servicee;
import com.entity.Employee;
import com.entity.Appointment;

public class App {
    public static void main(String[] args) {
        // Obtain a Hibernate SessionFactory
        SessionFactory factory = HibernateUtil.getSessionFactory();
        
        // Open a new session
        Session session = factory.openSession();
        // Begin a transaction
        Transaction transaction = session.beginTransaction();

        try {
            // Create Customer
            Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", "123 Main St");
            session.save(customer); // Save customer

            // Create Servicee
            Servicee service = new Servicee(1, "Consultation", "General consultation", 100.0, 30);
            session.save(service); // Save service

            // Create Employee
            Employee employee = new Employee(1, "Jane Smith", "jane@example.com", "0987654321", "Consultant");
            session.save(employee); // Save employee

            // Create Appointment
            Appointment appointment = new Appointment(1, customer, service, employee, "2024-10-20 10:00:00", "Scheduled");
            session.save(appointment); // Save appointment

            // Commit the transaction
            transaction.commit();

            // Output confirmation
            System.out.println("Customer, Service, Employee, and Appointment created successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback in case of error
            e.printStackTrace(); // Log the exception
        } finally {
            // Close the Session
            session.close();
            // Close the Session Factory
            factory.close();
        }
    }
}
*/
