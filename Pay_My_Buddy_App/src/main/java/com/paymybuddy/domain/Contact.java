package com.paymybuddy.domain;

import jakarta.persistence.*;
import lombok.Data;

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

        /*@Id //clef primaire
        @GeneratedValue(strategy = GenerationType.IDENTITY) //mode de generation de l'attribut - en rapport avec identity column de la BDD
        @Column(name = "money_transaction_id")
        private int moneyTransactionId;*/
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
