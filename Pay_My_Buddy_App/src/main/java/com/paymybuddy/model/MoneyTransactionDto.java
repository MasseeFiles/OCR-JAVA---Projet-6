package com.paymybuddy.model;

import lombok.Data;

@Data
public class MoneyTransactionDto {
    private String contactEmbeddedIdOtherEmail;
    private float transferAmount;
}
