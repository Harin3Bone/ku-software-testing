package com.cs.ku.shape;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeometryTest {

    private static final String EQUILATERAL = "Equilateral";
    private static final String ISOSCELES = "Isosceles";
    private static final String SCALENE = "Scalene";
    private static final String INVALID = "Invalid";

    @ParameterizedTest
    @MethodSource(value = {
            "getTriangleTypeStatementCoverage",
            "getTriangleTypeDecisionCoverage"
    })
    void testGetTriangleType(int a, int b, int c, String expected) {
        System.out.println("Test getTriangleType with sides: " + a + ", " + b + ", " + c);

        // when
        var actual = Geometry.getTriangleType(a, b, c);

        // then
        System.out.println("Expected: " + expected + ", Actual: " + actual);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getTriangleTypeStatementCoverage() {
        return Stream.of(
                Arguments.of(0, 1, 1, INVALID),
                Arguments.of(1, 2, 4, INVALID),
                Arguments.of(3, 4, 5, SCALENE),
                Arguments.of(3, 3, 3, EQUILATERAL),
                Arguments.of(3, 3, 4, ISOSCELES),
                Arguments.of(4, 3, 3, ISOSCELES),
                Arguments.of(3, 4, 3, ISOSCELES),
                Arguments.of(1, 1, 2, INVALID)
        );
    }

    private static Stream<Arguments> getTriangleTypeDecisionCoverage() {
        return Stream.of(
                Arguments.of(0, 0, 1, INVALID),
                Arguments.of(0, 1, 0, INVALID),
                Arguments.of(-1, 1, 1, INVALID),
                Arguments.of(1, -1, 1, INVALID),
                Arguments.of(1, 1, -1, INVALID),
                Arguments.of(1, 4, 2, INVALID),
                Arguments.of(4, 1, 2, INVALID),
                Arguments.of(2, 1, 1, INVALID),
                Arguments.of(1, 2, 1, INVALID)
        );
    }

}
