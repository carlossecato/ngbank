package com.ngbank.ngbank_api.controller;

import com.ngbank.ngbank_api.dto.AccountDTO;
import com.ngbank.ngbank_api.model.Account;
import com.ngbank.ngbank_api.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void testCreateAccount() throws Exception {
        Account account = new Account(1L, "234", "180.37");
        when(accountService.startAccount(any(AccountDTO.class))).thenReturn(account);

        mockMvc.perform(post("/conta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numero_conta\":234,\"saldo\":180.37}"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.accountNumber").value("234"));
    }
}
