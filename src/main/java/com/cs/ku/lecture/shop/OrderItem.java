package com.cs.ku.lecture.shop;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderItem {

    private Product prod;
    private int quantity;

    public double getSubtotal() {
        return prod.getPrice() * quantity;
    }
}
