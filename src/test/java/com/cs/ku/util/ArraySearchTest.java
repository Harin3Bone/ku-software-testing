package com.cs.ku.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class ArraySearchTest {

    private ArraySearch arraySearch;

    @BeforeEach
    void setup() {
        log.info("Reset ArraySearch.");
        arraySearch = new ArraySearch();
    }

    @ParameterizedTest(name = "{0}: {1}")
    @MethodSource({
            "sourceForLoopCoverage",
            "sourceForEdgePairCoverage",
            "sourceForMcCabeCoverage"
    })
    void testFindFirst(String prefixName, int[] array, int findNum, int expectedIndex) {
        log.info("Test for {}: {}", prefixName, array);

        // when
        var actualIndex = arraySearch.findFirst(array, findNum);

        // then
        assertEquals(expectedIndex, actualIndex);
    }

    @SuppressWarnings("java:S1144")
    private static Stream<Arguments> sourceForLoopCoverage() {
        final String prefix = "Loop Coverage";
        return Stream.of(
                Arguments.of(prefix, new int[]{}, 1, -1),
                Arguments.of(prefix, new int[]{1, 2, 3}, 1, 0),
                Arguments.of(prefix, new int[]{1, 2, 3}, 2, 1)
        );
    }

    @SuppressWarnings("java:S1144")
    private static Stream<Arguments> sourceForEdgePairCoverage() {
        final String prefix = "Edge Pair Coverage";
        return Stream.of(
                Arguments.of(prefix, new int[]{}, 10, -1),
                Arguments.of(prefix, new int[]{1, 2, 3}, 2, 1),
                Arguments.of(prefix, new int[]{1, 2}, 3, -1)
        );
    }

    @SuppressWarnings("java:S1144")
    private static Stream<Arguments> sourceForMcCabeCoverage() {
        final String prefix = "McCabe Cyclomatic Coverage";
        return Stream.of(
                Arguments.of(prefix, new int[]{}, 1, -1),
                Arguments.of(prefix, new int[]{1, 2, 3}, 1, 0),
                Arguments.of(prefix, new int[]{1}, 2, -1),
                Arguments.of(prefix, new int[]{1, 2, 3}, 2, 1)
        );
    }

}
