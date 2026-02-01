package com.cs.ku.bank;

import com.cs.ku.bank.stub.CustomerFileDataServiceStub;
import com.cs.ku.bank.stub.CustomerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankStubTest {

    private Bank bank;
    private CustomerStub customerStub;

    @BeforeEach
    void setup() {
        // Stub
        customerStub = new CustomerStub(1, 1234, "Jane Doe");
        CustomerFileDataServiceStub customerDataService = new CustomerFileDataServiceStub();
        customerDataService.addCustomer(customerStub);
        customerDataService.addCustomer(new Customer(2, 5678, "John Smith"));
        customerDataService.addCustomer(new Customer(3, 9012, "Adam Warlock"));

        // Setup
        bank = new Bank("Test Bank", customerDataService);
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

    @Test
    void testGetCustomerFromFiles() {
        // Given
        var expected = new ArrayList<Customer>();
        expected.add(customerStub);
        expected.add(new Customer(2, 5678, "John Smith"));
        expected.add(new Customer(3, 9012, "Adam Warlock"));

        // When
        var actual = bank.getCustomerFromFiles();

        // Then
        assertNotNull(actual);
        assertEquals(3, actual.size());

        for (var i = 0; i < actual.size(); i++) {
            var actualCustomer = actual.get(i);
            var expectedCustomer = expected.get(i);
            assertEquals(expectedCustomer.getId(), actualCustomer.getId());
            assertEquals(expectedCustomer.getPin(), actualCustomer.getPin());
            assertEquals(expectedCustomer.getName(), actualCustomer.getName());
        }
    }

}
