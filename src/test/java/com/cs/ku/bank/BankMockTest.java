package com.cs.ku.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class BankMockTest {

    private Bank bank;

    @Mock
    private BankAccount mockAccountA;

    @Mock
    private BankAccount mockAccountB;

    @BeforeEach
    void initMocks() {
        openMocks(this);
        bank = new Bank("Test Bank");
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

}
