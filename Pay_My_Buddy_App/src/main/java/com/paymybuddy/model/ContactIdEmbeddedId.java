package com.paymybuddy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class ContactIdEmbeddedId implements Serializable {
    @Column(name = "origin_email")
    private String originEmail;
    @Column(name = "other_email")
    private String otherEmail;

    public ContactIdEmbeddedId() {
    }
    public ContactIdEmbeddedId(String originEmail, String otherEmail) {
        this.originEmail = originEmail;
        this.otherEmail = otherEmail;
    }
}
