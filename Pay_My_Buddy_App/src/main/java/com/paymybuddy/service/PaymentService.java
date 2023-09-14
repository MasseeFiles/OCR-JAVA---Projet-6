package com.paymybuddy.service;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {
    private UserRepository userRepository;
    private MoneyTransactionRepository moneyTransactionRepository;

    public PaymentService(UserRepository userRepository, MoneyTransactionRepository moneyTransactionRepository) {
        this.userRepository = userRepository;
        this.moneyTransactionRepository = moneyTransactionRepository;
    }

    public boolean allowPayment(MoneyTransaction moneyTransaction) throws RuntimeException {    //interet de throws RuntimeException
        boolean paymentAllowed;
        float transferAmount = moneyTransaction.getAmount();

        String giverEmail = moneyTransaction.getGiverEmail();
        Optional<User> optionalGiver = userRepository.findById(giverEmail);

        if (optionalGiver.isEmpty()) {    //verification de la valeur vide ou pas de l'optional
            throw new RuntimeException("User giver not found : Id used " + giverEmail);
        }
//                // OU AVEC METHOD orElse (impossible à utiliser avec valeur null)
//
//        Optional<User> optionalGiver = Optional.of(userRepository.findById(giverEmail)
//                    .orElseThrow(() -> new RuntimeException("User receiver not found : Id used " + giverEmail)));
//

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

            if (optionalReceiver.isEmpty()) {    //verification de la valeur vide ou pas de l'optional
                throw new RuntimeException("User receiver not found : Id used " + receiverEmail);
            }

//                // OU AVEC METHOD orElse
//            Optional<User> optionalReceiver = Optional.of(userRepository.findById(receiverEmail)
//                    .orElseThrow(() -> new RuntimeException("User receiver not found : Id used " + receiverEmail)));

            User receiverToUpdate = optionalReceiver.get();
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
