package com.cs.ku.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {

    private int id;
    private int pin;
    private String name;

    public boolean match(int pin) {
        return this.pin == pin;
    }

}
