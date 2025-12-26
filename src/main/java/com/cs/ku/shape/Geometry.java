package com.cs.ku.shape;

public class Geometry {

    private static final int EQUILATERAL = 0;
    private static final int ISOSCELES = 1;
    private static final int SCALENE = 2;
    private static final int INVALID = 3;
    private static final String[] triangleType =
            {"Equilateral", "Isosceles", "Scalene", "Invalid"};

    public static String getTriangleType(int a, int b, int c) {
        int type;
        if ((a <= 0) || (b <= 0) || (c <= 0)) {
            return triangleType[INVALID];
        }
        type = 0;
        if (a == b) {
            type = type + 1;
        }
        if (a == c) {
            type = type + 2;
        }
        if (b == c) {
            type = type + 3;
        }
        if (type == 0) {
            if (((a + b) < c) || ((a + c) < b) || ((b + c) < a)) {
                return triangleType[INVALID];
            } else {
                return triangleType[SCALENE];
            }
        }
        if (type > 3) {
            return triangleType[EQUILATERAL];
        }
        if ((type == 1) && ((a + b) > c)) {
            return triangleType[ISOSCELES];
        } else if ((type == 2) && ((a + c) > b)) {
            return triangleType[ISOSCELES];
        } else if ((type == 3) && ((b + c) > a)) {
            return triangleType[ISOSCELES];
        }
        return triangleType[INVALID];
    }

}
