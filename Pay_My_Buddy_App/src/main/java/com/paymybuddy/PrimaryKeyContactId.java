package com.paymybuddy;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class ContactId implements Serializable {
    private String originEmailId;
    private String connectionEmailId;

    // Constructors, getters, setters, equals, hashCode
}