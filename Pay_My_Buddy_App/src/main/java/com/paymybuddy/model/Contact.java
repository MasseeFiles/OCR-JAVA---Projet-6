package com.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "contact")
public class Contact {
        @Column(name = "origin_email")
        private String originEmail;

        @Column(name = "other_email")
        private String otherEmail;
        //@NotBlank(message = "Connection email is mandatory")

        @Column(name = "name_contact")
        private String nameContact;
        //@NotBlank(message = "Connection name is mandatory")

        @EmbeddedId //annotation pour déclarer une primary key composite (classe particuliere composée de originEmail et otherEmail)
        private ContactID contactID;



/*
    public String getOriginEmail() {
        return originEmail;
    }

    public void setOriginEmail(String originEmail) {
        this.originEmail = originEmail;
    }

    public String getOtherEmail() {
        return otherEmail;
    }

    public void setOtherEmail(String otherEmail) {
        this.otherEmail = otherEmail;
    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }
    */
}
