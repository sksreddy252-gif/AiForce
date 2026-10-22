package com.example.commerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.commerce.cart.ShoppingCart;
import com.example.commerce.domain.Category;
import com.example.commerce.domain.Product;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {

    @Test
    void addItem_shouldIncreaseCartQuantityAndSubtotal() {
        ShoppingCart cart = new ShoppingCart();
        Product shirt = new Product("SKU-100", "Cotton T-Shirt", Category.APPAREL, 19.99, 20);

        cart.addItem(shirt, 3);

        assertEquals(3, cart.getItemCount());
        assertEquals(59.97, cart.getSubtotal(), 0.01);
    }

    @Test
    void addItem_shouldRejectQuantityBeyondStock() {
        ShoppingCart cart = new ShoppingCart();
        Product mobile = new Product("SKU-200", "Phone", Category.ELECTRONICS, 499.00, 2);
        cart.addItem(mobile, 2);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> cart.addItem(mobile, 1)
        );

        assertTrue(exception.getMessage().contains("Insufficient stock"));
    }

    @Test
    void removeItem_shouldUpdateCart() {
        ShoppingCart cart = new ShoppingCart();
        Product mug = new Product("SKU-300", "Coffee Mug", Category.HOME, 12.50, 10);
        cart.addItem(mug, 4);

        cart.removeItem("SKU-300", 2);

        assertEquals(2, cart.getItemCount());
        assertEquals(25.00, cart.getSubtotal(), 0.01);
    }
}
