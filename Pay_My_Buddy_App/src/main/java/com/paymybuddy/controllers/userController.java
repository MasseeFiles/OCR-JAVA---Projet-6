package com.paymybuddy.controllers;

import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    public ResponseEntity addUser(@RequestBody User userToAdd) {
        //LOGGER.info("Requete pour l'ajout d'un user : " + userToAdd);
        try {
            userService.addUser(userToAdd);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            //LOGGER.warn("Impossible d'ajouter l'utilisateur " + fireStationToAdd, ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/user")
    public ResponseEntity updateUser(@RequestBody User userToUpdate) {
        //LOGGER.info("Requete pour la mise à jour d'un  utilisateur : " + userToUpdate);
        try {
            userService.updateUser(userToUpdate);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            //LOGGER.warn("Impossible de mettre à jour l'utilisateur " + userToUpdate, ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity deleteUser(@RequestBody User userToDelete) {
        //LOGGER.info("Requete pour la suppression d'un utilisateur : " + userToDelete);
        try {
            userService.deleteUser(userToDelete);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            //LOGGER.warn("Impossible de supprimer l'utilisateur " + userToDelete, ex);
            return ResponseEntity.badRequest().build();
        }
    }

    /*
    @PostMapping("/user")
    public String validate(@Valid User user, BindingResult result, Model model) {
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
