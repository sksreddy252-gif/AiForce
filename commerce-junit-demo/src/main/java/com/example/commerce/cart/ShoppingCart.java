package com.example.commerce.cart;

import com.example.commerce.domain.Product;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private final Map<String, CartItem> items = new LinkedHashMap<>();

    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product is required");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        CartItem current = items.get(product.getSku());
        int totalRequested = (current == null ? 0 : current.getQuantity()) + quantity;
        if (totalRequested > product.getStockQuantity()) {
            throw new IllegalStateException(
                    "Insufficient stock for product " + product.getName() + ". Available stock: " + product.getStockQuantity() + ", requested: " + totalRequested
            );
        }

        if (current == null) {
            items.put(product.getSku(), new CartItem(product, quantity));
        } else {
            current.setQuantity(totalRequested);
        }
    }

    public void removeItem(String sku, int quantity) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is required");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        CartItem item = items.get(sku);
        if (item == null) {
            throw new IllegalArgumentException("Item not found in cart: " + sku);
        }

        int remaining = item.getQuantity() - quantity;
        if (remaining <= 0) {
            items.remove(sku);
            return;
        }

        item.setQuantity(remaining);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getItemCount() {
        int total = 0;
        for (CartItem item : items.values()) {
            total += item.getQuantity();
        }
        return total;
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : items.values()) {
            subtotal += item.getLineTotal();
        }
        return subtotal;
    }

    public void clear() {
        items.clear();
    }
}
