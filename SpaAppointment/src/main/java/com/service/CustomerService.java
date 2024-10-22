package com.service;

import java.util.List;
import com.entity.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int customerId);
    Customer updateCustomer(int customerId, Customer updatedCustomer);
    String deleteCustomer(int customerId);
}
