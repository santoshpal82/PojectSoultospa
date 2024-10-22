package com.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.AppointmentDao;
import com.entity.Appointment;
import com.exception.ResourceNotFoundException;
import com.util.HibernateUtil;

public class AppointmentDaoImpl implements AppointmentDao {

    @Override
    public Appointment createAppointment(Appointment appointment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(appointment);
            transaction.commit();
            return appointment;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error creating appointment: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Appointment> query = session.createQuery("FROM Appointment", Appointment.class);
            return query.list();
        } catch (HibernateException e) {
            System.err.println("Error retrieving appointments: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        try (Session session = HibernateUtil.getSession()) {
            Appointment appointment = session.get(Appointment.class, appointmentId);
            if (appointment == null) {
                throw new ResourceNotFoundException("Appointment ID not found: " + appointmentId);
            }
            return appointment;
        } catch (HibernateException e) {
            System.err.println("Error retrieving appointment: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Appointment updateAppointment(int appointmentId, Appointment updatedAppointment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appointmentId);
            if (appointment != null) {
                appointment.setCustomer(updatedAppointment.getCustomer());
                appointment.setService(updatedAppointment.getService());
                appointment.setEmployee(updatedAppointment.getEmployee());
                appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
                appointment.setStatus(updatedAppointment.getStatus());
                session.update(appointment); // Use update instead of saveOrUpdate
                transaction.commit();
                return appointment;
            } else {
                throw new ResourceNotFoundException("Appointment ID not found: " + appointmentId);
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating appointment: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteAppointment(int appointmentId) {
        Transaction transaction = null;
        String message;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appointmentId);
            if (appointment != null) {
                session.delete(appointment);
                transaction.commit();
                message = "Appointment with ID " + appointmentId + " is deleted";
            } else {
                throw new ResourceNotFoundException("Appointment ID not found: " + appointmentId);
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            message = "Error deleting appointment: " + e.getMessage();
            System.err.println(message);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Unexpected error: " + e.getMessage();
            System.err.println(message);
        }
        return message;
    }
}
