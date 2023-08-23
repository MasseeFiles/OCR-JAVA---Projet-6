package com.paymybuddy.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ContactID implements Serializable {
    private String originEmailPk;
    private String otherEmailPk;
}
