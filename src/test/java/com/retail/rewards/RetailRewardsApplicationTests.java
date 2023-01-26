package com.retail.rewards;

import com.retail.rewards.controller.RewardsController;
import com.retail.rewards.controller.TransactionsController;
import com.retail.rewards.entity.Customer;
import com.retail.rewards.entity.CustomerList;
import com.retail.rewards.entity.Transaction;
import com.retail.rewards.entity.TransactionList;
import com.retail.rewards.repository.CustomerRepository;
import com.retail.rewards.repository.TransactionRepository;
import com.retail.rewards.service.RewardsService;
import com.retail.rewards.service.RewardsServiceImpl;
import com.retail.rewards.service.TransactionsService;
import com.retail.rewards.service.TransactionsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.AssertionErrors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RetailRewardsApplicationTests {

	@Autowired
	private RewardsService rewardsServiceImpl;
	@Autowired
	private TransactionsService transactionsServiceImpl;

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	@Test
	void testRewardsController() throws ParseException {
		// Data setup
		customerDataSetup();

		TestRestTemplate testRestTemplate = new TestRestTemplate();

		// Existing customer - Amount more than 100
		ResponseEntity<Customer> res1 = testRestTemplate.getForEntity("http://localhost:8088/rewards/10", Customer.class);

		Assertions.assertNotNull(res1.getBody());
		AssertionErrors.assertEquals("Status Code did not match", HttpStatus.OK, res1.getStatusCode());
		AssertionErrors.assertEquals("Rewards point did not match", 180L, res1.getBody().getRewardPoints());

		// Existing customer - Amount less than 100
		ResponseEntity<Customer> res2 = testRestTemplate.getForEntity("http://localhost:8088/rewards/30", Customer.class);

		Assertions.assertNotNull(res2.getBody());
		AssertionErrors.assertEquals("Status Code did not match", HttpStatus.OK, res2.getStatusCode());
		AssertionErrors.assertEquals("Rewards point did not match", 30L, res2.getBody().getRewardPoints());

		// Existing customer - Amount less than 50
		ResponseEntity<Customer> res3 = testRestTemplate.getForEntity("http://localhost:8088/rewards/20", Customer.class);

		Assertions.assertNotNull(res3.getBody());
		AssertionErrors.assertEquals("Status Code did not match", HttpStatus.OK, res3.getStatusCode());
		AssertionErrors.assertEquals("Rewards point did not match", 0L, res3.getBody().getRewardPoints());

		// Non-Existing customer
		ResponseEntity<Customer> res4 = testRestTemplate.getForEntity("http://localhost:8088/rewards/100", Customer.class);

		Assertions.assertNull(res4.getBody());
		AssertionErrors.assertEquals("Status Code did not match", HttpStatus.NOT_FOUND, res4.getStatusCode());

		ResponseEntity<CustomerList> res5 = testRestTemplate.getForEntity("http://localhost:8088/rewards", CustomerList.class);

		Assertions.assertNotNull(res5.getBody());
		AssertionErrors.assertEquals("Count of customers did not match", 6, res5.getBody().getCustomers().size());
	}

	@Test
	void testTransactionsController() throws ParseException {
		// Data setup
		customerDataSetup();

		TestRestTemplate testRestTemplate = new TestRestTemplate();

		ResponseEntity<TransactionList> res = testRestTemplate.getForEntity("http://localhost:8088/transactions", TransactionList.class);

		Assertions.assertNotNull(res.getBody());
		AssertionErrors.assertEquals("Count of customers did not match", 7, res.getBody().getTransactions().size());
	}

	private void customerDataSetup() throws ParseException {
		// Clean existing data
		customerRepository.deleteAll();
		transactionRepository.deleteAll();

		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy hh:mm");

		// Customers setup
		customerRepository.save(new Customer(10L, "Noah"));
		customerRepository.save(new Customer(20L, "Michael"));
		customerRepository.save(new Customer(30L, "Ethan"));
		customerRepository.save(new Customer(40L, "Mason"));
		customerRepository.save(new Customer(50L, "Samuel"));
		customerRepository.save(new Customer(60L, "John"));

		// Transactions setup
		transactionRepository.save(new Transaction(sm.parse("01-11-2022 20:20"), 120L, 10L));
		transactionRepository.save(new Transaction(sm.parse("11-12-2022 20:20"), 120L, 10L));
		transactionRepository.save(new Transaction(sm.parse("20-08-2022 20:20"), 120L, 10L));
		transactionRepository.save(new Transaction(sm.parse("01-01-2023 20:20"), 40L, 20L));
		transactionRepository.save(new Transaction(sm.parse("02-01-2023 20:20"), 80L, 30L));
		transactionRepository.save(new Transaction(sm.parse("03-01-2023 20:20"), 100L, 40L));
		transactionRepository.save(new Transaction(sm.parse("04-01-2023 20:20"), 250L, 50L));
	}

}
