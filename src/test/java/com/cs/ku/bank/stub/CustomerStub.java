package com.cs.ku.bank.stub;

import com.cs.ku.bank.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerStub extends Customer {

    private boolean hardCodeMatch = false;

    public CustomerStub(int id, int pin, String name) {
        super(id, pin, name);
    }

    @Override
    public boolean match(int pin) {
        return this.hardCodeMatch;
    }

}
