package com.cs.ku.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    private String name;
    private double price;
    private int stock;

    public void cutStock(int quantity) {
        stock -= quantity;
    }

}
