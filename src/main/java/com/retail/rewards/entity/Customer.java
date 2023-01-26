package com.retail.rewards.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<Transaction> transactions;

    private long rewardPoints;

    public Customer(){}

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public long getRewardPoints() {
        // Consider only last 90 days of transactions
        return transactions.stream()
            .filter(t -> TimeUnit.DAYS.convert(new Date().getTime() - t.getTransactionDate().getTime(), TimeUnit.MILLISECONDS) <= 90)
            .map(t -> {
                // Calculate rewards for each transaction
                if(t.getAmount() > 100) {
                    return 50 + 2 * (t.getAmount() - 100);
                } else if(t.getAmount() > 50) {
                    return t.getAmount() - 50;
                } else {
                    return 0L;
                }
            })
            // Sum up rewards from all transactions
            .reduce(0L, Long::sum);
    }

    public void setRewardPoints(long rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
