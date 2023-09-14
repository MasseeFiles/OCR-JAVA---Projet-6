package com.paymybuddy.controllers;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.MoneyTransactionDto;
import com.paymybuddy.model.User;
import com.paymybuddy.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MoneyTransactionController {
    private static final Logger logger = LogManager.getLogger("MoneyTransactionController");
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/transfer")
    public String getTransfer(Model model) {    //parametre "Model" IMPORTANT :  permet de passer des données du controller à la vue

        MoneyTransaction[] moneyTransactions = new MoneyTransaction[4];
        User user = new User();
        user.setFirstName("bob");

        moneyTransactions[0] = new MoneyTransaction("giverEmail1", user , "description1", 15);
        moneyTransactions[1] = new MoneyTransaction("giverEmail2", user , "description2", 456);
        moneyTransactions[2] = new MoneyTransaction("giverEmail3", user , "description3", 78);
        moneyTransactions[3] = new MoneyTransaction("giverEmail4", user , "description4", 21);

        model.addAttribute("moneyTransactions", moneyTransactions);

        Contact[] contacts = new Contact[4];

        contacts[0] = new Contact("bob");
        contacts[1] = new Contact("mike");
        contacts[2] = new Contact("kim");
        contacts[3] = new Contact("cindy");

        model.addAttribute("contacts", contacts);

        return "transfer";
    }

//    @PostMapping("/moneyTransactionForm")
//    public String processPayment(Model model , MoneyTransaction moneyTransactionToAdd) {    //valeur renvoyée est une string qui indique une view à afficher
//
//        logger.info("Requete pour l'ajout d'une moneyTransaction : " + moneyTransactionToAdd);
//
////        model.addAttribute( "errorMessage", String.format("Could not validate this money transaction!"));
////        model.addAttribute( "successMessage", String.format("Money transaction validated!"));
//
//        try {
//            paymentService.allowPayment(moneyTransactionToAdd);
//            return "redirect:/transfer";
//        } catch (RuntimeException ex) {
//            logger.warn("Impossible d'ajouter la moneyTransaction " + moneyTransactionToAdd, ex);
//            return "transfer";
//        }
//    }

    //version du controller avec dto : utilisation de modelMapper necessaire pour conversion???
//    @PostMapping("/moneyTransactionForm")
//    public String processPayment(Model model , MoneyTransactionDto moneyTransactionDto) {    //valeur renvoyée est une string qui indique une view à afficher
//
//        logger.info("Requete pour l'ajout d'une moneyTransaction en utilisant le moneyTransactionDto  : " + moneyTransactionDto);
//
//        MoneyTransaction moneyTransactionToAdd = new MoneyTransaction();
//
//        moneyTransactionToAdd.setGiverEmail(moneyTransactionDto.getGiverEmail());
//        User user = new User();
//        user.setUserEmail(moneyTransactionDto.getReceiverEmail());
//        moneyTransactionToAdd.setReceiver(user);
//        moneyTransactionToAdd.setAmount(moneyTransactionDto.getTransferAmount());
//        // par d'ajout de description dans une nouvelle moneyTransaction pour l'instant
//
//        try {
//            paymentService.allowPayment(moneyTransactionToAdd);
//            return "redirect:/transfer";
//        } catch (RuntimeException ex) {
//            logger.warn("Impossible d'ajouter la moneyTransaction " + moneyTransactionToAdd, ex);
//            return "transfer";
//        }
//    }

}


