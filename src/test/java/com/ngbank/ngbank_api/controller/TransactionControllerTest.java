package com.ngbank.ngbank_api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ngbank.ngbank_api.dto.AccountDTO;
import com.ngbank.ngbank_api.model.Account;
import com.ngbank.ngbank_api.model.Transaction;
import com.ngbank.ngbank_api.service.TransactionService;

@WebMvcTest(controllers = TransactionController.class)
public class TransactionControllerTest {

    // @Autowired
    // private MockMvc mockMvc; 

    // @MockBean
    // private TransactionService transactionService;

    // @Test
    // void testExecuteTransaction() {
    //     Account account = new Mockito.mock(Account.class);
    //     Transaction transaction = new Transaction(1L, "D", "180.37", account);
    //     when(accountService.startAccount(any(AccountDTO.class))).thenReturn(account);

    //     mockMvc.perform(post("/conta")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content("{\"numero_conta\":234,\"saldo\":180.37}"))
    //             .andExpect(status().is(201))
    //             .andExpect(jsonPath("$.accountNumber").value("234"));
    // }

    // @Autowired
    // private MockMvc mockMvc;

    // @Test
    // void givenRouteTransactionThenReturnOk() throws Exception{
    //     mockMvc.perform(get("/transacao"))
    //             .andExpect(status().isOk());
    // }
}
