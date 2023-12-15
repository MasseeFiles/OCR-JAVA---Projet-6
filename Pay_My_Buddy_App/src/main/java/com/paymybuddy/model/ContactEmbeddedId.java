package com.paymybuddy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ContactEmbeddedId implements Serializable {
//    @Column(name = "origin_email")
//    private String originEmail;
    @ManyToOne
    @JoinColumn(name = "origin_email", referencedColumnName = "user_email")
    private User originUser;

//    @Column(name = "other_email")
//    private String otherEmail;
    @ManyToOne
    @JoinColumn(name = "other_email", referencedColumnName = "user_email")
    private User otherUser;

    public ContactEmbeddedId() {
    }

    public ContactEmbeddedId(User originUser, User otherUser) {
        this.originUser = originUser;
        this.otherUser = otherUser;
    }
}
