package com.ngbank.ngbank_api.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ngbank.ngbank_api.dto.TransactionDTO;
import com.ngbank.ngbank_api.model.Account;
import com.ngbank.ngbank_api.model.Transaction;
import com.ngbank.ngbank_api.repository.AccountRepository;
import com.ngbank.ngbank_api.repository.TransactionRepository;
import com.ngbank.ngbank_api.service.paymentStrategy.PaymentStrategy;
import com.ngbank.ngbank_api.service.paymentStrategy.PaymentStrategyBuilder;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final PaymentStrategyBuilder paymentStrategyBuilder;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository, PaymentStrategyBuilder paymentStrategyBuilder) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.paymentStrategyBuilder = paymentStrategyBuilder;
    }

    public Account process(TransactionDTO dto) {
       Account account = accountRepository.findByAccountNumber(dto.getAccountNumber())
       .orElseThrow(() -> new RuntimeException("Account do not exists"));

     PaymentStrategy strategy =  paymentStrategyBuilder.getStrategy(dto.getPaymentForm());
     BigDecimal tax = strategy.calculateTax(dto);
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
