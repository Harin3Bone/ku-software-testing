package com.cs.ku.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
