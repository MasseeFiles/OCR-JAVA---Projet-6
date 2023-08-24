package com.paymybuddy.model;

import java.io.Serializable;

public class ContactIdIdClass implements Serializable {
    private String originEmail;
    private String otherEmail;

    public ContactIdIdClass(String originEmail, String otherEmail) {
        this.originEmail = originEmail;
        this.otherEmail = otherEmail;
    }
}
