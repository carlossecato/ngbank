package com.ngbank.ngbank_api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.ngbank.ngbank_api.model.Account;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void testSaveAccountWithDataase() {
        // Account account = new Account();
        // account.setAccountNumber("234");
        // account.setAccountBalance("100.37");

        // Account savedAccount = accountRepository.save(account);

        // assertThat(savedAccount.getId()).isNotNull();
        // assertThat(savedAccount.getAccountNumber()).isEqualTo("234");
        // assertThat(savedAccount.getAccountBalance()).isEqualTo("100.37");
    }
}
