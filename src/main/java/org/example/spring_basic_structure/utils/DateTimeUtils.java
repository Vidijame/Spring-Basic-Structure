package org.example.spring_basic_structure.utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static final String PATTERN_DTM_8 = "yyyyMMdd";
    public static final String PATTERN_DTM_14 = "yyyyMMddHHmmss";

    public static final String CUSTOM_PATTERN = "yyyy-MM-dd hh:mm a";

    public static String formatLocalDateTime(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }
    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    public static String formatCustomDateTime(String dateTimeStr) {
        LocalDateTime dateTime = parseLocalDateTime(dateTimeStr, PATTERN_DTM_14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CUSTOM_PATTERN);
        return dateTime.format(formatter);
    }
}
