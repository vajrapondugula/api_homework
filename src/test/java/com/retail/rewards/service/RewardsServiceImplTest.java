package com.retail.rewards.service;

import com.retail.rewards.entity.Customer;
import com.retail.rewards.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class RewardsServiceImplTest {

    @InjectMocks
    RewardsServiceImpl service;

    @Mock
    CustomerRepository repository;

    @Test
    void testGetCustomerById() {
//        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(new Customer())
    }

}