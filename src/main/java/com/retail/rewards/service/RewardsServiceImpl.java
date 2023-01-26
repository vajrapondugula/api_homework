package com.retail.rewards.service;

import com.retail.rewards.entity.Customer;
import com.retail.rewards.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    private CustomerRepository repository;

    @PostConstruct
    public void customerDataSetup() {
        // Data setup for the customers
        repository.save(new Customer(1L, "Noah"));
        repository.save(new Customer(2L, "Michael"));
        repository.save(new Customer(3L, "Ethan"));
        repository.save(new Customer(4L, "Mason"));
        repository.save(new Customer(5L, "Samuel"));
        repository.save(new Customer(6L, "John"));
    }

    @Override
    public Customer getCustomerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }
}
