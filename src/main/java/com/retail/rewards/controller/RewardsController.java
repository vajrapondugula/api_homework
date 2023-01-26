package com.retail.rewards.controller;

import com.retail.rewards.entity.Customer;
import com.retail.rewards.entity.CustomerList;
import com.retail.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    /**
     * Get the Customer details including reward points
     * @param customerId Id of the required customer
     * @return Customer details for matching customer id
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getRewardsForCustomer(@PathVariable Long customerId) {
        // Retrieve the Customer from H2 DB based on given customer id
        Customer customer = rewardsService.getCustomerById(customerId);

        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /**
     * Get all the Customer details including reward points
     * @return All Customer details
     */
    @GetMapping
    public ResponseEntity<CustomerList> getRewardsForAllCustomers() {
        // Retrieve details of all the customers
        List<Customer> customers = rewardsService.getAllCustomers();

        return new ResponseEntity<>(new CustomerList(customers), HttpStatus.OK);
    }
}
