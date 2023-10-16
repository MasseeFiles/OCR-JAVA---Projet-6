package com.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "money_transactions")
public class MoneyTransaction {
    @Column(name = "giver_Email")
    private String giverEmail;

    @ManyToOne
    @JoinColumn(name = "receiver_email")
    private User receiver;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private float amount;

    @Id //clef primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //mode de generation de l'attribut - en rapport avec identity column de la BDD
    @Column(name = "money_transaction_id")
    private int moneyTransactionId;

    public MoneyTransaction(String giverEmail, User receiver, String description, float amount) {
        this.giverEmail = giverEmail;
        this.receiver = receiver;
        this.description = description;
        this.amount = amount;
    }
}
