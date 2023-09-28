package com.paymybuddy.service;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.MoneyTransaction;
import com.paymybuddy.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void addContact(Contact contactToAdd) {contactRepository.save(contactToAdd);
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = (List<Contact>) contactRepository.findAll();
        return contacts;
//        return (List<Contact>) contactRepository.findAll();
    }
}


