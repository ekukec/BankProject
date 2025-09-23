package com.emanuelkukec.mybank.springboot.repository;

import com.emanuelkukec.mybank.springboot.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,String> {
    Iterable<Transaction> findByReceivingUserId(String userId);
}
