package com.example.commerce.payment;

public class PaymentResult {
    private final boolean success;
    private final String message;
    private final double amount;

    public PaymentResult(boolean success, String message, double amount) {
        this.success = success;
        this.message = message;
        this.amount = amount;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public double getAmount() {
        return amount;
    }
}
