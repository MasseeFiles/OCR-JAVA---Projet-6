package com.paymybuddy.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity //annotation mappage objet sql / objet java
@Table(name = "user")  //lien direct entre objet java et table associée
public class User {
    @Id //specifie le field à utiliser en PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_email")
    private String userEmail;
    //@NotBlank(message = "Username is mandatory")    //dependance maven à ajouter ex: hibernate validator

    @Column(name = "password")
    private String password;
    //@NotBlank(message = "Password is mandatory")

    @Column(name = "balance")
    private float balance;  //a initialiser lors d'instanciation
}
