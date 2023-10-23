package com.paymybuddy.controllers;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.MoneyTransactionDto;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.MoneyTransactionService;
import com.paymybuddy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;

    //  enpoint get sert uniquement à l'insertion des données de la BDD dans la vue (via thymeleaf)
    @GetMapping("/transfer")
    public String getTransfer(Model model) {    //parametre "Model" IMPORTANT :  permet de passer des données du controller à la vue via thymeleaf

        logger.info("Requete pour l'affichage de la page HTML transfer");

        String userEmailAuthenticated = userService.getUserEmailAuthenticated();

        List<MoneyTransaction> moneyTransactionsAuthenticated = (List<MoneyTransaction>) moneyTransactionRepository.findAllByGiverEmail(userEmailAuthenticated);
        model.addAttribute("moneyTransactions", moneyTransactionsAuthenticated);

        User userAuthenticated = userRepository.findById(userEmailAuthenticated)
                .orElseThrow(() -> new RuntimeException("UserAuthenticated not found : Id used " + userEmailAuthenticated));
        List<Contact> contactsAuthenticated = userAuthenticated.getContacts();
        model.addAttribute("contacts", contactsAuthenticated);

        return "transfer";
    }

    @PostMapping("/transferRequest")
    public String processPayment(MoneyTransactionDto moneyTransactionDto) {
        //valeur renvoyée est une string qui indique une view à afficher


        logger.info("Requete pour l'ajout d'une moneyTransaction en utilisant le moneyTransactionDto  : " + moneyTransactionDto);

        String userEmailAuthenticated = userService.getUserEmailAuthenticated();

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


