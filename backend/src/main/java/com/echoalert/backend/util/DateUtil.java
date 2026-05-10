package com.echoalert.backend.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    private DateUtil() {
        // Prevent object creation
    }

    // Common formatters
    public static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static final DateTimeFormatter ISO_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Current DateTime
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    // Format LocalDateTime
    public static String format(LocalDateTime dateTime) {

        if (dateTime == null) {
            return null;
        }

        return dateTime.format(DATE_TIME_FORMAT);
    }

    // Format Custom Pattern
    public static String format(LocalDateTime dateTime,
                                DateTimeFormatter formatter) {

        if (dateTime == null) {
            return null;
        }

        return dateTime.format(formatter);
    }

    // Parse String to LocalDateTime
    public static LocalDateTime parse(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMAT);
    }

    // Parse with Custom Formatter
    public static LocalDateTime parse(String dateTime,
                                      DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateTime, formatter);
    }
}