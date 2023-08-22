package com.paymybuddy.domain;

import jakarta.persistence.*;
import lombok.Data;

    @Data
    @Entity
    @Table(name = "money_transaction")
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
        @GeneratedValue(strategy = GenerationType.IDENTITY) //mode de generation de l'attribut - en rapport avec identity column de la BDD
        @Column(name = "money_transaction_id")
        private int moneyTransactionId;
/*
        public String getGiverEmail() {
            return giverEmail;
        }

        public void setGiverEmail(String giverEmail) {
            this.giverEmail = giverEmail;
        }

        public String getReceiverEmail() {
            return receiverEmail;
        }

        public void setReceiverEmail(String receiverEmail) {
            this.receiverEmail = receiverEmail;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public int getMoneyTransactionId() {
            return moneyTransactionId;
        }

        public void setMoneyTransactionId(int moneyTransactionId) {
            this.moneyTransactionId = moneyTransactionId;
        }


//@EmbeddedId //specifie le field à utiliser en PK
        //private PrimaryKeyContactId id;
    }

*/


}
