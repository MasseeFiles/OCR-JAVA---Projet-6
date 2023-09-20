package com.paymybuddy.model;

import lombok.Data;

@Data
public class MoneyTransactionDto {
    private String contactIdEmbeddedIdOtherEmail;
    private float transferAmount;
}
