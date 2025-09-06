package com.emanuelkukec.mybank.service;

import com.emanuelkukec.mybank.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class TransactionService {

    private String bankSlogan;

    List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public TransactionService(@Value("${bank.slogan}")  String bankSlogan) {
        this.bankSlogan = bankSlogan;
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public Transaction create(BigDecimal amount, String reference, String receivingUserId) {
        Transaction transaction = new Transaction(amount, ZonedDateTime.now(), reference, bankSlogan, receivingUserId);
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> findByUserId(String userId) {
        return transactions.stream().filter(transaction -> transaction.getId().equals(userId)).collect(Collectors.toList());
    }
}
