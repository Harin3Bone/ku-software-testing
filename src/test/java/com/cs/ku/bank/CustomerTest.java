package com.cs.ku.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setup() {
        customer = new Customer(1, 1234, "John Doe");
    }

    @Test
    void testMatch_CorrectPin() {
        var actual = customer.match(1234);
        assertTrue(actual);
    }

    @Test
    void testMatch_IncorrectPin() {
        var actual = customer.match(5678);
        assertFalse(actual);
    }

}
