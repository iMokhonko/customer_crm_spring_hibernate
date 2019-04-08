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

/**
 * RestController for Customer entity.
 * Responses in JSON format.
 */
@RestController
@RequestMapping(value = "/api")
public class CustomerRestController {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * @return List of customers
     */
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    /**
     * Retrieves customer with given id from database, if customer not found throws
     * CustomerNotFoundException exception.
     * @param customerId customer identified in database
     * @return customer
     */
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    public Customer getCustomersById(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);

        if(customer == null) {
            throw new CustomerNotFoundException("Customer with id = " + customerId + " not found!");
        }

        return customer;
    }

    /**
     * Receives HTTP PUT request with JSON representation of customer and saves it to database.
     * @param customer
     * @return saved customer
     */
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setCustomerId(0);
        customerService.saveCustomer(customer);

        return customer;
    }

    /**
     * Receives HTTP PUT request with JSON representation of customer and updates it to database.
     * @param customer
     * @return updated customer
     */
    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    public Customer updateCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);

        return customer;
    }

    /**
     * Removes customer with given id from database, if customer not found throws
     * CustomerNotFoundException exception.
     * @param customerId customer identified in database
     * @return String message
     */
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    public String deleteCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);

        if(customer == null) {
            throw new CustomerNotFoundException("Customer with id" + customerId + " is not exist!");
        }

        customerService.deleteCustomer(customerId);

        return "Customer with id = " + customerId + " deleted!";
    }

    /**
     * Catches exceptions and send formatted HTTP response with code 404 (NOT_FOUND).
     * @param exception occurred exception
     * @return ResponseEntity
     */
    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleBadStudentId(CustomerNotFoundException exception) {
        RestErrorResponse errorResponse = new RestErrorResponse();

        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
