package com.ngbank.ngbank_api.service.paymentStrategy;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PaymentStrategyBuilder {

    private final Map<String, PaymentStrategy> strategies;

    public PaymentStrategyBuilder(Map<String, PaymentStrategy> strategies) {
        this.strategies = strategies;
    }

    public PaymentStrategy getStrategy(String paymentForm) {
        PaymentStrategy strategy = strategies.get(paymentForm);
        if(strategy == null) {
            throw new RuntimeException("Invalid Payment Form");
        }
        return strategy;
    }
}
