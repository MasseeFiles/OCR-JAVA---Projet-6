package com.paymybuddy.service;

import com.paymybuddy.controllers.MoneyTransactionController;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

//@WebMvcTest(MoneyTransactionController.class) //autoconfigure mockmvc + autre parametres necerssaire à tests
@SpringBootTest
@AutoConfigureMockMvc
public class MoneyTransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;  //mock qui envoie les request http

    @MockBean
    private MoneyTransactionService moneyTransactionService;       //mock d'un collaborateur du controlleur
    @MockBean
    private UserService userService;        //mock d'un collaborateur du controlleur
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MoneyTransactionRepository moneyTransactionRepository;




    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }


    //methode 2 - perform
    @Test
    @WithMockUser

    void shouldReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders     //methode perform sert à envoyer la request lors du test
                        .get("/transfer"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk())  //method status() pour le status http retourné
                .andExpect(MockMvcResultMatchers
                        .view().name("transfer"));
    }
}

//    @Test
//@WithMockUser

//    void shouldReturnListsNotEmpty() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders
//                        .get("/transfer"))
//                .andExpect(MockMvcResultMatchers
//                        .model().attributeExists("moneyTransactions"))  //verifier si liste???
//                .andExpect(MockMvcResultMatchers
//                        .model().attributeExists("contacts"));
//    }

// ppur mocker un collaborateur du controller  :
//    @MockBean
//        private UserService userService;

//    assertion pour verification de appel d'une method sur un objet particulier - allow
//    verify(userService).allowpayement(any(Moneutransaction.class)


//    @BeforeEach
//    public void configureSystemUnderTest() {
//        moneyTransactionService = mock(MoneyTransactionService.class);
//
//        MoneyTransactionController moneyTransactionControllerTested = new MoneyTransactionController();
//        MockMvc mockMvc = MockMvcBuilders
//                .standaloneSetup(moneyTransactionControllerTested)
////          .setHandlerExceptionResolvers(exceptionResolver())
////          .setLocaleResolver(fixedLocaleResolver())
////          .setViewResolvers(jspViewResolver())
//                .build();
//
//        requestBuilder = new PayMyBuddyRequestBuilder(mockMvc);   //introduction du mockmvc
//    }

//methode 1 - requestBuilder

//        @Test
//@WithMockUser

//        void shouldReturn_Status_ok() throws Exception {
//            requestBuilder.getTransferPage()
//                    //methode andExpect() sert à definir les assertions. Renvoie un objet  ResultActions
//                    .andExpect(status().isOk())   //method de MockMvcResultMatchers sur status http
//                    .andExpect(view().name("transfer"));                          // pertinent pour un test unitaire???
//        }
