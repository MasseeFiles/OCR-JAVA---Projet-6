package com.paymybuddy.service;

import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User userToAdd) {
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

    public User findByUserEmail(String userEmailAuthenticated) {
        return userRepository.findByUserEmail(userEmailAuthenticated)
                .orElseThrow(() -> new RuntimeException("UserAuthenticated not found : Id used " + userEmailAuthenticated));
    }
}
