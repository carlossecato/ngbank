package com.ngbank.ngbank_api.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ngbank.ngbank_api.dto.TransactionDTO;
import com.ngbank.ngbank_api.model.Account;
import com.ngbank.ngbank_api.model.Transaction;
import com.ngbank.ngbank_api.repository.AccountRepository;
import com.ngbank.ngbank_api.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account process(TransactionDTO dto) {
       Account account = accountRepository.findByAccountNumber(dto.getAccountNumber())
       .orElseThrow(() -> new RuntimeException("Account do not exists"));

       BigDecimal tax = switch(dto.getPaymentForm()) {
        case "D" -> dto.getTransactionAmount().multiply(BigDecimal.valueOf(0.03));
        case "C" -> dto.getTransactionAmount().multiply(BigDecimal.valueOf(0.05));
        case "P" -> dto.getTransactionAmount().multiply(BigDecimal.valueOf(0.00));
        default -> throw new RuntimeException("Invalid Payment Form");
       };

       BigDecimal totalAmount = dto.getTransactionAmount().add(tax);
       
       if(new BigDecimal(account.getAccountBalance()).compareTo(totalAmount) == -1) {
        throw new RuntimeException("There no enought funds");
       }
       
       account.setAccountBalance(new BigDecimal(account.getAccountBalance()).subtract(totalAmount).toString());
       accountRepository.save(account);

       Transaction transaction = new Transaction(null, dto.getPaymentForm(), dto.getTransactionAmount().toString(), account);
       transactionRepository.save(transaction);

     return account;
    }


}
