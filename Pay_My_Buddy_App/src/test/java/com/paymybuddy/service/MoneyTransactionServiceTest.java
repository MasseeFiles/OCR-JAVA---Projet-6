package com.paymybuddy.service;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MoneyTransactionServiceTest {
    private UserRepository userRepository;
    private MoneyTransactionRepository moneyTransactionRepository;
    private MoneyTransactionService paymentService;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        moneyTransactionRepository = mock(MoneyTransactionRepository.class);
        paymentService = new MoneyTransactionService(userRepository, moneyTransactionRepository);
    }

    @Test
    void allowPayment_GiverBalanceOK() {
        //GIVEN
        User giver = new User();
        String giverEmail = "giver@email.com";
        giver.setUserEmail(giverEmail);
        giver.setBalance(500f);

        User receiver = new User();
        String receiverEmail = ("receiver@email.com");
        receiver.setUserEmail(receiverEmail);
        receiver.setBalance(500f);

        MoneyTransaction moneyTransaction = new MoneyTransaction();
        moneyTransaction.setGiver(giver);
        moneyTransaction.setReceiver(receiver);
        moneyTransaction.setAmount(200.05f);
        //WHEN
        boolean booleanTest = paymentService.allowPayment(moneyTransaction);

        //THEN
        assertTrue(booleanTest);

        float newBalanceGiverExpected = 299.95f;
        float newBalanceGiverActual = giver.getBalance();
        assertEquals(newBalanceGiverExpected, newBalanceGiverActual);

        float newBalanceReceiverExpected = 700.05f;
        float newBalanceReceiver = receiver.getBalance();
        assertEquals(newBalanceReceiverExpected, newBalanceReceiver);
    }

    @Test
    void allowPayment_GiverBalanceTooLow() {
        //GIVEN
        User giver = new User();
        giver.setUserEmail("giver@email.com");
        giver.setBalance(100f);

        User receiver = new User();
        receiver.setUserEmail("receiver@email.com");
        receiver.setBalance(500f);

        MoneyTransaction moneyTransaction = new MoneyTransaction();
        moneyTransaction.setGiver(giver);
        moneyTransaction.setReceiver(receiver);
        moneyTransaction.setAmount(200.05f);
        //WHEN
        boolean booleanTest = paymentService.allowPayment(moneyTransaction);

        //THEN
        assertFalse(booleanTest);
    }

//    @Test
//    void allowPayment_GiverNotFound() {
//        //GIVEN
//        User giver = new User();
//        giver.setUserEmail("giver@email.com");
//        giver.setBalance(200.05f);
//
//        Optional<User> optionalEmpty = Optional.empty();
//        when(userRepository.findByUserEmail(giverEmail)).thenReturn(optionalEmpty);  //giver non present dans la BDD , renvoie un optional Empty
//
//        User receiver = new User();
//        receiver.setUserEmail("receiver@email.com");
//        receiver.setBalance(500f);
//        Optional<User> optionalReceiverTest = Optional.of(receiver);
//        when(userRepository.findByUserEmail(receiver.getUserEmail())).thenReturn(optionalReceiverTest);  //mock
//
//        MoneyTransaction moneyTransaction = new MoneyTransaction();
//        moneyTransaction.setGiver(giver);
//        moneyTransaction.setReceiver(receiver);
//        moneyTransaction.setAmount(200.05f);
//        //WHEN
//
//        //THEN
//        assertThatThrownBy(() -> paymentService.allowPayment(moneyTransaction))
//                .isInstanceOf(RuntimeException.class)
//                .hasMessageContaining("User giver not found");
//    }
//
//    @Test
//    void allowPayment_ReceiverNotFound() {
//        //GIVEN
//        MoneyTransaction moneyTransaction = new MoneyTransaction();
//        String giverEmail = ("giver@email.com");
//        moneyTransaction.setGiverEmail(giverEmail);
//        moneyTransaction.setAmount(200.05f);
//
//        User giver = new User();
//        giver.setUserEmail(giverEmail);
//        giver.setBalance(500f);
//        Optional<User> optionalGiverTest = Optional.of(giver);
//        when(userRepository.findByUserEmail(giverEmail)).thenReturn(optionalGiverTest);  //mock
//
//        User receiver = new User();
//        String receiverEmail = "receiver@email.com";
//        receiver.setUserEmail(receiverEmail);
//        receiver.setBalance(500f);
//        moneyTransaction.setReceiver(receiver);
//
//        Optional<User> optionalEmpty = Optional.empty();
//        when(userRepository.findByUserEmail(receiverEmail)).thenReturn(optionalEmpty); // receiver non present dans la BDD , renvoie un optional Empty
//
//        //WHEN
//
//        //THEN
//        assertThatThrownBy(() -> paymentService.allowPayment(moneyTransaction))
//                .isInstanceOf(RuntimeException.class)
//                .hasMessageContaining("User receiver not found");
//    }
}