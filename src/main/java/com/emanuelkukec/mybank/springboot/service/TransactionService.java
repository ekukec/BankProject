package com.emanuelkukec.mybank.springboot.service;

import com.emanuelkukec.mybank.springboot.model.Transaction;
import com.emanuelkukec.mybank.springboot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Component
public class TransactionService {

    private String bankSlogan;

    private TransactionRepository transactionRepository;

    public TransactionService(@Value("${bank.slogan}")  String bankSlogan, TransactionRepository transactionRepository) {
        this.bankSlogan = bankSlogan;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Iterable<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Transactional
    public Transaction create(BigDecimal amount, String reference, String receivingUserId) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTimestamp(ZonedDateTime.now());
        transaction.setReference(reference);
        transaction.setSlogan(bankSlogan);
        transaction.setReceivingUserId(receivingUserId);

        return transactionRepository.save(transaction);
    }

    @Transactional
    public Iterable<Transaction> findByReceivingUserId(String userId) {
        return transactionRepository.findByReceivingUserId(userId);
    }
}
