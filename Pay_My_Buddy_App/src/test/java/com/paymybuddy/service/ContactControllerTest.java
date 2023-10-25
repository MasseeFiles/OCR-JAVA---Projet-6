package com.paymybuddy.service;

import com.paymybuddy.model.ContactIdEmbeddedId;
import com.paymybuddy.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    @WithMockUser(username = "giverContactTest")
    void shouldReturnRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/contact")
                        .param("nameContact", "nameContactTest")
                        .param("originEmail", "giverContactTest")    //pour ID contactIdEmbeddedId
                        .param("otherEmail", "receiverContactTest")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers    //methode andExpect() sert à definir les assertions. Renvoie un objet  ResultActions
                        .status().is3xxRedirection());
//                .andExpect(MockMvcResultMatchers
//                        .view().name("redirect:/user/homepage"));

        ContactIdEmbeddedId contactIdEmbeddedIdTest = new ContactIdEmbeddedId("giverContactTest", "receiverContactTest");
        assertTrue(contactRepository.existsById(contactIdEmbeddedIdTest.toString()));   //lambda???
    }
// pas possible de tester si pas de sauvegarde d'un contact
//    @Test
//    @WithMockUser(username = "giverContactTest")
//    void shouldReturnError() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/contact")
//                        .param("nameContact", "nameContactTest")
//                        .param("originEmail", "giverContactTest")
//                        .param("otherEmail", "giverContactTest")
//                .with(csrf()))
//                .andExpect(MockMvcResultMatchers    //methode andExpect() sert à definir les assertions. Renvoie un objet  ResultActions
//                        .status().isOk())
//                .andExpect(MockMvcResultMatchers
//                        .view().name("error"));
//
//
//    }
}