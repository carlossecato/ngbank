package com.ngbank.ngbank_api.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ngbank.ngbank_api.dto.AccountDTO;
import com.ngbank.ngbank_api.model.Account;
import com.ngbank.ngbank_api.repository.AccountRepository;

@Service
public class AccountService {
    
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account startAccount(AccountDTO dto) {
        if(accountRepository.findByAccountNumber(dto.getAccountNumber()).isPresent()) {
            throw new RuntimeException("Account already exists");
        }
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber().toString());
        account.setAccountBalance(dto.getAccountBalance().toString());
        return accountRepository.save(account);
    }

    public Optional<Account> findByAccountNumber(Number accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
