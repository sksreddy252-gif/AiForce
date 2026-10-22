package com.example.commerce.pricing;

import com.example.commerce.cart.ShoppingCart;
import com.example.commerce.domain.Customer;

public class PricingService {
    public double calculateSubtotal(ShoppingCart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart is required");
        }
        return cart.getSubtotal();
    }

    public double calculateDiscount(double subtotal, double percentOff) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("Subtotal cannot be negative");
        }
        if (percentOff < 0 || percentOff > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }
        return subtotal * (percentOff / 100.0);
    }

    public double calculateShipping(double subtotal, Customer customer) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("Subtotal cannot be negative");
        }
        if (subtotal == 0) {
            return 0;
        }
        if (customer != null && customer.isVip()) {
            return 0;
        }
        return subtotal >= 100 ? 0 : 8.99;
    }

    public double calculateFinalTotal(double subtotal, double discountPercent, Customer customer) {
        double discount = calculateDiscount(subtotal, discountPercent);
        return subtotal - discount + calculateShipping(subtotal - discount, customer);
    }
}
