package com.ngbank.ngbank_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(unique = true, nullable = false)
    private Integer accountNumber;

    @Column(nullable = false)
    private Double accountBalance;

}
