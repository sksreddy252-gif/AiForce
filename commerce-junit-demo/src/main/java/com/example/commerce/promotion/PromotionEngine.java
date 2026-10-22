package com.example.commerce.promotion;

import com.example.commerce.cart.ShoppingCart;
import java.util.List;

public class PromotionEngine {
    private final List<Promotion> promotions;

    public PromotionEngine(List<Promotion> promotions) {
        this.promotions = promotions == null ? List.of() : List.copyOf(promotions);
    }

    public Promotion getApplicablePromotion(ShoppingCart cart) {
        if (cart == null || cart.isEmpty()) {
            return null;
        }

        double subtotal = cart.getSubtotal();
        for (Promotion promotion : promotions) {
            if (subtotal >= promotion.getMinimumSubtotal()) {
                return promotion;
            }
        }
        return null;
    }

    public double getDiscountAmount(ShoppingCart cart) {
        Promotion promotion = getApplicablePromotion(cart);
        if (promotion == null) {
            return 0;
        }
        return cart.getSubtotal() * (promotion.getPercentOff() / 100.0);
    }
}
