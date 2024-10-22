package com.entity;

import javax.persistence.*;

@Entity
public class Appointment {
    @Id
    private int appointmentId;

    @ManyToOne // Assuming each appointment is associated with one customer
    @JoinColumn(name = "customer_id") // Foreign key column name in the Appointment table
    private Customer customer;

    @ManyToOne // Assuming each appointment is associated with one service
    @JoinColumn(name = "service_id") // Foreign key column name in the Appointment table
    private Servicee service;

    @ManyToOne // Assuming each appointment is associated with one employee
    @JoinColumn(name = "employee_id") // Foreign key column name in the Appointment table
    private Employee employee;

    private String appointmentDate; // You can change this to LocalDateTime if preferred
    private String status;

    // Default constructor
    public Appointment() {}

    // Constructor with parameters
    public Appointment(int appointmentId, Customer customer, Servicee service, Employee employee, String appointmentDate, String status) {
        this.appointmentId = appointmentId;
        this.customer = customer;
        this.service = service;
        this.employee = employee;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

    // Getters and setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Servicee getService() {
        return service;
    }

    public void setService(Servicee service) {
        this.service = service;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", customer=" + customer.getCustomerName() 
                + ", service=" + service.getServiceName() + ", employee=" + employee.getEmployeeName() 
                + ", appointmentDate=" + appointmentDate + ", status=" + status + "]";
    }
}
