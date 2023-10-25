package com.paymybuddy.service;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.ContactIdEmbeddedId;
import com.paymybuddy.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    @WithMockUser(username = "giverEmail1")
    void shouldAddContact() throws Exception {
        Contact contactExpected = new Contact();
        contactExpected.setNameContact("nameContactTest");
        ContactIdEmbeddedId contactIdEmbeddedIdExpected = new ContactIdEmbeddedId("giverContactTest", "giverContactTest");
        contactExpected.setContactIdEmbeddedId(contactIdEmbeddedIdExpected);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/contact")
//                        .param("nameContact", "nameContactTest")
//                        .param("originEmail", "giverContactTest")
//                        .param("otherEmail", "giverContactTest")
                .param("contactToAdd", "contactExpected")
                .with(csrf()));
//                .andExpect(MockMvcResultMatchers    //methode andExpect() sert à definir les assertions. Renvoie un objet  ResultActions
//                        .status().isOk())
//                .andExpect(MockMvcResultMatchers
//                        .view().name("redirect:/user/homepage"));

        Optional<Contact> contactActual = contactRepository.findById("contactIdEmbeddedIdExpected");   //type attendu string ou objet
//                .orElseThrow(() -> new RuntimeException("Contact not found : Id used " + contactIdEmbeddedIdExpected));
        assertEquals(contactExpected, contactActual);
    }
}