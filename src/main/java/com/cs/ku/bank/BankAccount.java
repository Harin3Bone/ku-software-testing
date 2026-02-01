package com.cs.ku.bank;

import lombok.Getter;

@Getter
public class BankAccount {

    private String name;
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance < amount)
            throw new IllegalStateException("Balance must be more than amount");
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        this.balance -= amount;
    }

    public void addInterest(double rate) {
        balance = balance + (balance * rate);
    }

}
