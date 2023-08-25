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
    @Column(name = "user_email")
    private String userEmail;
    //@NotBlank(message = "Username is mandatory")    //dependance maven à ajouter ex: hibernate validator

    @Column(name = "password")
    private String password;
    //@NotBlank(message = "Password is mandatory")

    @Column()
    private String firstName;  //a initialiser lors d'instanciation

    @Column()
    private String lastName;  //a initialiser lors d'instanciation

    @Column(name = "balance")
    private float balance;  //a initialiser lors d'instanciation

    @OneToMany( //definit impact de l'action d'une entity sur entity jointe (User et Contact) - attributs dessous reglent des situations particulieres
            cascade = CascadeType.ALL,       //suppression de User entraine suppressionn des objets contact associés
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "origin_email" )    //doit specifier nom de clef etrangere
    private List<Contact> contacts = new ArrayList<Contact>();
}
