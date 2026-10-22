package com.example.commerce;

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

        CartItem existing = items.get(product.getSku());
        int updatedQuantity = (existing == null ? 0 : existing.getQuantity()) + quantity;

        if (updatedQuantity > product.getStockQuantity()) {
            throw new IllegalStateException(
                    "Insufficient stock for product " + product.getName() + ". " +
                            "Available stock: " + product.getStockQuantity() + ", requested: " + updatedQuantity
            );
        }

        if (existing == null) {
            items.put(product.getSku(), new CartItem(product, quantity));
        } else {
            existing.setQuantity(updatedQuantity);
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

        int remainingQuantity = item.getQuantity() - quantity;
        if (remainingQuantity <= 0) {
            items.remove(sku);
            return;
        }

        item.setQuantity(remainingQuantity);
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
