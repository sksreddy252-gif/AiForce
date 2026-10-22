package com.example.commerce;

public class CheckoutService {
    private final PricingService pricingService;

    public CheckoutService() {
        this(new PricingService());
    }

    public CheckoutService(PricingService pricingService) {
        if (pricingService == null) {
            throw new IllegalArgumentException("Pricing service is required");
        }
        this.pricingService = pricingService;
    }

    public Order checkout(ShoppingCart cart, double discountPercent) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty. Add products before checkout.");
        }

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (item.getQuantity() > product.getStockQuantity()) {
                throw new IllegalStateException(
                        "Insufficient stock for product " + product.getName() + ". " +
                                "Available stock: " + product.getStockQuantity() + ", requested: " + item.getQuantity()
                );
            }
        }

        double subtotal = pricingService.calculateSubtotal(cart);
        double discountAmount = pricingService.calculateDiscountAmount(subtotal, discountPercent);
        double shipping = pricingService.calculateShipping(subtotal - discountAmount);
        double total = subtotal - discountAmount + shipping;

        return new Order(cart.getItems(), subtotal, discountAmount, shipping, total);
    }
}
