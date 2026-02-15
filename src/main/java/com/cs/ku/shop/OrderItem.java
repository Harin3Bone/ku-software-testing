package com.cs.ku.shop;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderItem {

    private Product prod;
    private int quantity;

    public double getSubtotal() {
        return prod.getPrice() * quantity;
    }
}
