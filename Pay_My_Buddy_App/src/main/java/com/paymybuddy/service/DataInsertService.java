package com.paymybuddy.service;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.ContactIdEmbeddedId;
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
        users[0] = new User("giverEmail1", bCryptPasswordEncoder.encode("pass1"), "nameUser1" , 5000f);
        users[1] = new User("giverEmail2", "pass2", "nameUser2" , 200f);
        users[2] = new User("giverEmail3", "pass3", "nameUser3" , 0f);
        users[3] = new User("giverEmail4", "pass4", "nameUser4" , 5000f);
        userRepository.saveAll(Arrays.asList(users));

        Contact[] contacts = new Contact[4];
        ContactIdEmbeddedId contactIdEmbeddedId1 = new ContactIdEmbeddedId("giverEmail1", "giverEmail4");
        ContactIdEmbeddedId contactIdEmbeddedId2 = new ContactIdEmbeddedId("giverEmail1", "giverEmail2");
        ContactIdEmbeddedId contactIdEmbeddedId3 = new ContactIdEmbeddedId("giverEmail1", "giverEmail3");
        ContactIdEmbeddedId contactIdEmbeddedId4 = new ContactIdEmbeddedId("giverEmail1", "giverEmail4");

        contacts[0] = new Contact("bob", contactIdEmbeddedId1);
        contacts[1] = new Contact("mike", contactIdEmbeddedId2);
        contacts[2] = new Contact("jack", contactIdEmbeddedId3);
        contacts[3] = new Contact("cindy", contactIdEmbeddedId4);
        contactRepository.saveAll(Arrays.asList(contacts));

        MoneyTransaction[] moneyTransactions = new MoneyTransaction[4];
        User userReceiver = new User("userMoneyTransactionEmail", "userMoneyTransactionPassword","nameReceiver",  200f);
        userRepository.save(userReceiver);
        moneyTransactions[0] = new MoneyTransaction("giverEmail1", userReceiver, "description1", 15);
        moneyTransactions[1] = new MoneyTransaction("giverEmail2", userReceiver, "description2", 456);
        moneyTransactions[2] = new MoneyTransaction("giverEmail3", userReceiver, "description3", 78);
        moneyTransactions[3] = new MoneyTransaction("giverEmail4", userReceiver, "description4", 21);
        moneyTransactionRepository.saveAll(Arrays.asList(moneyTransactions));
    }
}
