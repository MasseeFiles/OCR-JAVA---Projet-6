package com.paymybuddy.service;

import com.paymybuddy.model.Contact;
import com.paymybuddy.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Iterable<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public void addContact(Contact contactToAdd) {
        contactRepository.save(contactToAdd);
    }

    public void updateContact(Contact contactToUpdate) {
        contactRepository.save(contactToUpdate);
    }

    public void deleteContact(Contact contactToDelete) {
        contactRepository.delete(contactToDelete);
    }
}


