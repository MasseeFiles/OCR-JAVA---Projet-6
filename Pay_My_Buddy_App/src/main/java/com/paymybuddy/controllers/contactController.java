package com.paymybuddy.controllers;

import com.paymybuddy.model.Contact;
import com.paymybuddy.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class contactController {
    @Autowired
    private ContactService contactService;

    @PostMapping(value = "/contact")
    public ResponseEntity addContact(@RequestBody Contact contactToAdd) {
        //LOGGER.info("Requete pour l'ajout d'un contact : " + contactToAdd);
        try {
            contactService.addContact(contactToAdd);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            //LOGGER.warn("Impossible d'ajouter la connection " + contactToAdd, ex);
            return ResponseEntity.badRequest().build();
        }
    }

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

