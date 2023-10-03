package com.paymybuddy.service;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.ContactIdEmbeddedId;
import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ContactRepository;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DataInsertService {
    //Constructeur
    public DataInsertService(ContactRepository contactRepository, MoneyTransactionRepository moneyTransactionRepository, UserRepository userRepository) {

        User[] users = new User[5];
        users[0] = new User("giverEmail1", "pass3", "first" , 5000f);
        users[1] = new User("giverEmail2", "pass4", "first" , 200f);
        users[2] = new User("giverEmail3", "pass5","first" ,  0f);
        users[3] = new User("giverEmail4", "pass6", "first" , 5000f);
        users[4] = new User("giverToCheck", "passGiverToCheck", "first", 5000f);
        userRepository.saveAll(Arrays.asList(users));

        Contact[] contacts = new Contact[4];
        ContactIdEmbeddedId contactIdEmbeddedId = new ContactIdEmbeddedId("giverEmail1", "giverEmail4");
        contacts[0] = new Contact("bob", contactIdEmbeddedId);
        contacts[1] = new Contact("mike", contactIdEmbeddedId);
        contacts[2] = new Contact("jack", contactIdEmbeddedId);
        contacts[3] = new Contact("cindy", contactIdEmbeddedId);
        contactRepository.saveAll(Arrays.asList(contacts));

        MoneyTransaction[] moneyTransactions = new MoneyTransaction[4];
        User userReceiver = new User("userMoneyTransactionEmail", "userMoneyTransactionPassword","firstName1",  200f);

        userRepository.save(userReceiver);
        moneyTransactions[0] = new MoneyTransaction("giverEmail1", userReceiver, "description1", 15);
        moneyTransactions[1] = new MoneyTransaction("giverEmail2", userReceiver, "description2", 456);
        moneyTransactions[2] = new MoneyTransaction("giverEmail3", userReceiver, "description3", 78);
        moneyTransactions[3] = new MoneyTransaction("giverEmail4", userReceiver, "description4", 21);
        moneyTransactionRepository.saveAll(Arrays.asList(moneyTransactions));
    }
}
