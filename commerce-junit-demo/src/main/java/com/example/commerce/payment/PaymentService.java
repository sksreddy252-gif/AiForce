package com.example.commerce.payment;

public class PaymentService {
    public PaymentResult processPayment(String cardNumber, double amount) {
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("Card number is required");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        if (cardNumber.length() < 12) {
            throw new IllegalArgumentException("Card number is invalid");
        }

        return new PaymentResult(true, "Payment approved", amount);
    }
}
