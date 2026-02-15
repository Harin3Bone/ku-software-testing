package com.cs.ku.shop;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {

    private List<OrderItem> items;
    private LocalDateTime date;

    public Order() {
        this.items = new ArrayList<>();
        this.date = LocalDateTime.now();
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
        product.cutStock(quantity);
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items)
            total += item.getSubtotal();
        return total;
    }
}
