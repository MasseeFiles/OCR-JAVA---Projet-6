package com.paymybuddy.service;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ContactRepository;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DataInsertService {
    //Constructeur
    public DataInsertService(ContactRepository contactRepository, MoneyTransactionRepository moneyTransactionRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        User[] users = new User[4];

        users[0] = new User("userEmail1", "$2a$12$kVlFNkyutY6qXRyT/JRdUusQLZp6k1MXS47eeKDJh3PBtgHNof2sO", "fred", "smith", 6000f);    //fred
        users[1] = new User("userEmail2", "$2a$12$Ko/Ai7TyQwfVAFYS42jfl.YeP01OpDIoU7b6spyMXz7FWbPC4XYWC", "mike", "smith", 200f); //mike
        users[2] = new User("userEmail3", "$2a$12$xESGiGC8PVYoeMa3pC4TXOp/AYm8WVvuHqh5dTf6sbwN9c8nvNNRC", "cindy", "smith", 0f);   //cindy
        users[3] = new User("userEmail4", "$2a$12$DxOiBwpuYZStOBfhzYvWWeEzZFcg96WLgMIOMwNLfkWPgtlf7qzb2", "jack", "smith", 5000f);    //jack

        userRepository.saveAll(Arrays.asList(users));

        Contact[] contacts = new Contact[5];

        contacts[0] = new Contact("jack - friend of fred", users[0], users[3]);
        contacts[1] = new Contact("mike - friend of fred", users[0], users[1]);
        contacts[2] = new Contact("cindy - friend of fred", users[0], users[2]);
        contacts[3] = new Contact("jack - friend of mike", users[1], users[3]);
        contacts[4] = new Contact("cindy - friend of mike", users[1], users[2]);
        contactRepository.saveAll(Arrays.asList(contacts));

        MoneyTransaction[] moneyTransactions = new MoneyTransaction[4];

        moneyTransactions[0] = new MoneyTransaction(users[0], users[1], "description 1", 445);
        moneyTransactions[1] = new MoneyTransaction(users[1], users[2], "description2", 456);
        moneyTransactions[2] = new MoneyTransaction(users[2], users[3], "description3", 78);
        moneyTransactions[3] = new MoneyTransaction(users[3], users[0], "description4", 21);
        moneyTransactionRepository.saveAll(Arrays.asList(moneyTransactions));
    }
}
