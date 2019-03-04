package com.imokhonko.dao;

import com.imokhonko.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CustomerDaoImplTest {

    private SessionFactory sessionFactory = null;
    CustomerDAO customerDAO = null;

    private Session session = null;

    @Before
    public void setUp() {
        sessionFactory = mock(SessionFactory.class);
        customerDAO = new CustomerDaoImpl(sessionFactory);

        session = mock(Session.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void getCustomers() {
        Query<Customer> query = mock(Query.class);

        when(session.createQuery("from Customer", Customer.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        assertThat(customerDAO.getCustomers()).isInstanceOf(List.class);
        verify(query, times(1)).getResultList();
    }

    @Test
    public void saveCustomer() {
        Customer customer = mock(Customer.class);
        customerDAO.saveCustomer(customer);

        verify(session, times(1)).saveOrUpdate(customer);
    }

    @Test
    public void getCustomer() {
        customerDAO.getCustomer(1);
        verify(session, times(1)).get(Customer.class, 1);
    }

    @Test
    public void deleteCustomer() {
        Query query = mock(Query.class);

        when(session.createQuery("delete from Customer customer where customer.customerId = :customerId")).thenReturn(query);

        customerDAO.deleteCustomer(1);
        verify(query, times(1)).executeUpdate();
    }
}