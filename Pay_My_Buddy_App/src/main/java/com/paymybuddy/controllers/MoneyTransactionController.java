package com.paymybuddy.controllers;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.MoneyTransactionDto;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ContactRepository;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.DataInsertService;
import com.paymybuddy.service.PaymentService;
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
    private PaymentService paymentService;
    @Autowired
    private DataInsertService dataInsertService;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;
    @Autowired
    private UserRepository userRepository;



    @GetMapping("/transfer")
    public String getTransfer(Model model) {    //parametre "Model" IMPORTANT :  permet de passer des données du controller à la vue

        logger.info("Requete pour l'affichage de la page HTML transfer");

        List<MoneyTransaction> moneyTransactions = dataInsertService.getMoneyTransactions(); //insertion des moneyTransactions dans la BDD h2
        model.addAttribute("moneyTransactions", moneyTransactions);

        List<Contact> contacts = dataInsertService.getContacts(); //insertion des moneyTransactions dans la BDD h2
        model.addAttribute("contacts", contacts);

        return "transfer";
    }

    @PostMapping("/transferRequest")
    public String processPayment(Model model, MoneyTransactionDto moneyTransactionDto) {    //valeur renvoyée est une string qui indique une view à afficher

        logger.info("Requete pour l'ajout d'une moneyTransaction en utilisant le moneyTransactionDto  : " + moneyTransactionDto);

        MoneyTransaction moneyTransactionToAdd = new MoneyTransaction();
        String giverEmailToAdd = ("giverEmail1");    // rechercher dans BDD de giverEmail avec nameContact du DTO
        moneyTransactionToAdd.setGiverEmail(giverEmailToAdd);

        //Conversion d'un moneyTransactionDto en un moneyTransaction
        String receiverEmailToAdd = (moneyTransactionDto.getContactIdEmbeddedIdOtherEmail());
        User userReceiver = new User();
        userReceiver.setUserEmail(receiverEmailToAdd);

        moneyTransactionToAdd.setReceiver(userReceiver);

        moneyTransactionToAdd.setAmount(moneyTransactionDto.getTransferAmount());
        // pas d'ajout de description dans une nouvelle moneyTransaction

        try {
            paymentService.allowPayment(moneyTransactionToAdd);
            return "redirect:/transfer";
        } catch (RuntimeException ex) {
            logger.warn("Impossible d'ajouter la moneyTransaction " + moneyTransactionToAdd, ex);
            return "redirect:/transfer";
        }
    }
}


