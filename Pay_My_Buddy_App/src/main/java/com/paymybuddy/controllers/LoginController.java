package com.paymybuddy.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private static final Logger logger = LogManager.getLogger("LoginController");

    @GetMapping("/login")
    public String getTransfer(Model model) {

        logger.info("Requete pour l'affichage de la page HTML login");

        return "login";
    }


}