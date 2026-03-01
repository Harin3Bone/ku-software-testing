package com.cs.ku.shop;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {

    private final Map<String, Product> products = new HashMap<>();

    public void addProduct(String name, double price, int stock) {
        products.put(name, new Product(name, price, stock));
    }

    public Product getProduct(String name) {
        return products.get(name);
    }
}
