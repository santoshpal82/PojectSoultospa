package com.serviceImpl;

import java.util.List;

import org.hibernate.Session;

import com.entity.Appointment;
import com.dao.AppointmentDao;
import com.daoImpl.AppointmentDaoImpl;
import com.util.HibernateUtil;
import com.service.AppointmentService;

public class AppointmentServiceImpl implements AppointmentService {

    AppointmentDao appointmentDao = new AppointmentDaoImpl();

    @Override
    public Appointment createAppointment(Appointment appointment) {
        // Invoke dao method to save the appointment object
        return appointmentDao.createAppointment(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        // Retrieve all appointments
        return appointmentDao.getAllAppointments();
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        // Retrieve an appointment by ID
        Session session = HibernateUtil.getSessionFactory().openSession();
        Appointment appointment = session.get(Appointment.class, appointmentId);
        System.out.println("Appointment ID queried: " + appointmentId);
        System.out.println("Retrieved Appointment: " + appointment);
        session.close();
        return appointment;
    }

    @Override
    public Appointment updateAppointment(int appointmentId, Appointment updatedAppointment) {
        // Update an existing appointment
        return appointmentDao.updateAppointment(appointmentId, updatedAppointment);
    }

    @Override
    public String deleteAppointment(int appointmentId) {
        // Delete an appointment by ID
        return appointmentDao.deleteAppointment(appointmentId);
    }
}
