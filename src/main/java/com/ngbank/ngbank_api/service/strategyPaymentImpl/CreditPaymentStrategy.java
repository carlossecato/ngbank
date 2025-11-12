package com.ngbank.ngbank_api.service.strategyPaymentImpl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ngbank.ngbank_api.dto.TransactionDTO;
import com.ngbank.ngbank_api.service.paymentStrategy.PaymentStrategy;

@Component("C")
public class CreditPaymentStrategy implements PaymentStrategy {

    @Override
    public BigDecimal calculateTax(TransactionDTO dto) {
        return dto.getTransactionAmount().multiply(BigDecimal.valueOf(0.05));
    }

}
