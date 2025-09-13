package com.ngbank.ngbank_api.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void testAccountConstructorAndGetters() {
        // Arrange
        String accountNumber = "123";
        String balance = "1000.50";

        // Act
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountBalance(balance);
        
        // Assert
        assertThat(account).isNotNull();
        assertThat(account.getAccountNumber()).isEqualTo(accountNumber);
        //assertThat(account.getAccountBalance()).isEqualByComparingTo(balance);
        assertThat(account.getId()).isNull(); 
    }

    @Test
    void testSetters() {
        
        Account account = new Account();

        
        account.setAccountNumber("456");
        account.setAccountBalance("500.75");

        
        assertThat(account.getAccountNumber()).isEqualTo("456");
        assertThat(account.getAccountBalance()).isEqualTo("500.75");
    }
}