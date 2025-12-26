package com.cs.ku.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    public double add(Number a, Number b) {
        var bigA = BigDecimal.valueOf(a.doubleValue());
        var bigB = BigDecimal.valueOf(b.doubleValue());
        return bigA.add(bigB).doubleValue();
    }

    public double subtract(Number a, Number b) {
        var bigA = BigDecimal.valueOf(a.doubleValue());
        var bigB = BigDecimal.valueOf(b.doubleValue());
        return bigA.subtract(bigB).doubleValue();
    }

    public double multiply(Number a, Number b) {
        var bigA = BigDecimal.valueOf(a.doubleValue());
        var bigB = BigDecimal.valueOf(b.doubleValue());
        return bigA.multiply(bigB).doubleValue();
    }

    public double divide(Number a, Number b) {
        var bigA = BigDecimal.valueOf(a.doubleValue());
        var bigB = BigDecimal.valueOf(b.doubleValue());

        if (BigDecimal.ZERO.compareTo(bigB) == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        return bigA.divide(bigB, RoundingMode.HALF_UP).doubleValue();
    }

    public int power(int base, int exponent) {
        int result = 1;
        if (exponent < 0) throw new ArithmeticException("Negative exponent not supported.");

        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        return result;
    }

}
