package com.cs.ku.bank;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Bank {

    private String bankName;
    private Map<Integer, Customer> customers;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer findCustomerById(int id) {
        return customers.get(id);
    }

    public boolean validateCustomer(int customerId, int pin) {
        Customer customer = findCustomerById(customerId);
        return customer != null && customer.match(pin);
    }

}
