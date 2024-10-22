package com.dao;

import java.util.List;
import com.entity.Customer;

public interface CustomerDao {

    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int customerId);
    Customer updateCustomer(int customerId, Customer updatedCustomer);
    String deleteCustomer(int customerId);
}
