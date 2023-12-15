package com.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contacts")
public class Contact {
    @Id //specifie le field à utiliser en PK
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactID;

    @ManyToOne
    @JoinColumn(name = "origin_user_id", referencedColumnName = "user_id")
    private User originUser;

    @ManyToOne
    @JoinColumn(name = "other_user_id", referencedColumnName = "user_id")
    private User otherUser;

    @Column(name = "name_contact")
    private String nameContact;

//    @EmbeddedId     //      clef primaire composite avec @EmbeddedId (originEmail et otherEmail)
//    private ContactEmbeddedId contactEmbeddedId;

    public Contact() {
    }

    public Contact(String nameContact, User originUser, User otherUser) {
        this.nameContact = nameContact;
        this.originUser = originUser;
        this.otherUser = otherUser;

    }
}
