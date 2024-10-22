package com.serviceImpl;

import java.util.List;

import com.dao.ServiceeDao;
import com.daoImpl.ServiceeDaoImpl;
import com.entity.Servicee;
import com.service.ServiceeService;

public class ServiceeServiceImpl implements ServiceeService {

    ServiceeDao serviceeDao = new ServiceeDaoImpl();

    @Override
    public Servicee createServicee(Servicee servicee) {
        // Invoke dao method to save service object
        return serviceeDao.createServicee(servicee);
    }

    @Override
    public List<Servicee> getAllServices() {
        // Retrieve all services
        return serviceeDao.getAllServices();
    }

    @Override
    public Servicee getServiceeById(int serviceId) {
        // Retrieve service by ID
        return serviceeDao.getServiceeById(serviceId);
    }

    @Override
    public Servicee updateServicee(int serviceId, Servicee updatedServicee) {
        // Update service details
        return serviceeDao.updateServicee(serviceId, updatedServicee);
    }

    @Override
    public String deleteServicee(int serviceId) {
        // Delete service by ID
        return serviceeDao.deleteServicee(serviceId);
    }
}
