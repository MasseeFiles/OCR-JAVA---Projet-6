package com.paymybuddy.service;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.repository.MoneyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransactionService {

    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;

    public Iterable<MoneyTransaction> getMoneyTransactions() {
        return moneyTransactionRepository.findAll();
    }

    public void addMoneyTransaction(MoneyTransaction moneyTransactionToAdd) {
        MoneyTransactionRepository.save(moneyTransactionToAdd);
    }

/*
//    public void updateMoneyTransaction(MoneyTransaction moneyTransactionToUpdate) {
//        MoneyTransactionRepository.save(moneyTransactionToUpdate);
//    }

    public void deleteMoneyTransaction(MoneyTransaction moneyTransactionToDelete) {
        moneyTransactionRepository.delete(moneyTransactionToDelete);
    }
*/

}

