package com.example.commerce.domain;

import java.util.Objects;

public class Product {
    private final String sku;
    private final String name;
    private final Category category;
    private final double price;
    private final int stockQuantity;

    public Product(String sku, String name, Category category, double price, int stockQuantity) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category is required");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }

        this.sku = sku;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Product)) {
            return false;
        }
        Product product = (Product) other;
        return Double.compare(product.price, price) == 0
                && stockQuantity == product.stockQuantity
                && sku.equals(product.sku)
                && name.equals(product.name)
                && category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, category, price, stockQuantity);
    }
}
