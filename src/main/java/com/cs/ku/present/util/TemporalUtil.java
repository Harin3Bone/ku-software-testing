package com.cs.ku.present.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemporalUtil {

    private static final DateTimeFormatter LCD_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter ZDT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSZ");

    public static String toString(ZonedDateTime in) {
        if (in == null) {
            throw new NullPointerException("ZonedDateTime is null");
        }

        return in.format(ZDT_FORMAT);
    }

    public static String toString(LocalDate in) {
        if (in == null) {
            throw new NullPointerException("LocalDate is null");
        }

        return in.format(LCD_FORMAT);
    }
}
