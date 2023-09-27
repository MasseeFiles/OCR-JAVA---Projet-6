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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataInsertService {
    private UserRepository userRepository;
    private ContactRepository contactRepository;
    private MoneyTransactionRepository moneyTransactionRepository;

    //Constructeur
    public DataInsertService(ContactRepository contactRepository, MoneyTransactionRepository moneyTransactionRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.moneyTransactionRepository = moneyTransactionRepository;
        this.userRepository = userRepository;

        MoneyTransaction[] moneyTransactions = new MoneyTransaction[4];
        User userMoneyTransaction = new User();
        userMoneyTransaction.setFirstName("bob");

        moneyTransactions[0] = new MoneyTransaction("giverEmail1", userMoneyTransaction , "description1", 15);
        moneyTransactions[1] = new MoneyTransaction("giverEmail2", userMoneyTransaction , "description2", 456);
        moneyTransactions[2] = new MoneyTransaction("giverEmail3", userMoneyTransaction , "description3", 78);
        moneyTransactions[3] = new MoneyTransaction("giverEmail4", userMoneyTransaction , "description4", 21);

        moneyTransactionRepository.saveAll(Arrays.asList(moneyTransactions));

        Contact[] contacts = new Contact[4];

        ContactIdEmbeddedId contactIdEmbeddedId = new ContactIdEmbeddedId("giverEmail1", "giverEmail4");

        contacts[0] = new Contact("bob", contactIdEmbeddedId);
        contacts[1] = new Contact("mike", contactIdEmbeddedId);
        contacts[2] = new Contact("kim", contactIdEmbeddedId);
        contacts[3] = new Contact("cindy", contactIdEmbeddedId);
        contactRepository.saveAll(Arrays.asList(contacts));
    }

    public List<MoneyTransaction> getMoneyTransactions() {

        Iterable<MoneyTransaction> iterable = moneyTransactionRepository.findAll();
        List<MoneyTransaction> result = new ArrayList<MoneyTransaction>();
        iterable.forEach( (moneyTransaction) -> {
            result.add((MoneyTransaction) moneyTransaction);
        });
        return result;
    }

    public List<Contact> getContacts() {

        Iterable<Contact> iterable = contactRepository.findAll();
        List<Contact> result = new ArrayList<Contact>();
        iterable.forEach( (contact) -> {
            result.add((Contact) contact);
        });
        return result;
        }
}
