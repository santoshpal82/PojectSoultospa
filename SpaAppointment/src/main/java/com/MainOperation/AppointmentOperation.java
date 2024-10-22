package com.MainOperation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import com.entity.Appointment;
import com.entity.Customer;  // Assuming Customer class exists
import com.entity.Servicee;  // Assuming Servicee class exists
import com.entity.Employee;  // Assuming Employee class exists
import com.util.HibernateUtil;

public class AppointmentOperation {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            // Create or retrieve Customer, Servicee, and Employee objects
            Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", "123 Main St");
            Servicee service = new Servicee(1, "Consultation", "General consultation", 100.0, 30);
            Employee employee = new Employee(1, "Jane Smith", "jane@example.com", "0987654321", "Consultant");

            // Ensure they are saved if not already
            session.save(customer);
            session.save(service);
            session.save(employee);

            // Create an Appointment object and associate the customer, service, and employee with it
            Appointment appointment = new Appointment(1, customer, service, employee, "2024-10-20 10:00:00", "Scheduled");

            // Save the appointment to the database
            session.save(appointment);

            transaction.commit();

            // Retrieve the appointment from the database
            Appointment retrievedAppointment = session.get(Appointment.class, appointment.getAppointmentId());
            System.out.println(retrievedAppointment);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        // Manage factory at application shutdown
    }
}
