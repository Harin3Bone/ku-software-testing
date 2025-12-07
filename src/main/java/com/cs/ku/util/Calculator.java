package com.cs.ku.util;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int power(int base, int exponent) {
        int result = 1;
        if (exponent < 0) return -1;

        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        return result;
    }

}
