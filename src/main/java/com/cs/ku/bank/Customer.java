package com.cs.ku.bank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private int id;
    private int pin;
    private String name;
    private BankAccount account;

    public Customer(int id, int pin) {
        this(id, pin, 0.0);
    }

    public Customer(int id, int pin, String name) {
        this(id, pin, name, 0.0);
    }

    public Customer(int id, int pin, double balance) {
        this(id, pin, null, balance);
    }

    public Customer(int id, int pin, String name, double balance) {
        this.id = id;
        this.pin = pin;
        this.name = name;
        this.account = new BankAccount(balance);
    }

    public boolean match(int pin) {
        return this.pin == pin;
    }

}
