package com.ngbank.ngbank_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ngbank.ngbank_api.dto.TransactionDTO;
import com.ngbank.ngbank_api.model.Account;
import com.ngbank.ngbank_api.service.TransactionService;

@RestController
@RequestMapping("/transacao")
public class TransactionController {
    
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> executeTransaction(@RequestBody TransactionDTO dto) {
        System.out.println("dto Amount: " + dto.getTransactionAmount()+ "payment form: " +dto.getPaymentForm());
       try {
        Account account = transactionService.process(dto);
        return ResponseEntity.status(201).body(account);

       } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    
}
