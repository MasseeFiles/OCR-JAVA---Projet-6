package com.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//@IdClass(ContactId)      //clef primaire composite avec @IdClass
@Table(name = "contacts")
public class Contact {
        @Column(name = "name_contact")
        private String nameContact;

        @EmbeddedId     //      clef primaire composite avec @EmbeddedId (originEmail et otherEmail)
        private ContactEmbeddedId contactEmbeddedId;

        public Contact() {
        }
        public Contact(String nameContact, ContactEmbeddedId contactEmbeddedId) {
                this.nameContact = nameContact;
                this.contactEmbeddedId = contactEmbeddedId;
        }
}
