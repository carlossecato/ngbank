package com.ngbank.ngbank_api.controller;

import com.ngbank.ngbank_api.dto.AccountDTO;
import com.ngbank.ngbank_api.model.Account;
import com.ngbank.ngbank_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/conta")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> startAccount(@Valid @RequestBody AccountDTO accountDto) {
       Account account = accountService.startAccount(accountDto);
        return ResponseEntity.ok(account);
    }
}
