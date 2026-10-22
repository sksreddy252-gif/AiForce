package com.example.commerce.domain;

import java.util.Objects;

public class Customer {
    private final String id;
    private final String name;
    private final boolean vip;
    private final int loyaltyPoints;

    public Customer(String id, String name, boolean vip, int loyaltyPoints) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Customer id is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name is required");
        }
        if (loyaltyPoints < 0) {
            throw new IllegalArgumentException("Loyalty points cannot be negative");
        }

        this.id = id;
        this.name = name;
        this.vip = vip;
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isVip() {
        return vip;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) other;
        return vip == customer.vip && loyaltyPoints == customer.loyaltyPoints && id.equals(customer.id) && name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vip, loyaltyPoints);
    }
}
