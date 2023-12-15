package com.paymybuddy.service;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MoneyTransactionService {
    private final UserRepository userRepository;
    private final MoneyTransactionRepository moneyTransactionRepository;

    public MoneyTransactionService(UserRepository userRepository, MoneyTransactionRepository moneyTransactionRepository) {
        this.userRepository = userRepository;
        this.moneyTransactionRepository = moneyTransactionRepository;
    }

    @Transactional  //@Transactional(rollbackOn = RuntimeException.class)  //Exemple configuration d'un rollback
    public boolean allowPayment(MoneyTransaction moneyTransaction) {
        boolean paymentAllowed;
        float transferAmount = moneyTransaction.getAmount();

//        String giverEmail = moneyTransaction.getGiverEmail();

//        User giverToCheck = userRepository.findByUserEmail(giverEmail)
//                .orElseThrow(() -> new RuntimeException("User giver not found : Id used " + giverEmail));     //.orElseThrow converti l'optional en User

//                      Equivaut à
//        Optional<User> optionalGiver = userRepository.findById(giverEmail);
//        if (optionalGiver.isEmpty()) {    //verification de la valeur vide ou pas de l'optional
//            throw new RuntimeException("User giver not found : Id used " + giverEmail);
//        } else {
//        User giverToCheck = optionalGiver.get();
//        }

        float balanceGiver = moneyTransaction.getGiver().getBalance();

        if (transferAmount <= balanceGiver) {    //verification du solde du donneur
            paymentAllowed = true;

            User giverToUpdate = moneyTransaction.getGiver();

            float balanceGiverToUpdate = giverToUpdate.getBalance();
            float newBalanceGiver = balanceGiverToUpdate - transferAmount;
            giverToUpdate.setBalance(newBalanceGiver);
            userRepository.save(giverToUpdate); //update

            User receiverToUpdate = moneyTransaction.getReceiver();

//            userRepository.findByUserEmail(moneyTransaction.getReceiver().getUserEmail())
//                    .orElseThrow(() -> new RuntimeException("User receiver not found : Id used " + receiverEmail));    //.orElseThrow converti l'optional en User
//
//            moneyTransaction.setReceiver(receiverToUpdate);

//                    Equivaut à
//            Optional<User> optionalReceiver = userRepository.findById(receiverEmail);
//            if (optionalReceiver.isEmpty()) {    //verification de la valeur vide ou pas de l'optional
//                throw new RuntimeException("User receiver not found : Id used " + receiverEmail);
//            } else {
//            User receiverToUpdate = optionalReceiver.get();
//            }

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

    public List<MoneyTransaction> findAllById(int moneyTransactionId) {
        return (List<MoneyTransaction>) moneyTransactionRepository.findAllById(Collections.singleton(moneyTransactionId));
    }
}
