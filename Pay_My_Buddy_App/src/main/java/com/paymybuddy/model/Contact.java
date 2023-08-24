package com.paymybuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//@IdClass(ContactIdIdClass)      //clef primaire composite avec @IdClass
@Table(name = "contacts")
public class Contact {
        @Column(name = "origin_email")
        private String originEmail;

        @Column(name = "other_email")
        private String otherEmail;
        //@NotBlank(message = "Connection email is mandatory")

        @Column(name = "name_contact")
        private String nameContact;
        //@NotBlank(message = "Connection name is mandatory")

        @EmbeddedId     //      clef primaire composite avec @EmbeddedId (originEmail et otherEmail)
        private ContactIdEmbeddedId contactIdEmbeddedId;

/*      clef primaire composite avec @IdClass (originEmail et otherEmail)

        @Id
        private String originEmail;

        @Id
        private String otherEmail;
         */
}
