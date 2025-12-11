package com.cs.ku.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankAccountTest {

    @Test
    void testDeposit() {
        // given
        var account = new BankAccount(50.0);

        // when
        account.deposit(25.0);

        // then
        assertEquals(75.0, account.getBalance());
    }

    @Test
    void testWithdraw() {
        // given
        var account = new BankAccount(100.0);

        // when
        account.withdraw(30.0);

        // then
        assertEquals(70.0, account.getBalance());
    }
}
