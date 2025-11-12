package com.ngbank.ngbank_api.service.paymentStrategy;

import java.math.BigDecimal;
import com.ngbank.ngbank_api.dto.TransactionDTO;

public interface PaymentStrategy {
    BigDecimal calculateTax(TransactionDTO dto);
}
