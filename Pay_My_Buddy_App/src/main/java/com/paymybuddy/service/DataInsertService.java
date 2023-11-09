package com.paymybuddy.service;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.ContactEmbeddedId;
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

        users[0] = new User("giverEmail1", "$2a$12$kVlFNkyutY6qXRyT/JRdUusQLZp6k1MXS47eeKDJh3PBtgHNof2sO" , "nameUser1" , 5000f);    //fred
        //bCryptPasswordEncoder.encode("pass1")
        users[1] = new User("giverEmail2", "$2a$12$Ko/Ai7TyQwfVAFYS42jfl.YeP01OpDIoU7b6spyMXz7FWbPC4XYWC" , "nameUser2" , 200f); //mike
        //bCryptPasswordEncoder.encode("pass2")
        users[2] = new User("giverEmail3", "$2a$12$xESGiGC8PVYoeMa3pC4TXOp/AYm8WVvuHqh5dTf6sbwN9c8nvNNRC", "nameUser3" , 0f);   //cindy
        //bCryptPasswordEncoder.encode("pass3")
        users[3] = new User("giverEmail4", "$2a$12$DxOiBwpuYZStOBfhzYvWWeEzZFcg96WLgMIOMwNLfkWPgtlf7qzb2" , "nameUser4" , 5000f);    //jack
        //bCryptPasswordEncoder.encode("pass4")
        userRepository.saveAll(Arrays.asList(users));

        Contact[] contacts = new Contact[5];

        ContactEmbeddedId contactEmbeddedId1 = new ContactEmbeddedId("giverEmail1", "giverEmail4");
        ContactEmbeddedId contactEmbeddedId2 = new ContactEmbeddedId("giverEmail1", "giverEmail2");
        ContactEmbeddedId contactEmbeddedId3 = new ContactEmbeddedId("giverEmail1", "giverEmail3");
        ContactEmbeddedId contactEmbeddedId4 = new ContactEmbeddedId("giverEmail2", "giverEmail4");
        ContactEmbeddedId contactEmbeddedId5 = new ContactEmbeddedId("giverEmail2", "giverEmail3");
        contacts[0] = new Contact("jack", contactEmbeddedId1);
        contacts[1] = new Contact("mike", contactEmbeddedId2);
        contacts[2] = new Contact("cindy", contactEmbeddedId3);
        contacts[3] = new Contact("friend of mike - jack", contactEmbeddedId4);
        contacts[4] = new Contact("friend of mike - cindy", contactEmbeddedId5);
        contactRepository.saveAll(Arrays.asList(contacts));

        MoneyTransaction[] moneyTransactions = new MoneyTransaction[4];

        User userReceiver = new User("userMoneyTransactionEmail", "userMoneyTransactionPassword","nameReceiver",  200f);
        //bCryptPasswordEncoder.encode("userMoneyTransactionPassword")

        userRepository.save(userReceiver);
        moneyTransactions[0] = new MoneyTransaction("giverEmail1", userReceiver, "description1", 15);
        moneyTransactions[1] = new MoneyTransaction("giverEmail2", userReceiver, "description2", 456);
        moneyTransactions[2] = new MoneyTransaction("giverEmail3", userReceiver, "description3", 78);
        moneyTransactions[3] = new MoneyTransaction("giverEmail4", userReceiver, "description4", 21);
        moneyTransactionRepository.saveAll(Arrays.asList(moneyTransactions));
    }
}
