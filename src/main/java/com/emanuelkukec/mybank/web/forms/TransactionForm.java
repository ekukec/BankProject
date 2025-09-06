package com.emanuelkukec.mybank.web.forms;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class TransactionForm {
    @NotNull
    @DecimalMin("0.01")
    @Max(100)
    private BigDecimal amount;

    @NotBlank
    @Size(min = 1, max = 25)
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
