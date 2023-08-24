package com.paymybuddy.repository;

import com.paymybuddy.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {  //string parce que la clef primaire (userEmail) est de type string
}
