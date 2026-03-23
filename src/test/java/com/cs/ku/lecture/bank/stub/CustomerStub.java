package com.cs.ku.lecture.bank.stub;

import com.cs.ku.lecture.bank.Customer;
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
