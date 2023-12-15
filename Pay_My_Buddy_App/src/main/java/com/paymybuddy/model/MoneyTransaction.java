package com.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "money_transactions")
public class MoneyTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "money_transaction_id")
    private int moneyTransactionId;

//    @Column(name = "giver_email")
//    private String giverEmail;

    @ManyToOne
    @JoinColumn(name = "giver_id", referencedColumnName = "user_id")
    private User giver;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User receiver;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private float amount;

    public MoneyTransaction(User giver, User receiver, String description, float amount) {
        this.giver = giver;
        this.receiver = receiver;
        this.description = description;
        this.amount = amount;
    }
}
