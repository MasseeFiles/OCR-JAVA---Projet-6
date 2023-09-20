package com.paymybuddy.controllers;

import com.paymybuddy.model.*;
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

        logger.info("Requete pour l'affichage de la page HTML Transfer");

        MoneyTransaction[] moneyTransactions = new MoneyTransaction[4];
        User user = new User();
        user.setFirstName("bob");

        moneyTransactions[0] = new MoneyTransaction("giverEmail1", user , "description1", 15);
        moneyTransactions[1] = new MoneyTransaction("giverEmail2", user , "description2", 456);
        moneyTransactions[2] = new MoneyTransaction("giverEmail3", user , "description3", 78);
        moneyTransactions[3] = new MoneyTransaction("giverEmail4", user , "description4", 21);

        model.addAttribute("moneyTransactions", moneyTransactions);

        Contact[] contacts = new Contact[4];
// ajouter clef embeddedid dans les contacts
        ContactIdEmbeddedId contactIdEmbeddedId = new ContactIdEmbeddedId("giverEmail1", "giverEmail4");
        contacts[0] = new Contact("bob", contactIdEmbeddedId);
        contacts[1] = new Contact("mike", contactIdEmbeddedId);
        contacts[2] = new Contact("kim", contactIdEmbeddedId);
        contacts[3] = new Contact("cindy", contactIdEmbeddedId);

        model.addAttribute("contacts", contacts);

        return "transfer";
    }

//    @PostMapping("/transferRequest")
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
    @PostMapping("/transferRequest")
    public String processPayment(Model model , MoneyTransactionDto moneyTransactionDto) {    //valeur renvoyée est une string qui indique une view à afficher

        logger.info("Requete pour l'ajout d'une moneyTransaction en utilisant le moneyTransactionDto  : " + moneyTransactionDto);

        MoneyTransaction moneyTransactionToAdd = new MoneyTransaction();
        String giverEmailToAdd = ("giverEmail1");    // rechercher dans BDD de giverEmail avec nameContact du DTO
        moneyTransactionToAdd.setGiverEmail(giverEmailToAdd);

        //Conversion d'un moneyTransactionDto en un moneyTransaction
        String receiverEmailToAdd = (moneyTransactionDto.getContactIdEmbeddedIdOtherEmail());
        User user1 = new User();
        user1.setUserEmail(receiverEmailToAdd);

        moneyTransactionToAdd.setReceiver(user1);

        moneyTransactionToAdd.setAmount(moneyTransactionDto.getTransferAmount());
        // par d'ajout de description dans une nouvelle moneyTransaction pour l'instant

        try {
            paymentService.allowPayment(moneyTransactionToAdd);
            return "redirect:/transfer";
        } catch (RuntimeException ex) {
            logger.warn("Impossible d'ajouter la moneyTransaction " + moneyTransactionToAdd, ex);
            return "redirect:/transfer";
        }
    }

//    private String getReceiverEmail(MoneyTransactionDto moneyTransactionDto) {
//        String nameContact = moneyTransactionDto.getNameContactDto();
//        String receiverEmail = ("methode access BDD à écrire");
//        return receiverEmail;
//    }
//
//    private String getGiverEmail(MoneyTransactionDto moneyTransactionDto) {
//        String nameContact = moneyTransactionDto.getNameContactDto();
//
//        String giverEmail = ("methode access BDD à écrire");
//        return giverEmail;
//    }

}


