package com.retail.rewards.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trans_ts")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date transactionDate;

    @Column(name = "trans_amount")
    private Long amount;

    @Column(name = "customer_id")
    private Long customerId;

    public Transaction(){}

    public Transaction(Date transDate, Long amount, Long custId) {
        this.transactionDate = transDate;
        this.amount = amount;
        this.customerId = custId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
