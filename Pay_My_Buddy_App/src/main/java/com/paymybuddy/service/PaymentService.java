package com.paymybuddy.service;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PaymentService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;

    public boolean allowPayment(MoneyTransaction moneyTransaction, User giver) {
        boolean paymentAllowed;

        float transferAmount = moneyTransaction.getAmount();

        String giverEmail = moneyTransaction.getGiverEmail();
        Optional<User> optionalGiver = userRepository.findById(giverEmail);
        User giverToCheck = optionalGiver.get();
        float balanceGiver = giverToCheck.getBalance();

        if (transferAmount < balanceGiver) {    //verification du solde du donneur
            paymentAllowed = true;

            User giverToUpdate = optionalGiver.get();   //a voir si plutot utilisation de givertocheck
            float balanceGiverToUpdate = giverToUpdate.getBalance();
            float newBalanceGiver = balanceGiverToUpdate - (transferAmount + (transferAmount * 0.05f));
            giverToUpdate.setBalance(newBalanceGiver);
            userRepository.save(giverToUpdate); //update

            String receiverEmail = moneyTransaction.getReceiver().getUserEmail();
            Optional<User> optionalReceiver = userRepository.findById(receiverEmail);
            User receiver = optionalReceiver.get();
            float balanceReceiver = receiver.getBalance();
            float newBalanceReceiver = balanceReceiver + transferAmount;
            receiver.setBalance(newBalanceReceiver);
            userRepository.save(receiver); //update

            moneyTransactionRepository.save(moneyTransaction);

        } else {
            paymentAllowed = false;
        }
        return paymentAllowed;
    }
}
