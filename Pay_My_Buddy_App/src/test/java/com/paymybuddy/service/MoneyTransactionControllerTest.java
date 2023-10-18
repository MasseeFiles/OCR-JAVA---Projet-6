package com.paymybuddy.service;

import com.paymybuddy.controllers.MoneyTransactionController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MoneyTransactionControllerTest {
    private PayMyBuddyRequestBuilder requestBuilder; //generateur de requetes http
    private MoneyTransactionService moneyTransactionService;

 @BeforeEach
 public void configureSystemUnderTest() {
     moneyTransactionService = mock(MoneyTransactionService.class);

     MoneyTransactionController moneyTransactionControllerTested = new MoneyTransactionController();
     MockMvc mockMvc = MockMvcBuilders
             .standaloneSetup(moneyTransactionControllerTested)
//          .setHandlerExceptionResolvers(exceptionResolver())
//          .setLocaleResolver(fixedLocaleResolver())
//          .setViewResolvers(jspViewResolver())
             .build();

     requestBuilder = new PayMyBuddyRequestBuilder(mockMvc);   //introduction du mockmvc
 }

     @Test
     @DisplayName("Should return the HTTP status code 200")
     void shouldReturnHttpStatusCodeOk() throws Exception {
         requestBuilder.getTransferPage()
                 .andExpect(status().isOk());
     }

    @Test
    @DisplayName("Should return ???")
    void postTransferRequest() throws Exception {
        requestBuilder.getTransferPage()
                .andExpect(status().isOk());
    }






 mockMvc.perform(   //methode perform sert à envoyer la request lors du test
            get("/transfer"))   //choix de la librairie pour method get ???
         //methode andExpect() sert à definir les assertions. Renvoie un objet  ResultActions
         .andExpect(status().isOk())  //method status() pour le status http retourné
         .andExpect(view().name("/transfer"));
//         .andExpect(model().attribute(
//                 "parameter???",
//                 hasProperty("id", equalTo(TODO_ITEM_ID))

        //exemples assertions
//         .andExpect(content().mimeType("text/html"))
//         .andExpect(forwardedUrl("/transfer"));

//                 .willThrow(new TodoItemNotFoundException(""));






}
