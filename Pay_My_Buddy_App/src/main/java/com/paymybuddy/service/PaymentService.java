package com.paymybuddy.service;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PaymentService {
    public PaymentService(UserRepository userRepository, MoneyTransactionRepository moneyTransactionRepository) {
        this.userRepository = userRepository;
        this.moneyTransactionRepository = moneyTransactionRepository;
    }

    private UserRepository userRepository;
    private MoneyTransactionRepository moneyTransactionRepository;

    public boolean allowPayment(MoneyTransaction moneyTransaction) {
        boolean paymentAllowed;

        float transferAmount = moneyTransaction.getAmount();

        String giverEmail = moneyTransaction.getGiverEmail();
        Optional<User> optionalGiver = userRepository.findById(giverEmail); //recherche dans la bdd des infos du giver
        User giverToCheck = optionalGiver.get();
        float balanceGiver = giverToCheck.getBalance();

        if (transferAmount <= balanceGiver) {    //verification du solde du donneur
            paymentAllowed = true;

            User giverToUpdate = optionalGiver.get();   //a voir si plutot utilisation de givertocheck
            float balanceGiverToUpdate = giverToUpdate.getBalance();
            float newBalanceGiver = balanceGiverToUpdate - transferAmount;
            giverToUpdate.setBalance(newBalanceGiver);
            userRepository.save(giverToUpdate); //update

            String receiverEmail = moneyTransaction.getReceiver().getUserEmail();
            Optional<User> optionalReceiver = userRepository.findById(receiverEmail);
            User receiverToUpdate = optionalReceiver.orElseThrow(() -> new RuntimeException("receiver not found"));
            float balanceReceiver = receiverToUpdate.getBalance();
            float newBalanceReceiver = balanceReceiver + transferAmount;
            receiverToUpdate.setBalance(newBalanceReceiver);
            userRepository.save(receiverToUpdate); //update

            moneyTransactionRepository.save(moneyTransaction);

        } else {
            paymentAllowed = false;
        }
        return paymentAllowed;
    }
}
