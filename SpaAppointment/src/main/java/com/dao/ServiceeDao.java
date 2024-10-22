package com.dao;

import java.util.List;
import com.entity.Servicee;

public interface ServiceeDao {

    Servicee createServicee(Servicee servicee);
    List<Servicee> getAllServices();
    Servicee getServiceeById(int serviceId);
    Servicee updateServicee(int serviceId, Servicee updatedServicee);
    String deleteServicee(int serviceId);
}
