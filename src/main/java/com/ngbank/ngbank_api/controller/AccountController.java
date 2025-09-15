package com.ngbank.ngbank_api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ngbank.ngbank_api.dto.AccountDTO;
import com.ngbank.ngbank_api.model.Account;
import com.ngbank.ngbank_api.service.AccountService;
import com.ngbank.ngbank_api.view.Views;

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
    @JsonView(Views.Public.class)
    public ResponseEntity<Account> startAccount(@Valid @RequestBody AccountDTO accountDto) {
       Account account = accountService.startAccount(accountDto);
        return ResponseEntity.status(201).body(account);
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<?> findAccount(@RequestParam Integer numero_conta) {
        return accountService.findByAccountNumber(numero_conta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
