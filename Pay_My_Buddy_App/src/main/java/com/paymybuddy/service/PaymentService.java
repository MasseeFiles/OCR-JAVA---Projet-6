package com.paymybuddy.service;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;

public class PaymentService {

    public boolean verifyBalance(MoneyTransaction moneyTransaction) {
        boolean paymentPossible;
        float moneyTransferAmount = moneyTransaction.getAmount();

        User userToCheck = new User();
        float balanceUser = userToCheck.getBalance();

        User userReceiver = new User();
        float balanceReceiver = userReceiver.getBalance();

        if (moneyTransferAmount < balanceUser) {
            paymentPossible = true;
            float newBalanceUser = balanceUser - moneyTransferAmount;


            float newBalanceReceiver = balanceReceiver + moneyTransferAmount;

// persister la moneyTransaction

        } else {
            paymentPossible = false;
        }
        return paymentPossible;
    }

    public float calculateFee(MoneyTransaction moneyTransaction) {
        return moneyTransaction.getAmount()*0.05f;
    }
}
