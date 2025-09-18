package com.emanuelkukec.mybank.springboot.web;

import com.emanuelkukec.mybank.springboot.dto.TransactionDto;
import com.emanuelkukec.mybank.springboot.model.Transaction;
import com.emanuelkukec.mybank.springboot.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController {
    private TransactionService transactionService;

    public BankController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> transactions() {
        return transactionService.findAll();
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody @Valid TransactionDto transactionDto) {
        return transactionService.create(transactionDto.getAmount(), transactionDto.getReference(), transactionDto.getReceivingUserId());
    }
}
