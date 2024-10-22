package com.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.CustomerDao;
import com.entity.Customer;
import com.exception.ResourceNotFoundException;
import com.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public Customer createCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            return customer;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error creating customer: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
            return query.list();
        } catch (HibernateException e) {
            System.err.println("Error retrieving customers: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Customer getCustomerById(int customerId) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Customer.class, customerId);
        } catch (HibernateException e) {
            System.err.println("Error retrieving customer: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Customer updateCustomer(int customerId, Customer updatedCustomer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, customerId);
            if (customer != null) {
                customer.setCustomerName(updatedCustomer.getCustomerName());
                customer.setEmail(updatedCustomer.getEmail());
                customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
                customer.setAddress(updatedCustomer.getAddress());
                session.update(customer); // Use update instead of saveOrUpdate
                transaction.commit();
                return customer;
            } else {
                throw new ResourceNotFoundException("Customer not found with ID: " + customerId);
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating customer: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteCustomer(int customerId) {
        Transaction transaction = null;
        String message;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, customerId);
            if (customer != null) {
                session.delete(customer);
                transaction.commit();
                message = "Customer deleted successfully";
            } else {
                message = "Customer not found with ID: " + customerId;
                transaction.rollback(); // Rollback if the customer was not found
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            message = "Error deleting customer: " + e.getMessage();
            System.err.println(message);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Unexpected error: " + e.getMessage();
            System.err.println(message);
        }
        return message;
    }
}
