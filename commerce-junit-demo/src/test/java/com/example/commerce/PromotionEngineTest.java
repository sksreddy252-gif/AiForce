package com.example.commerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.commerce.cart.ShoppingCart;
import com.example.commerce.domain.Category;
import com.example.commerce.domain.Product;
import com.example.commerce.promotion.Promotion;
import com.example.commerce.promotion.PromotionEngine;
import java.util.List;
import org.junit.jupiter.api.Test;

class PromotionEngineTest {

    @Test
    void getApplicablePromotion_shouldReturnMostRelevantPromotion() {
        ShoppingCart cart = new ShoppingCart();
        Product tv = new Product("SKU-700", "TV", Category.ELECTRONICS, 800.00, 10);
        cart.addItem(tv, 1);

        PromotionEngine engine = new PromotionEngine(List.of(
                new Promotion("SAVE15", "15% off", 15, 500),
                new Promotion("SAVE20", "20% off", 20, 1000)
        ));

        Promotion promotion = engine.getApplicablePromotion(cart);
        assertEquals("SAVE15", promotion.getCode());
    }

    @Test
    void getDiscountAmount_shouldReturnZeroWhenNoPromotionApplies() {
        ShoppingCart cart = new ShoppingCart();
        Product book = new Product("SKU-800", "Java Guide", Category.BOOKS, 25.00, 20);
        cart.addItem(book, 1);

        PromotionEngine engine = new PromotionEngine(List.of(
                new Promotion("SAVE20", "20% off", 20, 150)
        ));

        assertEquals(0.0, engine.getDiscountAmount(cart), 0.01);
    }
}
