package com.paymybuddy.service;

import com.paymybuddy.model.Contact;
import com.paymybuddy.model.ContactIdEmbeddedId;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ContactRepository;
import com.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "userEmailTest")
    void shouldAddUser() throws Exception {
        User userExpected = new User();
        userExpected.setUserEmail("userEmailTest");


        mockMvc.perform(MockMvcRequestBuilders
                .post("/user")
                .param("userToAdd", "userExpected")
                .with(csrf()))
                .andExpect(MockMvcResultMatchers    //methode andExpect() sert à definir les assertions. Renvoie un objet  ResultActions
                        .status().isOk());
//                .andExpect(MockMvcResultMatchers
//                        .view().name("redirect:/user/homepage"));

        User userActual = userRepository.findById("userEmailTest") //singleton???
                .orElseThrow(() -> new RuntimeException("User not found : Id used " + userExpected.getUserEmail()));

        assertEquals(userExpected, userActual);
    }
}
