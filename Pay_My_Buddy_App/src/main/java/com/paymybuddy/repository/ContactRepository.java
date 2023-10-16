package com.paymybuddy.repository;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.ContactIdEmbeddedId;
import com.paymybuddy.model.MoneyTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
    Iterable<Contact> findAllByContactIdEmbeddedId(ContactIdEmbeddedId contactIdEmbeddedIds);

}
