package com.paymybuddy.controllers;

import com.paymybuddy.model.Contact;
import com.paymybuddy.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class contactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(value = "/contact")
    public String addContact(Contact contactToAdd) {    //valeur renvoyée est une string qui indique une view à afficher
        //LOGGER.info("Requete pour l'ajout d'un contact : " + contactToAdd);
        try {
            contactService.addContact(contactToAdd);
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

