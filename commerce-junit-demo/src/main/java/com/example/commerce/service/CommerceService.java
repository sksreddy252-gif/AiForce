package com.example.commerce.service;

import com.example.commerce.cart.ShoppingCart;
import com.example.commerce.domain.Customer;
import com.example.commerce.order.CheckoutService;
import com.example.commerce.order.Order;
import com.example.commerce.payment.PaymentResult;
import com.example.commerce.payment.PaymentService;
import com.example.commerce.promotion.PromotionEngine;
import com.example.commerce.pricing.PricingService;

public class CommerceService {
    private final CheckoutService checkoutService;
    private final PaymentService paymentService;

    public CommerceService() {
        this(new CheckoutService(new PricingService(), new PromotionEngine(java.util.List.of())), new PaymentService());
    }

    public CommerceService(CheckoutService checkoutService, PaymentService paymentService) {
        this.checkoutService = checkoutService;
        this.paymentService = paymentService;
    }

    public Order checkout(ShoppingCart cart, Customer customer) {
        return checkoutService.checkout(cart, customer);
    }

    public PaymentResult pay(String cardNumber, double amount) {
        return paymentService.processPayment(cardNumber, amount);
    }
}
