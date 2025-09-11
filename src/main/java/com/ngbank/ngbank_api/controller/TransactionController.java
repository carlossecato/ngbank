package com.ngbank.ngbank_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransactionController {
    
    @GetMapping
    public String getTransaction() {
        return "Transaction OK";
    }
}
