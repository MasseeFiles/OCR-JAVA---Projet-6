package com.paymybuddy.service;

import com.paymybuddy.controllers.MoneyTransactionController;
import com.paymybuddy.repository.MoneyTransactionRepository;
import com.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(MoneyTransactionController.class) //autoconfigure mockmvc + autre parametres necerssaire à tests
public class MoneyTransactionControllerTest {


    // configuration de MockMVC - debut
    @MockBean
    private MoneyTransactionService moneyTransactionService;
    @MockBean
    private UserService userService;        //mock d'un collaborateur du controlleur
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MoneyTransactionRepository moneyTransactionRepository;
    // configuration de MockMVC - fin

    @Autowired
    private MockMvc mockMvc;  //mock qui envoie les request http


    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }


    //methode 2 - perform
    @Test
    void shouldReturnStatusOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders     //methode perform sert à envoyer la request lors du test
                        .get("/transfer"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk())  //method status() pour le status http retourné
                .andExpect(MockMvcResultMatchers
                        .view().name("transfer"));
    }
}

//    @Test
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
//        void shouldReturn_Status_ok() throws Exception {
//            requestBuilder.getTransferPage()
//                    //methode andExpect() sert à definir les assertions. Renvoie un objet  ResultActions
//                    .andExpect(status().isOk())   //method de MockMvcResultMatchers sur status http
//                    .andExpect(view().name("transfer"));                          // pertinent pour un test unitaire???
//        }
