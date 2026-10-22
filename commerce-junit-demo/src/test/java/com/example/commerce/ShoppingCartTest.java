package com.example.commerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ShoppingCartTest {

    @Test
    void addItem_shouldIncreaseSubtotal() {
        ShoppingCart cart = new ShoppingCart();
        Product shirt = new Product("SKU-100", "Cotton T-Shirt", 19.99, 20);

        cart.addItem(shirt, 3);

        assertEquals(3, cart.getItemCount());
        assertEquals(59.97, cart.getSubtotal(), 0.01);
    }

    @Test
    void addItem_shouldRejectQuantityBeyondStock() {
        ShoppingCart cart = new ShoppingCart();
        Product laptop = new Product("SKU-200", "Laptop", 899.99, 2);
        cart.addItem(laptop, 2);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> cart.addItem(laptop, 1)
        );

        assertTrue(exception.getMessage().contains("Insufficient stock"));
    }

    @Test
    void removeItem_shouldReduceQuantity() {
        ShoppingCart cart = new ShoppingCart();
        Product mug = new Product("SKU-300", "Coffee Mug", 12.50, 10);
        cart.addItem(mug, 4);

        cart.removeItem("SKU-300", 2);

        assertEquals(2, cart.getItemCount());
        assertEquals(25.00, cart.getSubtotal(), 0.01);
    }
}
