package com.cs.ku.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class BankMockTest {

    private BankUnit bank;

    @Mock
    private BankAccount mockAccountA;

    @Mock
    private BankAccount mockAccountB;

    @BeforeEach
    void setup() {
        openMocks(this);
        bank = new BankUnit("Test Bank");
    }

    @Test
    void testTransfer() {
        // Stub
        when(mockAccountA.getName()).thenReturn("A");
        when(mockAccountB.getName()).thenReturn("B");

        // Given
        bank.openAccount(mockAccountA);
        bank.openAccount(mockAccountB);

        // When
        bank.transfer("A", "B", 1000);

        // Then
        verify(mockAccountA, times(1)).withdraw(1000);
        verify(mockAccountB, times(1)).deposit(1000);
    }

    @Test
    void testGiveInterest() {
        // Stub
        when(mockAccountA.getName()).thenReturn("John Doe");
        when(mockAccountB.getName()).thenReturn("Jane Doe");

        // Given
        var interestRate = 0.05;
        bank.openAccount(mockAccountA);
        bank.openAccount(mockAccountB);

        // When
        bank.giveInterestAll(interestRate);

        // Verify
        verify(mockAccountA, times(1)).addInterest(interestRate);
        verify(mockAccountB, times(1)).addInterest(interestRate);
    }

}
