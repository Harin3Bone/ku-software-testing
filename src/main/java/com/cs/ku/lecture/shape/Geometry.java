package com.cs.ku.lecture.shape;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Geometry {

    private static final int EQUILATERAL = 0;
    private static final int ISOSCELES = 1;
    private static final int SCALENE = 2;
    private static final int INVALID = 3;
    private static final String[] triangleType = {"Equilateral", "Isosceles", "Scalene", "Invalid"};

    public static String getTriangleType(int a, int b, int c) {
        if (hasNonPositiveSide(a, b, c)) {
            return triangleType[INVALID];
        }
        int equalSides = countEqualSidePairs(a, b, c);
        if (equalSides > 3) {
            return triangleType[EQUILATERAL];
        }
        if (equalSides == 0) {
            return isValidTriangle(a, b, c) ? triangleType[SCALENE] : triangleType[INVALID];
        }
        return isValidIsosceles(a, b, c, equalSides) ? triangleType[ISOSCELES] : triangleType[INVALID];
    }

    private static boolean hasNonPositiveSide(int a, int b, int c) {
        return a <= 0 || b <= 0 || c <= 0;
    }

    private static int countEqualSidePairs(int a, int b, int c) {
        int count = 0;
        if (a == b) count += 1;
        if (a == c) count += 2;
        if (b == c) count += 3;
        return count;
    }

    private static boolean isValidTriangle(int a, int b, int c) {
        return (a + b) > c && (a + c) > b && (b + c) > a;
    }

    private static boolean isValidIsosceles(int a, int b, int c, int equalSides) {
        return switch (equalSides) {
            case 1 -> (a + b) > c;
            case 2 -> (a + c) > b;
            case 3 -> (b + c) > a;
            default -> false;
        };
    }

}
