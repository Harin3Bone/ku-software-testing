package com.cs.ku.bank;

import com.cs.ku.bank.exception.NotEnoughBalanceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {
            "Balance > 0 and withdraw amount = balance, 1000.00, 1000.00, 0.00",
            "Balance > 0 and withdraw amount < balance, 2000.00, 1000.00, 1000.00"
    }, delimiter = ',')
    void testWithdrawBlackbox_shouldPass(String scenarioName, double initBalance, double withdrawAmt, double expectedBalance) {
        // given
        var account = new BankAccount(initBalance);

        // when
        account.withdraw(withdrawAmt);

        // then
        assertEquals(expectedBalance, account.getBalance());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource(value = {
            "sourceForWithdrawBlackbox_balanceMustMoreThanAmount",
            "sourceForWithdrawBlackbox_amountMustPositive"
    })
    <T extends RuntimeException> void testWithdrawBlackbox_shouldFailure(
            String scenarioName, double initBalance, double withdrawAmt,
            Class<T> exception, String errMsg
    ) {
        // given
        var account = new BankAccount(initBalance);

        // when & then
        var actualMsg = assertThrows(exception, () -> account.withdraw(withdrawAmt)).getMessage();
        assertEquals(errMsg, actualMsg);
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> sourceForWithdrawBlackbox_balanceMustMoreThanAmount() {
        var errException = NotEnoughBalanceException.class;
        var errMsg = "Balance must be more than amount";
        return Stream.of(
                Arguments.of("Balance > 0 and withdraw amount > balance", 1000.00, 2000.00, errException, errMsg),
                Arguments.of("Balance = 0 and withdraw amount > 0", 0.00,  1000.00, errException, errMsg)
        );
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> sourceForWithdrawBlackbox_amountMustPositive() {
        var errException = IllegalArgumentException.class;
        var errMsg = "Amount must be positive";
        return Stream.of(
                Arguments.of("Balance > 0 and withdraw amount = 0", 2000.00, 0.00, errException, errMsg),
                Arguments.of("Balance > 0 and withdraw amount < 0", 2000.00, -1000.00, errException, errMsg),
                Arguments.of("Balance = 0 and withdraw amount = 0", 0.00, 0.00, errException, errMsg),
                Arguments.of("Balance = 0 and withdraw amount < 0", 0.00, -1000.00, errException, errMsg)
        );
    }
}
