package com.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.ServiceeDao;
import com.entity.Servicee;
import com.exception.ResourceNotFoundException;
import com.util.HibernateUtil;

public class ServiceeDaoImpl implements ServiceeDao {

    @Override
    public Servicee createServicee(Servicee servicee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(servicee);
            transaction.commit();
            return servicee;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error creating service: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Servicee> getAllServices() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Servicee> query = session.createQuery("FROM Servicee", Servicee.class);
            return query.list();
        } catch (HibernateException e) {
            System.err.println("Error retrieving services: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Servicee getServiceeById(int serviceId) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Servicee.class, serviceId);
        } catch (HibernateException e) {
            System.err.println("Error retrieving service: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Servicee updateServicee(int serviceId, Servicee updatedServicee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Servicee servicee = session.get(Servicee.class, serviceId);
            if (servicee != null) {
                servicee.setServiceName(updatedServicee.getServiceName());
                servicee.setDescription(updatedServicee.getDescription());
                servicee.setPrice(updatedServicee.getPrice());
                servicee.setDuration(updatedServicee.getDuration());
                session.update(servicee); // Use update instead of saveOrUpdate
                transaction.commit();
                return servicee;
            } else {
                throw new ResourceNotFoundException("Service not found with ID: " + serviceId);
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating service: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteServicee(int serviceId) {
        Transaction transaction = null;
        String message;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Servicee servicee = session.get(Servicee.class, serviceId);
            if (servicee != null) {
                session.delete(servicee);
                transaction.commit();
                message = "Service deleted successfully";
            } else {
                message = "Service not found with ID: " + serviceId;
                transaction.rollback(); // Rollback if the service was not found
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            message = "Error deleting service: " + e.getMessage();
            System.err.println(message);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Unexpected error: " + e.getMessage();
            System.err.println(message);
        }
        return message;
    }
}
