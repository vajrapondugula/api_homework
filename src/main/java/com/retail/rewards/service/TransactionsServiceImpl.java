package com.retail.rewards.service;

import com.retail.rewards.entity.Transaction;
import com.retail.rewards.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionRepository repository;
    private SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    @PostConstruct
    public void transactionDataSetup() throws ParseException {
        // adding transactions in H2 DB
        repository.save(new Transaction(sm.parse("01-11-2022 20:20"), 120L, 1L));
        repository.save(new Transaction(sm.parse("11-12-2022 20:20"), 120L, 1L));
        repository.save(new Transaction(sm.parse("20-08-2022 20:20"), 120L, 1L));
        repository.save(new Transaction(sm.parse("01-01-2023 20:20"), 40L, 2L));
        repository.save(new Transaction(sm.parse("02-01-2023 20:20"), 80L, 3L));
        repository.save(new Transaction(sm.parse("03-01-2023 20:20"), 100L, 4L));
        repository.save(new Transaction(sm.parse("04-01-2023 20:20"), 250L, 5L));
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }
}
