package com.paymybuddy.service;

import com.paymybuddy.controllers.MoneyTransactionController;
import com.paymybuddy.model.MoneyTransactionDto;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
public class MoneyTransactionControllerTest {       //Tests d'integration!!!
    @Autowired
    private MockMvc mockMvc;  //mock qui envoie les request http

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }


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
    void shouldReturnListsNotEmpty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/transfer"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("moneyTransactions"))  //verifier si liste???
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("contacts"));

    }

//    @Test
//    @WithMockUser(username = "giverEmail1")
//    void shouldUseAllowPaymentMethod() throws Exception {
//        MoneyTransactionDto moneyTransactionDtoTest = new MoneyTransactionDto( "giverEmail2" ,  100);//ajout de constructeur- a enlever si necessaire
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/transferRequest").param("moneyTransactionDtoTest"))   //PB / COMMETN PASSR LE PARAMETEE moneyTransactionDtoTest
//                .andExpect(MockMvcResultMatchers
//                        .view().name("redirect:/transfer"));
//
////        verify(userService).allowpayement(any(Moneytransaction.class)     //    assertion pour verification de l'appel de method allowpayement sur un objet service
//    }

//    @Test
//    @WithMockUser(username = "giverEmail1")
    //    void shouldThrowRuntimeException(){
//
//    //THEN
//    Throwable exception = assertThrows(
//        RuntimeException.class,
//            () -> {
//                throw new RuntimeException("ex");
//            }
//    );
//    assertEquals("Exception message", exception.getMessage());
//}
//
//        verify(userService).allowpayement(any(Moneytransaction.class)     //    assertion pour verification de l'appel de method allowpayement sur un objet service
//    }
}






//methode 1 - requestBuilder

//        @Test
//@WithMockUser

//        void shouldReturn_Status_ok() throws Exception {
//            requestBuilder.getTransferPage()
//                    //methode andExpect() sert à definir les assertions. Renvoie un objet  ResultActions
//                    .andExpect(status().isOk())   //method de MockMvcResultMatchers sur status http
//                    .andExpect(view().name("transfer"));                          // pertinent pour un test unitaire???
//        }
