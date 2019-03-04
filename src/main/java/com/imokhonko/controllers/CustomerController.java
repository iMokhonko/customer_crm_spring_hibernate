package com.imokhonko.controllers;

import com.imokhonko.entities.Customer;
import com.imokhonko.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String customersList(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customersList", customers);

        return "customers-list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCustomerForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "add-customer-form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddCustomerForm(@Valid @ModelAttribute Customer customer,
                                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "add-customer-form";

        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editCustomerForm(@RequestParam("customerId") int customerId, Model model) {
        Customer customer = customerService.getCustomer(customerId);
        model.addAttribute("customer", customer);

        return "edit-customer-form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processEditCustomerForm(@Valid @ModelAttribute Customer customer,
                                          BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "edit-customer-form";

        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCustomer(@RequestParam("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
        return "redirect:/customer/list";
    }

}
