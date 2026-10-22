package com.example.commerce.promotion;

public class Promotion {
    private final String code;
    private final String description;
    private final double percentOff;
    private final double minimumSubtotal;

    public Promotion(String code, String description, double percentOff, double minimumSubtotal) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Promotion code is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (percentOff < 0 || percentOff > 100) {
            throw new IllegalArgumentException("Promotion percent must be between 0 and 100");
        }
        if (minimumSubtotal < 0) {
            throw new IllegalArgumentException("Minimum subtotal cannot be negative");
        }

        this.code = code;
        this.description = description;
        this.percentOff = percentOff;
        this.minimumSubtotal = minimumSubtotal;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public double getPercentOff() {
        return percentOff;
    }

    public double getMinimumSubtotal() {
        return minimumSubtotal;
    }
}
