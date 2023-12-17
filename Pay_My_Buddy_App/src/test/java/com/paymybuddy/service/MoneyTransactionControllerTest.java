package com.paymybuddy.service;

import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc

public class MoneyTransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "userEmail1")
    void shouldReturnStatus_OkAndView_Transfer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders     //methode perform sert à envoyer la request lors du test
                        .get("/transfer"))
                .andExpect(MockMvcResultMatchers    //methode andExpect() sert à definir les assertions. Renvoie un objet  ResultActions
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("transfer"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void shouldReturnModelUpdated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/transfer"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("moneyTransactions"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("contacts"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void shouldChangeBalances() throws Exception {
        Float balanceGiverInput = userRepository.findByUserEmail("userEmail1").map(User::getBalance).orElseThrow();
        Float balanceReceiverInput = userRepository.findByUserEmail("userEmail2").map(user -> user.getBalance()).orElseThrow();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/transfer")
                .param("otherUserEmail", "userEmail2")
                .param("transferAmount", "100")
                .with(csrf()));

        Float balanceGiverOutput = userRepository.findByUserEmail("userEmail1").map(user -> user.getBalance()).orElseThrow();
        Float balanceReceiverOutput = userRepository.findByUserEmail("userEmail2").map(user -> user.getBalance()).orElseThrow();

        assertEquals(balanceGiverOutput, (balanceGiverInput - 100));
        assertEquals(balanceReceiverOutput, (balanceReceiverInput + 100));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void shouldReturnView_Redirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/transfer")
                        .param("otherUserEmail", "userEmail2")
                        .param("transferAmount", "100")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/transfer"));
    }
}


