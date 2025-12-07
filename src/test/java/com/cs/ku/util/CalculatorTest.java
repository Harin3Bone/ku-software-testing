package com.cs.ku.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    @Test
    void testAddPositive() {
        // given
        var calculator = new Calculator();

        // when
        var actual = calculator.add(1, 2);

        // then
        assertEquals(3, actual);
    }

    @Test
    void testAddNegative() {
        // given
        var calculator = new Calculator();

        // when
        var actual = calculator.add(-3, -4);

        // then
        assertEquals(-7, actual, "must add correctly");
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {
            "Add positive, 1, 2, 3",
            "Add negative, -3, -4, -7",
            "Add zero, 0, 0, 0",
            "Add positive precision, 2.5, 3.5, 6.0",
            "Add positive and negative, -1.5, 1.5, 0.0"
    }, delimiter = ',')
    void testAdd_shouldPass(String scenarioName, double a, double b, double expected) {
        // given
        var calculator = new Calculator();

        // when
        var actual = calculator.add(a, b);

        // then
        assertEquals(expected, actual, "must add correctly");
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {
        "Subtract positive, 5, 3, 2",
        "Subtract negative, -3, -4, 1",
        "Subtract zero, 0, 0, 0",
        "Subtract positive precision, 5.5, 2.5, 3.0",
        "Subtract positive and negative, -1.5, 1.5, -3.0"
    }, delimiter = ',')
    void testSubtract_shouldPass(String scenarioName, double a, double b, double expected) {
        // given
        var calculator = new Calculator();

        // when
        var actual = calculator.subtract(a, b);

        // then
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {
        "Multiply positive, 2, 3, 6",
        "Multiply negative, -3, -4, 12",
        "Multiply positive and negative, -2, 3, -6",
        "Multiply by zero, 5, 0, 0",
        "Multiply positive precision, 2.5, 4.0, 10.0"
    }, delimiter = ',')
    void testMultiply_shouldPass(String scenarioName, double a, double b, double expected) {
        // given
        var calculator = new Calculator();

        // when
        var actual = calculator.multiply(a, b);

        // then
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {
        "Divide positive, 6, 3, 2",
        "Divide negative, -8, -4, 2",
        "Divide positive and negative, -9, 3, -3",
        "Divide by precision, 7.5, 2.5, 3.0"
    }, delimiter = ',')
    void testDivide_shouldPass(String scenarioName, double a, double b, double expected) {
        // given
        var calculator = new Calculator();

        // when
        var actual = calculator.divide(a, b);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void testDivide_shouldFailure() {
        // given
        var calculator = new Calculator();

        // When & Then
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {
        "Positive power positive, 2, 3, 8",
        "Positive power zero, 5, 0, 1",
        "Positive power negative, 2, -2, -1",
        "Negative power positive, -2, 3, -8",
        "Negative power zero, -7, 0, 1",
        "Negative power negative, -4, -4, -1",
        "One power positive, 1, 100, 1",
        "One power negative, 1, -100, -1",
        "Zero power positive, 0, 5, 0"
    }, delimiter = ',')
    void testPower_shouldPass(String scenarioName, int base, int exponent, int expected) {
        // given
        var calculator = new Calculator();

        // when
        var actual = calculator.power(base, exponent);

        // then
        assertEquals(expected, actual);
    }

}
