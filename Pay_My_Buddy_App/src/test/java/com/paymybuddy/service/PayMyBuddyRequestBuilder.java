package com.paymybuddy.service;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class PayMyBuddyRequestBuilder { //definit le contenu de la requete http envoyée lors du test
    private final MockMvc mockMvc;

    public PayMyBuddyRequestBuilder(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
}
