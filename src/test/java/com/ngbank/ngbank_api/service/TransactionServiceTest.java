package com.ngbank.ngbank_api.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.ngbank.ngbank_api.dto.TransactionDTO;
import com.ngbank.ngbank_api.model.Account;
import com.ngbank.ngbank_api.model.Transaction;
import com.ngbank.ngbank_api.repository.AccountRepository;
import com.ngbank.ngbank_api.repository.TransactionRepository;

public class TransactionServiceTest {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    TransactionServiceTest() {
        this.accountRepository = Mockito.mock(AccountRepository.class);
        this.transactionRepository = Mockito.mock(TransactionRepository.class);
        this.transactionService = new TransactionService(accountRepository, transactionRepository);
    }

    @Test
    void givenInvalidAccountWhenProcessTransactionThenShouldThrowsExceptionAndNotSave() {
        TransactionDTO dto = new TransactionDTO("D",new BigDecimal(10.0),999);
    
        when(accountRepository.findByAccountNumber(999)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                                     () -> transactionService.process(dto));

        assertEquals(RuntimeException.class, exception.getClass());
        verify(accountRepository, never()).save(any());
        verify(transactionRepository, never()).save(any());
    }

    @Test
    void givenTransactionAndAccountWhenProcessAccountThatNotHaveFundsThenThrowsException() {
        Account account = new Account(1L, "123", "5.0");
        TransactionDTO dto = new TransactionDTO("D", new BigDecimal(10.0), 123);

        when(accountRepository.findByAccountNumber(123)).thenReturn(Optional.of(account));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> transactionService.process(dto));

        assertEquals(RuntimeException.class, ex.getClass());
    }

    @Test
    void givenTransactionAndAccountWhenProcessDebitTransactionWithThreePercentTaxThenShouldReturnAccountAndTransaction() {
        Account account = new Account(1L, "123", "100.0");
        TransactionDTO dto = new TransactionDTO("D",new BigDecimal(10.0),123);
        
        

        when(accountRepository.findByAccountNumber(123)).thenReturn(Optional.of(account));

        Account result = transactionService.process(dto);

        BigDecimal expectedBalance = new BigDecimal("89.70").setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualBalance = new BigDecimal(result.getAccountBalance()).setScale(2, RoundingMode.HALF_UP);

        assertEquals(expectedBalance, actualBalance);
        verify(accountRepository).save(any(Account.class));
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void givenTransactionAndAccountWhenProcessAccountWithPixWithoutTaxThenShouldReturnAccountAndTransaction() {
        Account account = new Account(1L, "123", "50.0");
        TransactionDTO dto = new TransactionDTO("P", new BigDecimal(25.0), 123);

        when(accountRepository.findByAccountNumber(123)).thenReturn(Optional.of(account));

        Account result = transactionService.process(dto);

        BigDecimal expectedBalance = new BigDecimal("25.0").setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualBalance = new BigDecimal(result.getAccountBalance()).setScale(2, RoundingMode.HALF_UP);

        assertEquals(expectedBalance, actualBalance); // 50 - 25
        verify(accountRepository).save(any(Account.class));
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void givenTransactionAndAccountWhenProcessAccountWithCreditWithFivePerCentTaxThenShouldReturnAccountAndTransaction() {
        Account account = new Account(1L, "123", "200.0");
        TransactionDTO dto = new TransactionDTO("C", new BigDecimal(20.0),123 );

        when(accountRepository.findByAccountNumber(123)).thenReturn(Optional.of(account));

        Account result = transactionService.process(dto);

        BigDecimal expectedBalance = new BigDecimal("179.0").setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualBalance = new BigDecimal(result.getAccountBalance()).setScale(2, RoundingMode.HALF_UP);

        assertEquals(expectedBalance, actualBalance); // 200 - (20 + 5%)
        verify(accountRepository).save(any(Account.class));
        verify(transactionRepository).save(any(Transaction.class));
    }


}
