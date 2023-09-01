package com.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "money_transactions")
public class MoneyTransaction {
    @Column(name = "giver_Email")
    private String giverEmail;

    @Column(name = "receiver_email")
    private String receiverEmail;
    //@NotBlank(message = "Connection email is mandatory")

    @Column(name = "description")
    private String description;
    //@NotBlank(message = "Connection name is mandatory")

    @Column(name = "amount")
    private float amount;

    @Id //clef primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //mode de generation de l'attribut - en rapport avec identity column de la BDD
    @Column(name = "money_transaction_id")
    private int moneyTransactionId;

    public MoneyTransaction(String giverEmail1, String receiverEmail1, String description1, int i) {
    }
}
