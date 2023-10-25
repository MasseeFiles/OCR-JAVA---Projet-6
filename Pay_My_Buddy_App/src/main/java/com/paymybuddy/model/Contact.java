package com.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//@IdClass(ContactIdIdClass)      //clef primaire composite avec @IdClass
@Table(name = "contacts")
public class Contact {
        @Column(name = "name_contact")
        private String nameContact;

        @EmbeddedId     //      clef primaire composite avec @EmbeddedId (originEmail et otherEmail)
        private ContactIdEmbeddedId contactIdEmbeddedId;

        public Contact() {
        }
        public Contact(String nameContact, ContactIdEmbeddedId contactIdEmbeddedId) {
                this.nameContact = nameContact;
                this.contactIdEmbeddedId = contactIdEmbeddedId;
        }
}
