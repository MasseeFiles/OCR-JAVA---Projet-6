package com.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "money_transactions")
public class MoneyTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "money_transaction_id")
    private int moneyTransactionId;

    @Column(name = "giver_Email")
    private String giverEmail;

    @ManyToOne
    @JoinColumn(name = "receiver_email",  referencedColumnName = "user_email")
    private User receiver;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private float amount;



    public MoneyTransaction(String giverEmail, User receiver, String description, float amount) {
        this.giverEmail = giverEmail;
        this.receiver = receiver;
        this.description = description;
        this.amount = amount;
    }
}
