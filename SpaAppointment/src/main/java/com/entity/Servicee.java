package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Servicee {

    @Id // primary key
    @Column(name = "ServiceId", length = 10)
    private int serviceId;

    @Column(name = "ServiceName", length = 50)
    private String serviceName;

    @Column(name = "Description", length = 255)
    private String description;

    @Column(name = "Price")
    private double price;

    @Column(name = "Duration") // Duration in minutes
    private int duration;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Service [serviceId=" + serviceId + ", serviceName=" + serviceName + ", description=" + description 
                + ", price=" + price + ", duration=" + duration + "]";
    }

    public Servicee(int serviceId, String serviceName, String description, double price, int duration) {
        super();
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public Servicee() {
        super();
        // Default constructor
    }
}
