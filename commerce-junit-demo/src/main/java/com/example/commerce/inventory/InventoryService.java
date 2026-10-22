package com.example.commerce.inventory;

import com.example.commerce.domain.Product;
import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private final Map<String, Integer> stockBySku = new HashMap<>();

    public InventoryService() {
    }

    public void addStock(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product is required");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        stockBySku.put(product.getSku(), quantity);
    }

    public void reserveStock(String sku, int requestedQuantity) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is required");
        }
        if (requestedQuantity <= 0) {
            throw new IllegalArgumentException("Requested quantity must be greater than zero");
        }

        int available = stockBySku.getOrDefault(sku, 0);
        if (available < requestedQuantity) {
            throw new IllegalStateException("Insufficient stock for SKU " + sku + ". Available: " + available + ", requested: " + requestedQuantity);
        }

        stockBySku.put(sku, available - requestedQuantity);
    }

    public int getAvailableStock(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is required");
        }
        return stockBySku.getOrDefault(sku, 0);
    }

    public boolean isAvailable(String sku, int quantity) {
        return getAvailableStock(sku) >= quantity;
    }
}
