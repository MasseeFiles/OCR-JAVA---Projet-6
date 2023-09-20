package com.paymybuddy.controllers;

import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private static final Logger logger = LogManager.getLogger("UserController");

    @Autowired
    private UserService userService;

    //ENDPOINT
    @PostMapping(value = "/user")
    public String addUser(User userToAdd) {    //valeur renvoyée est une string qui indique une view à afficher
        logger.info("Requete pour la sauvegarde du User: " + userToAdd);
        try {
            userService.addUser(userToAdd);
            return "redirect:/user/homepage";
        } catch (RuntimeException ex) {
            logger.warn("Impossible d'ajouter le User: " + userToAdd, ex);
            return "error";
        }
    }
}
