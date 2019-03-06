package com.imokhonko.dao;

import com.imokhonko.entities.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getCustomers();

    Customer saveCustomer(Customer customer);

    Customer getCustomer(int customerId);

    void deleteCustomer(int customerId);

}
