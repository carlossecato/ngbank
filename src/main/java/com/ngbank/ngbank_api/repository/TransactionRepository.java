package com.ngbank.ngbank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngbank.ngbank_api.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
}