package com.example.commerce;

public class PricingService {
    public double calculateSubtotal(ShoppingCart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart is required");
        }
        return cart.getSubtotal();
    }

    public double calculateDiscountAmount(double subtotal, double discountPercent) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("Subtotal cannot be negative");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }
        return subtotal * (discountPercent / 100.0);
    }

    public double calculateFinalTotal(double subtotal, double discountPercent) {
        return subtotal - calculateDiscountAmount(subtotal, discountPercent);
    }

    public double calculateShipping(double subtotal) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("Subtotal cannot be negative");
        }
        return subtotal >= 100 ? 0 : 8.99;
    }
}
