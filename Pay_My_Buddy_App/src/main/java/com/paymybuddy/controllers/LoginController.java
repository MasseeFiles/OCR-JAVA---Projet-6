package com.paymybuddy.controllers;

import com.paymybuddy.model.LoginDto;
import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.MoneyTransactionDto;
import com.paymybuddy.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {  //uniquement validation email/password
    private static final Logger logger = LogManager.getLogger("LoginController");

    @GetMapping("/login")
    public String getTransfer(Model model) {    //parametre "Model" IMPORTANT :  permet de passer des données du controller à la vue

        logger.info("Requete pour l'affichage de la page HTML login");

        return "login";
    }

    @PostMapping("/login")
    public String processLogin(Model model , LoginDto loginDto){

        logger.info("Requete pour la validation du login de l'utilisateur : " + loginDto.getUserEmail());


        return "redirect:/transfer";
    }
    /*public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            model.addAttribute("users", userRepository.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }
*/
}
