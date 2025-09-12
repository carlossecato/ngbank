package com.ngbank.ngbank_api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class AccountDTO {

    @NotNull
    @JsonProperty("numero_conta")
    private Integer accountNumber;

    @NotNull
    @JsonProperty("saldo")
    private BigDecimal accountBalance;

    
    public AccountDTO() {}

    public AccountDTO(Integer accountNumber, BigDecimal accountBalance) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}
