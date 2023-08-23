package com.paymybuddy.repository;

import com.paymybuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {  //string parce que la clef primaire (userEmail) est de type string
    //READ, CREATE, UPDATE , DELETE




//    public Iterable<User> getUsers(){
//        Iterable<User> iterableUser = userRepository.getUsers();
//    }
//        return userRepository.findAll();
//    List<User> users = new ArrayList<>();
//    users = iterableUser //faire conversion

}
