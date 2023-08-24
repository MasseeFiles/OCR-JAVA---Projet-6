package com.paymybuddy.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class ContactIdEmbeddedId implements Serializable {
    private String originEmail;
    private String otherEmail;

    public ContactIdEmbeddedId(String originEmail, String otherEmail) {
        this.originEmail = originEmail;
        this.otherEmail = otherEmail;
    }
}
