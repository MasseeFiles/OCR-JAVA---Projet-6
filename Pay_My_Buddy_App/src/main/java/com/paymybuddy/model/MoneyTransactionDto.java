package com.paymybuddy.model;

import lombok.Data;

@Data
public class MoneyTransactionDto {
    private String nameContactDto;
//    private String giverEmail;
//    private String receiverEmail;
    private float transferAmount;
}
