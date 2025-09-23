package com.emanuelkukec.mybank.springboot.web;

import com.emanuelkukec.mybank.springboot.dto.TransactionDto;
import com.emanuelkukec.mybank.springboot.model.Transaction;
import com.emanuelkukec.mybank.springboot.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankController {
    private TransactionService transactionService;

    public BankController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public Iterable<Transaction> transactions() {
        return transactionService.findAll();
    }

    @GetMapping("/transactions/{userId}")
    public Iterable<Transaction> transactions(@PathVariable String userId) {
        return transactionService.findByReceivingUserId(userId);
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody @Valid TransactionDto transactionDto) {
        return transactionService.create(transactionDto.getAmount(), transactionDto.getReference(), transactionDto.getReceivingUserId());
    }
}
