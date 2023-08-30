package com.paymybuddy.controllers;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.service.MoneyTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class moneyTransactionController {

    @Autowired
    private MoneyTransactionService moneyTransactionService;

    @PostMapping(value = "/moneyTransaction")
    public ResponseEntity addMoneyTransaction(@RequestBody MoneyTransaction moneyTransactionToAdd) {
        //LOGGER.info("Requete pour l'ajout d'une transaction : " + moneyTransactionToAdd);
        try {
            moneyTransactionService.addMoneyTransaction(moneyTransactionToAdd);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            //LOGGER.warn("Impossible d'ajouter la transaction " + moneyTransactionToAdd, ex);
            return ResponseEntity.badRequest().build();
        }
    }

/* voir si pertinent
        @PutMapping(value = "/contact")
        public ResponseEntity updateContact(@RequestBody Contact contactToUpdate) {
            //LOGGER.info("Requete pour la mise à jour d'un contact : " + contactToUpdate);
            try {
                contactService.updateContact(contactToUpdate);
                return ResponseEntity.ok().build();
            } catch (RuntimeException ex) {
                //LOGGER.warn("Impossible de mettre à jour le contact " + contactToUpdate, ex);
                return ResponseEntity.badRequest().build();
            }
        }

        @DeleteMapping(value = "/contact")
        public ResponseEntity deleteUser(@RequestBody Contact contactToDelete) {
            //LOGGER.info("Requete pour la suppression d'un contact : " + contactToDelete);
            try {
                contactService.deleteContact(contactToDelete);
                return ResponseEntity.ok().build();
            } catch (RuntimeException ex) {
                //LOGGER.warn("Impossible de supprimer le contact " + userToDelete, ex);
                return ResponseEntity.badRequest().build();
            }
        }
    }
    */

}
