package com.example.commerce.order;

import com.example.commerce.cart.ShoppingCart;
import com.example.commerce.domain.Customer;
import com.example.commerce.pricing.PricingService;
import com.example.commerce.promotion.PromotionEngine;

public class CheckoutService {
    private final PricingService pricingService;
    private final PromotionEngine promotionEngine;

    public CheckoutService() {
        this(new PricingService(), new PromotionEngine(java.util.List.of()));
    }

    public CheckoutService(PricingService pricingService, PromotionEngine promotionEngine) {
        if (pricingService == null) {
            throw new IllegalArgumentException("Pricing service is required");
        }
        if (promotionEngine == null) {
            throw new IllegalArgumentException("Promotion engine is required");
        }

        this.pricingService = pricingService;
        this.promotionEngine = promotionEngine;
    }

    public Order checkout(ShoppingCart cart, Customer customer) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty. Add products before checkout.");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer is required");
        }

        double subtotal = pricingService.calculateSubtotal(cart);
        double discountAmount = promotionEngine.getDiscountAmount(cart);
        double shipping = pricingService.calculateShipping(subtotal - discountAmount, customer);
        double total = subtotal - discountAmount + shipping;

        return new Order(cart.getItems(), subtotal, discountAmount, shipping, total);
    }
}
