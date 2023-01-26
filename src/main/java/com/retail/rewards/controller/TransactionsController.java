package com.retail.rewards.controller;

import com.retail.rewards.entity.Transaction;
import com.retail.rewards.entity.TransactionList;
import com.retail.rewards.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionsController {

    @Autowired
    private TransactionsService service;

    /**
     * Get all the available transactions
     * @return All Transactions
     */
    @GetMapping("/transactions")
    public ResponseEntity<TransactionList> getTransactions() {
        // Retrieve all the transactions from H2 DB
        List<Transaction> transactionList = service.getAllTransactions();

        return new ResponseEntity<>(new TransactionList(transactionList), HttpStatus.OK);
    }
}
