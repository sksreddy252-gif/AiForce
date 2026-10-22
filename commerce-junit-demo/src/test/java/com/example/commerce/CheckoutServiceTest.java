package com.example.commerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CheckoutServiceTest {

    @Test
    void checkout_shouldThrowWhenCartIsEmpty() {
        CheckoutService service = new CheckoutService();
        ShoppingCart cart = new ShoppingCart();

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.checkout(cart, 10)
        );

        assertTrue(exception.getMessage().contains("Cart is empty"));
    }

    @Test
    void checkout_shouldApplyDiscountAndFreeShippingForLargeOrders() {
        CheckoutService service = new CheckoutService();
        ShoppingCart cart = new ShoppingCart();
        Product jacket = new Product("SKU-400", "Winter Jacket", 75.00, 10);
        cart.addItem(jacket, 2);

        Order order = service.checkout(cart, 10);

        assertEquals(150.00, order.getSubtotal(), 0.01);
        assertEquals(15.00, order.getDiscountAmount(), 0.01);
        assertEquals(0.0, order.getShipping(), 0.01);
        assertEquals(135.00, order.getTotal(), 0.01);
    }

    @Test
    void checkout_shouldRejectInsufficientStock() {
        CheckoutService service = new CheckoutService();
        ShoppingCart cart = new ShoppingCart();
        Product keyboard = new Product("SKU-500", "Mechanical Keyboard", 89.99, 1);
        cart.addItem(keyboard, 1);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.checkout(cart, 0)
        );

        assertTrue(exception.getMessage().contains("Insufficient stock"));
    }
}
