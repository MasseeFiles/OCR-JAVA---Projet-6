package com.paymybuddy.service;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.ContactIdEmbeddedId;
import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ContactRepository;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DataInsertService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;

    //Constructeur
    public DataInsertService(ContactRepository contactRepository, MoneyTransactionRepository moneyTransactionRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.moneyTransactionRepository = moneyTransactionRepository;
        this.userRepository = userRepository;

        MoneyTransaction[] moneyTransactions = new MoneyTransaction[4];
        User userMoneyTransaction = new User("userMoneyTransactionEmail", "userMoneyTransactionPassword", 200f);
        userRepository.save(userMoneyTransaction);

        moneyTransactions[0] = new MoneyTransaction("giverEmail1", userMoneyTransaction, "description1", 15);
        moneyTransactions[1] = new MoneyTransaction("giverEmail2", userMoneyTransaction, "description2", 456);
        moneyTransactions[2] = new MoneyTransaction("giverEmail3", userMoneyTransaction, "description3", 78);
        moneyTransactions[3] = new MoneyTransaction("giverEmail4", userMoneyTransaction, "description4", 21);
        moneyTransactionRepository.saveAll(Arrays.asList(moneyTransactions));

        Contact[] contacts = new Contact[4];
        ContactIdEmbeddedId contactIdEmbeddedId = new ContactIdEmbeddedId("giverEmail1", "giverEmail4");
        contacts[0] = new Contact("bob", contactIdEmbeddedId);
        contacts[1] = new Contact("mike", contactIdEmbeddedId);
        contacts[2] = new Contact("kim", contactIdEmbeddedId);
        contacts[3] = new Contact("cindy", contactIdEmbeddedId);
        contactRepository.saveAll(Arrays.asList(contacts));

        User[] users = new User[3];
        users[0] = new User("bob", "pass1", 200f);
        users[1] = new User("mike", "pass2", 0f);
        users[2] = new User("kim", "pass3", 5000f);
        userRepository.saveAll(Arrays.asList(users));

    }
    //methode pour appeler objet datainsertService???
//    public void addContact(Contact contactToAdd) {contactRepository.save(contactToAdd);
//    }

}
