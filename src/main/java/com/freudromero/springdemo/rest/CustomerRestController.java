package com.freudromero.springdemo.rest;

import com.freudromero.springdemo.entity.Customer;
import com.freudromero.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    // Autowire the CustomerService
    @Autowired
    private CustomerService customerService;

    // Add mapping for GET /customers
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    // Add mapping for GET /customers/{customerId}
    @GetMapping("customers/{customerId}")
    public Customer getCustomer (@PathVariable int customerId) {
        Customer theCustomer = customerService.getCustomer(customerId);

        if(theCustomer == null) {
           throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        return theCustomer;
    }

    // Adding a mapping for POST /customers - add new customer
    @PostMapping ("/customers")
    public Customer addCustomer (@RequestBody Customer theCustomer) {

        // Also just in case the pass an id in JSON ... set id to 0
        // this is force a save of new item ... instead of update.
        theCustomer.setId(0);

        customerService.saveCustomer(theCustomer);
       return theCustomer;
    }


}
