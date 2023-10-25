package com.paymybuddy.service;

import com.paymybuddy.repository.UserRepository;
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
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "userEmailTest")
    void shouldAddUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .param("userEmail", "userEmailTest")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/user/homepage"));

        assertTrue(userRepository.existsById("userEmailTest"));
    }
}
