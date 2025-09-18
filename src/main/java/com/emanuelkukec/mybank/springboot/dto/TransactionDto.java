package com.emanuelkukec.mybank.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransactionDto {
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String reference;

    @NotBlank
    private String receivingUserId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReceivingUserId() {
        return receivingUserId;
    }

    public void setReceivingUserId(String receivingUserId) {
        this.receivingUserId = receivingUserId;
    }
}
