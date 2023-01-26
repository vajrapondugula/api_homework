package com.retail.rewards.entity;

import java.util.List;

public class TransactionList {

    private List<Transaction> transactions;

    public TransactionList() {}

    public TransactionList(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
