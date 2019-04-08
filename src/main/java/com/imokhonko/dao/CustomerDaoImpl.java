package com.imokhonko.dao;

import com.imokhonko.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate specific CustomerDAO implementation.
 */
@Repository
public class CustomerDaoImpl implements CustomerDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("from Customer", Customer.class);
        List<Customer> customers = query.getResultList();

        return customers;
    }

    /**
     * Saves the customer to db if customer_id is zero or null (not setted).
     * Updates the customer if customer_id is not zero or null (is setted).
     * @param customer customer entity to save or update
     * @return saved customer
     */
    public Customer saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);

        return customer;
    }

    public Customer getCustomer(int customerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Customer.class, customerId);
    }

    public void deleteCustomer(int customerId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Customer customer where customer.customerId = :customerId");
        query.setParameter("customerId", customerId);
        query.executeUpdate();
    }
}
