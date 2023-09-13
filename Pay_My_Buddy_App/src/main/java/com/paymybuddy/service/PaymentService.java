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

    public boolean allowPayment(MoneyTransaction moneyTransaction) throws RuntimeException {
        boolean paymentAllowed;
        float transferAmount = moneyTransaction.getAmount();
        User giverToCheck = new User();

        String giverEmail = moneyTransaction.getGiverEmail();
        Optional<User> optionalGiver = userRepository.findById(giverEmail);

                if(optionalGiver == null){
                    throw new RuntimeException("User giver not found : Id used " + giverEmail);
                }
                if (optionalGiver.isPresent()) {
                    giverToCheck = optionalGiver.get();
                }
//                .orElseThrow(() -> new RuntimeException("User giver not found : Id used " + giverEmail))); //IMPOSSIBLE A UTILISER AVEC VALEUR NULL
//        Optional<User> optionalGiver = userRepository.findById(giverEmail);    //peut exister ou etre null si abset dans la BDD
//        if(optionalGiver.isPresent()){
//            giverToCheck = optionalGiver.get();
//        } else {
//            throw new RuntimeException("User giver not found : Id used " + giverEmail);
//        }

        float balanceGiver = giverToCheck.getBalance();

        if (transferAmount <= balanceGiver) {    //verification du solde du donneur
            paymentAllowed = true;

            User giverToUpdate = optionalGiver.get();   //a voir si plutot utilisation de givertocheck
            float balanceGiverToUpdate = giverToUpdate.getBalance();
            float newBalanceGiver = balanceGiverToUpdate - transferAmount;
            giverToUpdate.setBalance(newBalanceGiver);
            userRepository.save(giverToUpdate); //update

            String receiverEmail = moneyTransaction.getReceiver().getUserEmail();

//            Optional<User> optionalReceiver = Optional.of(userRepository.findById(receiverEmail)
//                    .orElseThrow(() -> new RuntimeException("User receiver not found : Id used " + receiverEmail)));
            Optional<User> optionalReceiver = userRepository.findById(receiverEmail);
//            if(optionalReceiver.isPresent()){
//                receiverToUpdate = optionalReceiver.get();
//            } else {
//                throw new RuntimeException("User receiver not found : Id used " + receiverEmail);
//            }
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
