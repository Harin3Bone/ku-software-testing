package com.cs.ku.bank;

import com.cs.ku.bank.exception.NotEnoughBalanceException;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Bank {

    private String name;
    private final Map<Integer, Customer> customers = new HashMap<>();

    public Bank(String name) {
        this.name = name;
    }

    public void openAccount(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer getCustomer(int id) {
        return customers.get(id);
    }

    public Customer matchCustomer(int id, int pin) {
        Customer customer = customers.get(id);
        return customer != null && customer.match(pin)
                ? customer
                : null;
    }

    public void transfer(int givingId, int receiverId, double amount) throws NotEnoughBalanceException {
        customers.get(givingId).getAccount().withdraw(amount);
        customers.get(receiverId).getAccount().deposit(amount);
    }

}
