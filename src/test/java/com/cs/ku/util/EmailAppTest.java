package com.cs.ku.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class EmailAppTest {

    private EmailApp emailApp;

    @BeforeEach
    void setup() {
        log.info("Reset EmailApp.");
        emailApp = new EmailApp();
    }

    @ParameterizedTest
    @MethodSource({
            "sourceForDecisionCoverage",
            "sourceForConditionCoverage",
            "sourceForConditionDecisionCoverage",
            "sourceForMcDcCoverage"
    })
    void testRegister(String email, String password, int age, boolean expected) {
        // when
        var actual = emailApp.register(email, password, age);

        // then
        assertEquals(expected, actual);
    }

    @SuppressWarnings("java:S1144")
    private static Stream<Arguments> sourceForDecisionCoverage() {
        return Stream.of(
                Arguments.of("harin.thananam@gmail.com", "harin_1997@TH", 27, true),
                Arguments.of("harin.thananam.com", "harin_1997@TH", 27, false)
        );
    }

    @SuppressWarnings("java:S1144")
    private static Stream<Arguments> sourceForConditionCoverage() {
        return Stream.of(
                Arguments.of("harit.jumleoyrak@gmail.com", "harit_2009@EN", 17, false),
                Arguments.of("harin.thananam.com", "harin", 27, false)
        );
    }

    @SuppressWarnings("java:S1144")
    private static Stream<Arguments> sourceForConditionDecisionCoverage() {
        return Stream.of(
                Arguments.of("harin.thananam@gmail.com", "harin_1997@TH", 27, true),
                Arguments.of("harit.jumleoyrak", "harit", 17, false)
        );
    }

    @SuppressWarnings("java:S1144")
    private static Stream<Arguments> sourceForMcDcCoverage() {
        return Stream.of(
                Arguments.of("harin.thananam@gmail.com", "harin_1997@TH", 27, true),
                Arguments.of("harin.thananam.com", "harin_1997@TH", 27, false),
                Arguments.of("harin.thananam@gmail.com", "harin", 27, false),
                Arguments.of("harit.jumleoyrak@gmail.com", "harit_2009@EN", 17, false)
        );
    }

}
