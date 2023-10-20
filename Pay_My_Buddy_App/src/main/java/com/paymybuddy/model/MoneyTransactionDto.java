package com.paymybuddy.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MoneyTransactionDto {
    private String contactIdEmbeddedIdOtherEmail;
    private float transferAmount;

    public MoneyTransactionDto(String contactIdEmbeddedIdOtherEmail, float transferAmount) {
        this.contactIdEmbeddedIdOtherEmail = contactIdEmbeddedIdOtherEmail;
        this.transferAmount = transferAmount;
    }
}
