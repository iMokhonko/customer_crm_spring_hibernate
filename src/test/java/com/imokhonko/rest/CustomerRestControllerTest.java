package com.imokhonko.rest;

import com.imokhonko.entities.Customer;
import com.imokhonko.exceptions.CustomerNotFoundException;
import com.imokhonko.rest.exceptions.RestErrorResponse;
import com.imokhonko.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerRestControllerTest {

    CustomerRestController customerRestController = null;
    CustomerService customerService = null;

    @Before
    public void setUp() {
        customerService = mock(CustomerService.class);
        customerRestController = new CustomerRestController(customerService);
    }

    @Test
    public void getCustomers() {
        when(customerService.getCustomers()).thenReturn(Collections.emptyList());

        List<Customer> customers = customerRestController.getCustomers();

        assertThat(customers).isEqualTo(Collections.emptyList());
        verify(customerService, times(1)).getCustomers();
    }

    @Test
    public void getCustomersById_WithExistedCustomer() {
        Customer expectedCustomer = mock(Customer.class);

        when(customerService.getCustomer(1)).thenReturn(expectedCustomer);

        Customer actualCustomer = customerRestController.getCustomersById(1);

        verify(customerService, times(1)).getCustomer(1);
        assertThat(expectedCustomer).isEqualTo(actualCustomer);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void getCustomersById_CustomerWithGivenIdNotFound() {
        when(customerService.getCustomer(1)).thenReturn(null);
        customerRestController.getCustomersById(1);
    }

    @Test
    public void addCustomer() {
        Customer customer = mock(Customer.class);

        Customer newCustomer = customerRestController.addCustomer(customer);

        verify(customer, times(1)).setCustomerId(0);
        verify(customerService,times(1)).saveCustomer(customer);
    }

    @Test
    public void updateCustomer() {
        Customer customer = mock(Customer.class);

        customerRestController.updateCustomer(customer);

        verify(customerService, times(1)).saveCustomer(customer);
    }

    @Test
    public void deleteCustomer_SuccessfullyDeleteCustomer() {
        Customer customer = mock(Customer.class);

        when(customerService.getCustomer(0)).thenReturn(customer);
        verify(customerService, times(0)).deleteCustomer(0);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void deleteCustomer_CustomerWithGivenIdNotExist() {
        when(customerService.getCustomer(1)).thenReturn(null);

        customerRestController.deleteCustomer(1);
    }

    @Test
    public void handleBadStudentId() {
        CustomerNotFoundException customerNotFoundException = new CustomerNotFoundException();

        ResponseEntity<RestErrorResponse> restErrorResponseResponseEntity =
                customerRestController.handleBadStudentId(customerNotFoundException);

        assertThat(restErrorResponseResponseEntity).isInstanceOf(ResponseEntity.class);
    }
}