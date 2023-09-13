package com.paymybuddy.service;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentServiceTest {
    private UserRepository userRepository;
    private MoneyTransactionRepository moneyTransactionRepository;
    private PaymentService paymentservice;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        moneyTransactionRepository = mock(MoneyTransactionRepository.class);
        paymentservice = new PaymentService(userRepository, moneyTransactionRepository);
    }

    @Test
    void allowPayment_GiverBalanceOK() {
        //GIVEN
        MoneyTransaction moneyTransaction = new MoneyTransaction();
        moneyTransaction.setGiverEmail("giver@email.com");
        moneyTransaction.setAmount(200.05f);

        User giver = new User();
        giver.setUserEmail("giver@email.com");
        giver.setBalance(500f);
        Optional<User> optionalGiverTest = Optional.of(giver);
        String giverEmail = "giver@email.com";
        when(userRepository.findById(giverEmail)).thenReturn(optionalGiverTest);  //mock

        User receiver = new User();
        receiver.setUserEmail("receiver@email.com");
        receiver.setBalance(500f);
        Optional<User> optionalReceiverTest = Optional.of(receiver);
        moneyTransaction.setReceiver(receiver);
        when(userRepository.findById(receiver.getUserEmail())).thenReturn(optionalReceiverTest);  //mock

        //WHEN
        boolean booleanTest = paymentservice.allowPayment(moneyTransaction);

        //THEN
        assertTrue(booleanTest);
//        assertion sur calcul de solde - parametre appel de methode userRepository.save(receiverToUpdate);
//        float newBalanceGiverExpected = 500f;
//        float newBalanceGiverActual = giver.getBalance();
//        assertEquals(newBalanceGiverExpected, newBalanceGiverActual);
//
//
//        float newBalanceReceiverExpected = 700f;
//        float newBalanceReceiver = receiver.getBalance();
//        assertEquals(newBalanceReceiverExpected, newBalanceReceiver);
    }

    @Test
    void allowPayment_GiverBalanceTooLow() {
        //GIVEN
        MoneyTransaction moneyTransaction = new MoneyTransaction();
        moneyTransaction.setGiverEmail("giver@email.com");
        moneyTransaction.setAmount(200.05f);

        User giver = new User();
        giver.setUserEmail("giver@email.com");
        giver.setBalance(100f);
        Optional<User> optionalGiverTest = Optional.of(giver);
        String giverEmail = "giver@email.com";
        when(userRepository.findById(giverEmail)).thenReturn(optionalGiverTest);  //mock

        User receiver = new User();
        receiver.setUserEmail("receiver@email.com");
        receiver.setBalance(500f);
        Optional<User> optionalReceiverTest = Optional.of(receiver);
        moneyTransaction.setReceiver(receiver);
        when(userRepository.findById(receiver.getUserEmail())).thenReturn(optionalReceiverTest);  //mock

        //WHEN
        boolean booleanTest = paymentservice.allowPayment(moneyTransaction);

        //THEN
        assertFalse(booleanTest);
    }

    @Test
    void allowPayment_GiverNotFound() {
        //GIVEN
        MoneyTransaction moneyTransaction = new MoneyTransaction();
        moneyTransaction.setGiverEmail("giver@email.com");
        moneyTransaction.setAmount(200.05f);

        String giverEmail = "giver@email.com";
        when(userRepository.findById(giverEmail)).thenReturn(null);  //giver non present dans la BDD , renvoie null

        User receiver = new User();
        receiver.setUserEmail("receiver@email.com");
        receiver.setBalance(500f);
        Optional<User> optionalReceiverTest = Optional.of(receiver);
        moneyTransaction.setReceiver(receiver);
        when(userRepository.findById(receiver.getUserEmail())).thenReturn(optionalReceiverTest);  //mock

        //WHEN

        //THEN
        assertThrows(RuntimeException.class, () -> paymentservice.allowPayment(moneyTransaction));
        assertThatThrownBy(() -> paymentservice.allowPayment(moneyTransaction))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User giver not found : Id used ");

    }
}