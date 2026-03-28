package com.cs.ku.present.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemporalUtilTest {

    @Test
    void testZonedDateTimeToString_success() {
        // Given
        var expected = "2024-06-01 10:34:56.789000+0700";
        var zdt = ZonedDateTime.parse("2024-06-01T12:34:56.789+09:00[Asia/Bangkok]");

        // When
        String actual = TemporalUtil.toString(zdt);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void testLocalDateToString_success() {
        // Given
        var expected = "2026-01-01";
        var localDate = LocalDate.of(2026, 1, 1);

        // When
        String actual = TemporalUtil.toString(localDate);

        // Then
        assertEquals(expected, actual);
    }

}
