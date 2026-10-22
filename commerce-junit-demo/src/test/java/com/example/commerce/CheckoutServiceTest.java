package com.example.commerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.commerce.cart.ShoppingCart;
import com.example.commerce.domain.Category;
import com.example.commerce.domain.Customer;
import com.example.commerce.domain.Product;
import com.example.commerce.order.CheckoutService;
import com.example.commerce.order.Order;
import com.example.commerce.pricing.PricingService;
import com.example.commerce.promotion.Promotion;
import com.example.commerce.promotion.PromotionEngine;
import java.util.List;
import org.junit.jupiter.api.Test;

class CheckoutServiceTest {

    @Test
    void checkout_shouldApplyPromotionAndShipping() {
        ShoppingCart cart = new ShoppingCart();
        Product laptop = new Product("SKU-400", "Laptop", Category.ELECTRONICS, 1000.00, 5);
        cart.addItem(laptop, 1);

        PromotionEngine engine = new PromotionEngine(List.of(
                new Promotion("SAVE10", "10% off", 10, 500)
        ));
        CheckoutService service = new CheckoutService(new PricingService(), engine);
        Customer customer = new Customer("C-1", "Alice", false, 100);

        Order order = service.checkout(cart, customer);

        assertEquals(1000.00, order.getSubtotal(), 0.01);
        assertEquals(100.00, order.getDiscountAmount(), 0.01);
        assertEquals(0.0, order.getShipping(), 0.01);
        assertEquals(900.00, order.getTotal(), 0.01);
    }

    @Test
    void checkout_shouldThrowForEmptyCart() {
        ShoppingCart cart = new ShoppingCart();
        Customer customer = new Customer("C-2", "Bob", false, 50);
        CheckoutService service = new CheckoutService(new PricingService(), new PromotionEngine(List.of()));

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.checkout(cart, customer)
        );

        assertTrue(exception.getMessage().contains("Cart is empty"));
    }
}
