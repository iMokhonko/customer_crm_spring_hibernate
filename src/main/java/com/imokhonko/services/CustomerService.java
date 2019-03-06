package com.imokhonko.services;

import com.imokhonko.entities.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    Customer saveCustomer(Customer customer);

    Customer getCustomer(int customerId);

    void deleteCustomer(int customerId);

}
