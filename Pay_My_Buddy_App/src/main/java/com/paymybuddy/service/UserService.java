package com.paymybuddy.service;

import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(User userToAdd){
        userRepository.save(userToAdd);
    }
    public String getUserEmailAuthenticated() {
        String userEmailAuthenticated;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userEmailAuthenticated = ((UserDetails) principal).getUsername();
        } else {
            userEmailAuthenticated = principal.toString();
        }
        return userEmailAuthenticated;
    }
}
