package com.paymybuddy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class ContactEmbeddedId implements Serializable {
    @Column(name = "origin_email")
    private String originEmail;
    @Column(name = "other_email")
    private String otherEmail;

    public ContactEmbeddedId() {
    }
    public ContactEmbeddedId(String originEmail, String otherEmail) {
        this.originEmail = originEmail;
        this.otherEmail = otherEmail;
    }
}
