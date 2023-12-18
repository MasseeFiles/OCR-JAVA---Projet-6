package com.paymybuddy.controllers;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.MoneyTransactionDto;
import com.paymybuddy.model.User;
import com.paymybuddy.service.MoneyTransactionService;
import com.paymybuddy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MoneyTransactionController {
    private static final Logger logger = LogManager.getLogger("MoneyTransactionController");
    @Autowired
    private MoneyTransactionService moneyTransactionService;
    @Autowired
    private UserService userService;

    @GetMapping("/transfer")
    public String getTransfer(Model model) {    //parametre "Model" permet de passer des données du controller à la vue via thymeleaf

        logger.info("Requete pour l'affichage de la page HTML transfer");

        String userEmailAuthenticated = userService.getUserEmailAuthenticated();
        User userAuthenticated = userService.findByUserEmail(userEmailAuthenticated);

        List<MoneyTransaction> moneyTransactionsAuthenticated = userAuthenticated.getMoneyTransactions();
        model.addAttribute("moneyTransactions", moneyTransactionsAuthenticated);

        List<Contact> contactsAuthenticated = userAuthenticated.getContacts();
        model.addAttribute("contacts", contactsAuthenticated);

        return "transfer";
    }

    @PostMapping("/transfer")
    public String processPayment(MoneyTransactionDto moneyTransactionDto) {         //valeur renvoyée est une string qui indique une view à afficher

        logger.info("Requete pour l'ajout d'une moneyTransaction en utilisant le moneyTransactionDto  : " + moneyTransactionDto);

        MoneyTransaction moneyTransactionToAdd = new MoneyTransaction();

        String userEmailAuthenticated = userService.getUserEmailAuthenticated();
        User userAuthenticated = userService.findByUserEmail(userEmailAuthenticated);
        moneyTransactionToAdd.setGiver(userAuthenticated);

        String receiverEmailToAdd = moneyTransactionDto.getOtherUserEmail();
        moneyTransactionToAdd.setAmount(moneyTransactionDto.getTransferAmount());

        try {
            User userReceiver = userService.findByUserEmail(receiverEmailToAdd);
            moneyTransactionToAdd.setReceiver(userReceiver);
            moneyTransactionService.allowPayment(moneyTransactionToAdd);
            return "redirect:/transfer";
        } catch (RuntimeException ex) {

            logger.warn("Impossible d'ajouter la moneyTransaction " + moneyTransactionToAdd, ex);

            return "redirect:/transfer";
        }
    }
}


