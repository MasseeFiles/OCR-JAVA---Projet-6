package com.paymybuddy.service;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentServiceTest {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private MoneyTransactionRepository moneyTransactionRepository;

    @Autowired
    private PaymentService paymentService;

    @BeforeEach
    public void setup() {
        // Create a mock DatabaseConnector
        userRepository = mock(UserRepository.class);
    }
    @Test
    void allowPayment_GiverBalanceOK() {
        //GIVEN
        MoneyTransaction moneyTransaction = new MoneyTransaction();
        moneyTransaction.setGiverEmail("giver@email.com");
        moneyTransaction.setAmount(200.05f);

        User giver = new User();
        giver.setUserEmail("giver@email.com");
        Optional<User> optionalGiverTest = Optional.of(giver);
        String giverEmail = "giver@email.com";
        when(userRepository.findById(giverEmail)).thenReturn(optionalGiverTest);  //mock

        User receiver = new User();
        receiver.setUserEmail("receiver@email.com");
        receiver.setBalance(500f);
        moneyTransaction.setReceiver(receiver);

//        User receiver = new User();
//        receiver.setUserEmail("receiver@email.com");
//        moneyTransaction.setReceiver(receiver);

        //WHEN
        boolean booleanTest = paymentService.allowPayment(moneyTransaction);

        //THEN
        assertTrue(booleanTest);
    }

//    @Test
//    void allowPayment_GiverBalanceTooLow() {
//        //GIVEN
//        User giver = new User();
//        giver.setUserEmail("giver@email.com");
//        giver.setBalance(100f);
//        Optional<User> optionalGiverTest = Optional.of(giver);
//        String giverEmail = "giver@email.com";
//        when(userRepository.findById(giverEmail)).thenReturn(optionalGiverTest);  //mock
//
//        MoneyTransaction moneyTransaction = new MoneyTransaction();
//        moneyTransaction.setGiverEmail("giver@email.com");
//        moneyTransaction.setAmount(200.05f);
//
////        User receiver = new User();
////        receiver.setUserEmail("receiver@email.com");
////        moneyTransaction.setReceiver(receiver);
//
//        //WHEN
//        boolean booleanTest = paymentService.allowPayment(moneyTransaction, giver);
//
//        //THEN
//        assertFalse(booleanTest);
//    }
}