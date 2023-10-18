package com.paymybuddy.service;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//Request builder class  : definit le contenu de la requete http et l'envoie lors du test
//                        les methodes definies renvoyent un objet resultactions sur lesquel on peut faire des assertions

public class PayMyBuddyRequestBuilder {
    private final MockMvc mockMvc;  //mock qui envoie les request http

    public PayMyBuddyRequestBuilder(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


    ResultActions getTransferPage() throws Exception {
        return mockMvc.perform(get("/transfer"));
    }

    ResultActions postTransferRequest() throws Exception {
        return mockMvc.perform(post("/transferRequest"));
    }





}
