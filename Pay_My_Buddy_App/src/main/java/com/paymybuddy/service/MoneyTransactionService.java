package com.paymybuddy.service;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

        String giverEmail = moneyTransaction.getGiverEmail();

        User giverToCheck = userRepository.findById(giverEmail)
                .orElseThrow(() -> new RuntimeException("User receiver not found : Id used " + giverEmail));     //.orElseThrow converti l'optional en User

//                      Equivaut à
//        Optional<User> optionalGiver = userRepository.findById(giverEmail);
//        if (optionalGiver.isEmpty()) {    //verification de la valeur vide ou pas de l'optional
//            throw new RuntimeException("User giver not found : Id used " + giverEmail);
//        } else {
//        User giverToCheck = optionalGiver.get();
//        }

        float balanceGiver = giverToCheck.getBalance();

        if (transferAmount <= balanceGiver) {    //verification du solde du donneur
            paymentAllowed = true;

            float balanceGiverToUpdate = giverToCheck.getBalance();
            float newBalanceGiver = balanceGiverToUpdate - transferAmount;
            giverToCheck.setBalance(newBalanceGiver);
            userRepository.save(giverToCheck); //update

            String receiverEmail = moneyTransaction.getReceiver().getUserEmail();

            User receiverToUpdate = userRepository.findById(receiverEmail)
                    .orElseThrow(() -> new RuntimeException("User receiver not found : Id used " + receiverEmail));    //.orElseThrow converti l'optional en User

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
}
