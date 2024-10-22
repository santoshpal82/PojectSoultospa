package com.service;

import java.util.List;

import com.entity.Appointment;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(int appointmentId);
    Appointment updateAppointment(int appointmentId, Appointment updatedAppointment);
    String deleteAppointment(int appointmentId);
}
