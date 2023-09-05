package com.paymybuddy.controllers;

import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.service.MoneyTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class moneyTransactionController {

    @Autowired
    private MoneyTransactionService moneyTransactionService;

    @GetMapping("/transfer")
    public String getTransfer(Model model) {

        MoneyTransaction[] moneyTransactions = new MoneyTransaction[4];
        User user = new User();
        user.setFirstName("bob");

        moneyTransactions[0] = new MoneyTransaction("giverEmail1", user , "description1", 15);
        moneyTransactions[1] = new MoneyTransaction("giverEmail2", user , "description2", 456);
        moneyTransactions[2] = new MoneyTransaction("giverEmail3", user , "description3", 78);
        moneyTransactions[3] = new MoneyTransaction("giverEmail4", user , "description4", 21);

        model.addAttribute("moneyTransactions", moneyTransactions);

        return "transfer";
    }

    @PostMapping("/moneyTransaction")
    public String addMoneyTransaction(MoneyTransaction moneyTransactionToAdd) {    //valeur renvoyée est une string qui indique une view à afficher
        //model.addAttribute("moneyTransactions", moneyTransactions);
        //LOGGER.info("Requete pour l'ajout d'un contact : " + contactToAdd);
        try {
            moneyTransactionService.addMoneyTransaction(moneyTransactionToAdd);
            return "redirect:/transfer";
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


