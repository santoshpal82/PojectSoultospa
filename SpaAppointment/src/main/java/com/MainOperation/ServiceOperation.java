package com.MainOperation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import com.entity.Servicee; // Ensure this import matches your package structure
import com.util.HibernateUtil;

public class ServiceOperation {
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

            // Create a new Servicee object
            Servicee service = new Servicee(1, "Haircut", "A stylish haircut.", 15.99, 30);

            // Save the service to the database
            session.save(service);

            // Commit the transaction
            transaction.commit();

            // Retrieve the service from the database
            Servicee retrievedService = session.get(Servicee.class, service.getServiceId());
            // Display the retrieved service
            System.out.println(retrievedService);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Log the exception
        } finally {
            if (session != null) session.close();
            factory.close();
        }
    }
}
