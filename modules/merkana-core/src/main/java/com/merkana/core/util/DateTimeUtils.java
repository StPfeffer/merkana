package com.merkana.core.util;

import com.merkana.core.filter.LocalDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public final class DateTimeUtils {

    private DateTimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isConvertibleToLocalDateTime(CharSequence dateTime) {
        if (dateTime == null) {
            return false;
        }

        for (LocalDateFormat formatter : LocalDateFormat.values()) {
            try {
                LocalDateTime.parse(dateTime, formatter.getFormatter());
                return true;
            } catch (DateTimeParseException ignore) {
                // Ignore
            }
        }

        return false;
    }

    public static LocalDateTime convertToLocalDateTime(CharSequence dateTime) {
        if (dateTime == null) {
            return null;
        }

        for (LocalDateFormat formatter : LocalDateFormat.values()) {
            try {
                return LocalDateTime.parse(dateTime, formatter.getFormatter());
            } catch (DateTimeParseException ignore) {
                try {
                    LocalDate localDate = LocalDate.parse(dateTime, formatter.getFormatter());
                    return localDate.atStartOfDay().withSecond(0).withNano(0);
                } catch (DateTimeParseException ignore2) {
                    // ignore
                }
            }
        }

        return null;
    }

}
