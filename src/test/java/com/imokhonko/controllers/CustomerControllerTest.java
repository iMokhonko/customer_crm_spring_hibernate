package com.imokhonko.controllers;

import com.imokhonko.entities.Customer;
import com.imokhonko.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    private CustomerService customerService = null;
    private CustomerController customerController = null;
    private Model model = null;

    @Before
    public void setUp() {
        customerService = mock(CustomerService.class);
        customerController = new CustomerController(customerService);
        model = mock(Model.class);
    }

    @Test
    public void customersList() {
        when(customerService.getCustomers()).thenReturn(null);

        model = mock(Model.class);

        assertEquals("customers-list", customerController.customersList(model));
        List<Customer> customers = verify(customerService, times(1)).getCustomers();
        verify(model, times(1)).addAttribute("customersList", customers);
    }

    @Test
    public void addCustomerForm() {
        assertEquals("add-customer-form", customerController.addCustomerForm(model));
    }

    @Test
    public void processAddCustomerForm_HappyPath() {
        Customer customer = mock(Customer.class);
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(false);

        assertEquals("redirect:/customer/list", customerController.processAddCustomerForm(customer, bindingResult));
        verify(customerService, times(1)).saveCustomer(customer);
    }

    @Test
    public void processAddCustomerForm_HasErrors() {
        Customer customer = mock(Customer.class);
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(true);

        assertEquals("add-customer-form", customerController.processAddCustomerForm(customer, bindingResult));
        verify(customerService, times(0)).saveCustomer(customer);
    }

    @Test
    public void editCustomerForm() {
        Customer customer = mock(Customer.class);
        when(customerService.getCustomer(1)).thenReturn(customer);

        assertEquals("edit-customer-form", customerController.editCustomerForm(1, model));
        verify(model).addAttribute("customer", customer);
    }

    @Test
    public void processEditCustomerForm_HappyPath() {
        Customer customer = mock(Customer.class);
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(false);

        assertEquals("redirect:/customer/list", customerController.processEditCustomerForm(customer, bindingResult));
        verify(customerService, times(1)).saveCustomer(customer);
    }

    @Test
    public void processEditCustomerForm_HasErrors() {
        Customer customer = mock(Customer.class);
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(true);

        assertEquals("edit-customer-form", customerController.processEditCustomerForm(customer, bindingResult));
        verify(customerService, times(0)).saveCustomer(customer);
    }

    @Test
    public void deleteCustomer() {
        assertEquals("redirect:/customer/list", customerController.deleteCustomer(1));
        verify(customerService, times(1)).deleteCustomer(1);
    }
}