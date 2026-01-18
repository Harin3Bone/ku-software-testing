package com.cs.ku.bank;

import com.cs.ku.bank.stub.CustomerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankTest {

    private Bank bank;
    private CustomerStub customerStub;

    @BeforeEach
    void setup() {
        // Stub
        customerStub = new CustomerStub(1, 1234, "Jane Doe");

        bank = new Bank("Test Bank");
        bank.addCustomer(customerStub);
    }

    @Test
    void testFindCustomer_found() {
        // Given
        int customerId = 1;

        // When
        var actual = bank.findCustomerById(customerId);

        // Then
        assertNotNull(actual);
        assertEquals(customerId, actual.getId());
        assertEquals(1234, actual.getPin());
        assertEquals("Jane Doe", actual.getName());
    }

    @Test
    void testValidateCustomer_validPin() {
        // Given
        int customerId = 1;
        int pin = 123;

        customerStub.setHardCodeMatch(true);

        // When
        var actual = bank.validateCustomer(customerId, pin);

        // Then
        assertTrue(actual);
    }

    @Test
    void testValidateCustomer_invalidPin() {
        // Given
        int customerId = 1;
        int pin = 999;

        customerStub.setHardCodeMatch(false);

        // When
        var actual = bank.validateCustomer(customerId, pin);

        // Then
        assertFalse(actual);
    }

}
