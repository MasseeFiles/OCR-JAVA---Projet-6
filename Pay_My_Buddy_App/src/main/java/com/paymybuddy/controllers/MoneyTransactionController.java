package com.paymybuddy.controllers;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.MoneyTransactionDto;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ContactRepository;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.service.MoneyTransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class MoneyTransactionController {
    private static final Logger logger = LogManager.getLogger("MoneyTransactionController");

    @Autowired
    private MoneyTransactionService moneyTransactionService;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;

    //  enpoint get sert uniquement à l'insertion des données de la BDD dans la vue (via thymeleaf)
    @GetMapping("/transfer")
    public String getTransfer(Model model) {    //parametre "Model" IMPORTANT :  permet de passer des données du controller à la vue via thymeleaf

        logger.info("Requete pour l'affichage de la page HTML transfer");

        SecurityContextHolder security = new SecurityContextHolder(); // a retirer - suivi en mode debug

        String userEmailAuthenticated;

        //methode 1 - recuperation userEmailAuthenticated (ok si authentification reussie)
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            userEmailAuthenticated = ((UserDetails) principal).getUsername();
//        } else {
//            userEmailAuthenticated = principal.toString();
//        }
//
//        methode 2
//        userEmailAuthenticated = SecurityContextHolder.getContext().toString();

        //methode 3
        userEmailAuthenticated = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();


//        //methode 1 - transformation d'un iterable en collection (list)
        List<MoneyTransaction> moneyTransactionsAuthenticated = new ArrayList<MoneyTransaction>();

        Iterable<MoneyTransaction> iterableMoneyTransaction = moneyTransactionRepository.findAllById(Collections.singleton(userEmailAuthenticated));
        iterableMoneyTransaction.forEach(moneyTransactionsAuthenticated::add);
        model.addAttribute("contacts", moneyTransactionsAuthenticated);

        // ne marche pas
//        List<MoneyTransaction> moneyTransactionsAuthenticated = (List<MoneyTransaction>) moneyTransactionRepository.findAllById(Collections.singleton(userEmailAuthenticated));
//        model.addAttribute("moneyTransactions", moneyTransactionsAuthenticated);

//        //methode 2
//        List<Contact> contactsAuthenticated = new ArrayList<Contact>();
//        Iterable<Contact> iterable = contactRepository.findAllById(Collections.singleton(userEmailAuthenticated));
//        iterable.forEach(contactsAuthenticated::add);
//        model.addAttribute("contacts", contactsAuthenticated);
//
        return "transfer";
    }

    @PostMapping("/transferRequest")
    public String processPayment(Model model, MoneyTransactionDto moneyTransactionDto) {    //valeur renvoyée est une string qui indique une view à afficher

        SecurityContextHolder security = new SecurityContextHolder(); // a retirer - suivi en mode debug

        logger.info("Requete pour l'ajout d'une moneyTransaction en utilisant le moneyTransactionDto  : " + moneyTransactionDto);

        String userEmailAuthenticated = SecurityContextHolder.getContext().toString();

        MoneyTransaction moneyTransactionToAdd = new MoneyTransaction();
        moneyTransactionToAdd.setGiverEmail(userEmailAuthenticated);

        //Passage des données d'un moneyTransactionDto à un moneyTransaction
        String receiverEmailToAdd = moneyTransactionDto.getContactIdEmbeddedIdOtherEmail();
        User userReceiver = new User();
        userReceiver.setUserEmail(receiverEmailToAdd);
        moneyTransactionToAdd.setReceiver(userReceiver);

        moneyTransactionToAdd.setAmount(moneyTransactionDto.getTransferAmount());

        try {
            moneyTransactionService.allowPayment(moneyTransactionToAdd);

            return "redirect:/transfer";
        } catch (RuntimeException ex) {

            logger.warn("Impossible d'ajouter la moneyTransaction " + moneyTransactionToAdd, ex);

            return "redirect:/transfer";
        }
    }
}

