package com.paymybuddy.controllers;

import com.paymybuddy.model.MoneyTransactionDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {  //uniquement validation email/password
    private static final Logger logger = LogManager.getLogger("LoginController");

    @PostMapping("/login")
    public String processLogin(Model model , String userName, String password){

        logger.info("Requete pour la validation du login de l'utilisateur : " + userName);


        return "login";
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
