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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
public class MoneyTransactionControllerTest {       //Tests d'integration!!!
    @Autowired
    private MockMvc mockMvc;  //mock qui envoie les request http

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "giverEmail1")
    void shouldReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders     //methode perform sert à envoyer la request lors du test
                        .get("/transfer"))
                .andExpect(MockMvcResultMatchers    //methode andExpect() sert à definir les assertions. Renvoie un objet  ResultActions
                        .status().isOk())  //method status() pour le status http retourné
                .andExpect(MockMvcResultMatchers
                        .view().name("transfer"));
    }

    @Test
    @WithMockUser(username = "giverEmail1")
    void shouldReturnLists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/transfer"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("moneyTransactions"))  //verifier si liste???
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("contacts"));
// comment sortir du model (reponse http) l'objet moneyTransactions???

//
//        listTest = mockMvc.andReturn().getContent().getMoneyTransactions();
//        assert (listTest instanceof List<MoneyTransaction>);
    }

    @Test
    @WithMockUser(username = "giverEmail1")
    void shouldChangeBalances() throws Exception {
        Float balanceGiverInput = userRepository.findById("giverEmail1").map(user -> user.getBalance()).orElseThrow();
        Float balanceReceiverInput = userRepository.findById("giverEmail2").map(user -> user.getBalance()).orElseThrow();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/transferRequest")
                        .param("contactIdEmbeddedIdOtherEmail", "giverEmail2")
                        .param("transferAmount", "100")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/transfer"));

        Float balanceGiverOutput = userRepository.findById("giverEmail1").map(user -> user.getBalance()).orElseThrow();
        Float balanceReceiverOutput = userRepository.findById("giverEmail2").map(user -> user.getBalance()).orElseThrow();

        assertEquals(balanceGiverOutput, (balanceGiverInput - 100));
        assertEquals(balanceReceiverOutput, (balanceReceiverInput + 100));
    }

//    @Test
//    @WithMockUser(username = "giverEmail2")
//        //balance à 200
//    void shouldThrowRuntimeException() throws Exception {
//        Float balanceGiverInput = userRepository.findById("giverEmail2").map(user -> user.getBalance()).orElseThrow();
//        Float balanceReceiverInput = userRepository.findById("giverEmail1").map(user -> user.getBalance()).orElseThrow();
//
////        Throwable exception = assertThrows(RuntimeException.class, () -> {
////            mockMvc.perform(MockMvcRequestBuilders
////                    .post("/transferRequest").param("contactIdEmbeddedIdOtherEmail", "giverEmail1").param("transferAmount", "300").with(csrf()))
////            throw new RuntimeException();
////        });
//    }

}


