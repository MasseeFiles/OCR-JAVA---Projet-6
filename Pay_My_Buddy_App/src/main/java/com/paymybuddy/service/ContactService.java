package com.paymybuddy.service;

import com.paymybuddy.model.Contact;
import com.paymybuddy.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void addContact(Contact contactToAdd) {
        contactRepository.save(contactToAdd);
    }
}


