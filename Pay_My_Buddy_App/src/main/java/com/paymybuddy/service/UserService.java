package com.paymybuddy.service;

import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getUsers(){
        return userRepository.findAll();    //methode findAll() fournie par userRepository car la classe userRepository etend la class crudrepository
    }

    public void addUser(User userToAdd){
        userRepository.save(userToAdd);    //methode findAll() fournie par userRepository car la classe userRepository etend la class crudrepository
    }

    public void updateUser(User userToUpdate){
        userRepository.save(userToUpdate);    //methode findAll() fournie par userRepository car la classe userRepository etend la class crudrepository
    }

    public void deleteUser(User userToDelete){
        userRepository.delete(userToDelete);    //methode findAll() fournie par userRepository car la classe userRepository etend la class crudrepository
    }
}
