package com.imokhonko.rest;

import com.imokhonko.entities.Customer;
import com.imokhonko.exceptions.CustomerNotFoundException;
import com.imokhonko.rest.exceptions.RestErrorResponse;
import com.imokhonko.rest.filters.CustomerFilter;
import com.imokhonko.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = {"application/xml", "application/json", "text/html"})
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    public Customer getCustomersById(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);

        if(customer == null) {
            throw new CustomerNotFoundException("Customer with id = " + customerId + " not found!");
        }

        return customer;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setCustomerId(0);
        customerService.saveCustomer(customer);

        return customer;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    public Customer updateCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);

        return customer;
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    public String deleteCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);
        if(customer == null) {
            throw new CustomerNotFoundException("Customer with id" + customerId + " is not exist!");
        }

        customerService.deleteCustomer(customerId);

        return "Customer with id = " + customerId + " deleted!";
    }

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleBadStudentId(CustomerNotFoundException exception) {
        RestErrorResponse errorResponse = new RestErrorResponse();

        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
