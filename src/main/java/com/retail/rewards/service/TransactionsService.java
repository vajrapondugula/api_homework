package com.retail.rewards.service;

import com.retail.rewards.entity.Transaction;

import java.util.List;

public interface TransactionsService {
    List<Transaction> getAllTransactions();
}
