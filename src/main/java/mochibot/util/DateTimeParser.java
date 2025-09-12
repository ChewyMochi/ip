package mochibot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
    private static final DateTimeFormatter defaultFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");

    public static LocalDateTime parseInput(String input) {
        return LocalDateTime.parse(input, defaultFormat);
    }

    public static String formatDateTimeStorage(LocalDateTime dateTime) {
        return dateTime.format(defaultFormat);
    }

    public static String formatDateTimeDisplay(LocalDateTime dateTime) {
        return dateTime.format(displayFormat);
    }
}
