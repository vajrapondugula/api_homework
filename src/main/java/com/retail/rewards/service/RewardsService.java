package com.retail.rewards.service;

import com.retail.rewards.entity.Customer;

import java.util.List;

public interface RewardsService {

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();
}
