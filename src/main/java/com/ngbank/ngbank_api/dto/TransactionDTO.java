package com.ngbank.ngbank_api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    @JsonProperty("forma_pagamento")
    private String paymentForm;

    @JsonProperty("valor")
    private BigDecimal transactionAmount;

    @JsonProperty("numero_conta")
    private Integer accountNumber;

}
