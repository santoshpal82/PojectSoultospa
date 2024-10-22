package com.MainOperation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import com.entity.Customer;
import com.util.HibernateUtil;

public class CustomerOperation {
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

            // Create a new Customer object
            Customer customer = new Customer(1, "Aditi", "aditi@gmail.com", "1234567896", "Kelwe");

            // Save the customer to the database
            session.save(customer);

            // Commit the transaction
            transaction.commit();

            // Retrieve the customer from the database
            Customer retrievedCustomer = session.get(Customer.class, customer.getCustomerId());
            // Display the retrieved customer
            System.out.println(retrievedCustomer);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Log the exception
        } finally {
            if (session != null) session.close();
            factory.close();
        }
    }
}
