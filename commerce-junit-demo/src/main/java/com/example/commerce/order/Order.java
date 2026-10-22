package com.example.commerce.order;

import com.example.commerce.cart.CartItem;
import java.util.List;

public class Order {
    private final List<CartItem> items;
    private final double subtotal;
    private final double discountAmount;
    private final double shipping;
    private final double total;

    public Order(List<CartItem> items, double subtotal, double discountAmount, double shipping, double total) {
        this.items = List.copyOf(items);
        this.subtotal = subtotal;
        this.discountAmount = discountAmount;
        this.shipping = shipping;
        this.total = total;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getShipping() {
        return shipping;
    }

    public double getTotal() {
        return total;
    }
}
