package com.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity //annotation mappage objet sql / objet java
@Table(name = "users")  //lien direct entre objet java et table associée
public class User {

    @Id //specifie le field à utiliser en PK
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_email" , unique = true)
    private String userEmail;

    @Column(name = "password")
    private String password;

    @Column()
    private String firstName;

    @Column()
    private String lastName;

    @Column(name = "balance")
    private float balance;

    @OneToMany( //definit impact de l'action d'une entity sur entity jointe (User et Contact) - attributs dessous reglent des situations particulieres
            cascade = CascadeType.ALL,       //suppression de User entraine suppression des objets contact associés
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "origin_email" )    //doit specifier nom de clef etrangere
    private List<Contact> contacts = new ArrayList<Contact>();

    // Constructeurs
    public User() {
    }

    public User(String userEmail , String password , String firstName , String lastName, float balance) {
        this.userEmail = userEmail;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }
}
