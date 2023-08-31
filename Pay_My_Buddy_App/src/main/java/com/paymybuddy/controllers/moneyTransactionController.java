package com.paymybuddy.controllers;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.service.MoneyTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class moneyTransactionController {

    @Autowired
    private MoneyTransactionService moneyTransactionService;

    @PostMapping(value = "/moneyTransaction")
    public String addMoneyTransaction(MoneyTransaction moneyTransactionToAdd) {    //valeur renvoyée est une string qui indique une view à afficher
        //LOGGER.info("Requete pour l'ajout d'un contact : " + contactToAdd);
        try {
            moneyTransactionService.addMoneyTransaction(moneyTransactionToAdd);
            return "redirect:/user/homepage";
        } catch (RuntimeException ex) {
            //LOGGER.warn("Impossible d'ajouter la connection " + contactToAdd, ex);
            return "error";
        }
    }
    /* EXEMPLE P7
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
 */
}


