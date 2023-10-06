package com.paymybuddy.controllers;

import com.paymybuddy.model.LoginDto;
import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.MoneyTransactionDto;
import com.paymybuddy.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {  //uniquement validation email/password
    private static final Logger logger = LogManager.getLogger("LoginController");

    @GetMapping("/login")
    public String getTransfer(Model model) {    //parametre "Model" IMPORTANT :  permet de passer des données du controller à la vue

        logger.info("Requete pour l'affichage de la page HTML login");

        return "login";
    }

    @PostMapping("/login")
    public String processLogin(Model model, Authentication authentication){
        // Nb - l'extraction du couple username/password de la requete se fait automatiquement dans un objet Authentication
//        logger.info("Requete pour la validation du login de l'utilisateur : " + loginDto.getUserEmail());
//        logger.info("Requete pour la validation du login de l'utilisateur : " + authentication.getUserEmail());
//        logger.info("Requete pour la validation du login de l'utilisateur : " + authentication.getPassword());
//        logger.info("Requete pour la validation du login de l'utilisateur : " + authentication.getPrincipal().toString());
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        System.out.println(principal.authentication.getName());
        return "redirect:/transfer";

    }

}
