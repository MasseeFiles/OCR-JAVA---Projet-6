package com.paymybuddy.service;

import com.paymybuddy.controllers.MoneyTransactionController;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MoneyTransactionControllerTest {
 private PayMyBuddyRequestBuilder requestBuilder; //generateur de requetes http
 private MoneyTransactionService moneyTransactionService; //mock

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
  requestBuilder = new PayMyBuddyRequestBuilder(mockMvc);

// MockMvc mockMvc = webAppContextSetup(paymybuddy).build();
//
 mockMvc.perform(   //definiion de requetes http envoyée lors du test
            get("/transfer" , 1L))
         .andExpect(    //definition des assertions sur la reponse http
                 status().isOk())
         .andExpect(content().mimeType("text/html"))
         .andExpect(forwardedUrl("/transfer"));








}
