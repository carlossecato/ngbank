package com.ngbank.ngbank_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngbank.ngbank_api.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
   Optional<Account> findByAccountNumber(Number accountNumber);
}
