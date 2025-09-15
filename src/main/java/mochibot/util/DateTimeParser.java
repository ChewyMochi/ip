package mochibot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code DateTimeParser} class is responsible for handling the
 * format patterns for {@code LocalDateTime} objects.
 */
public class DateTimeParser {
    private static final DateTimeFormatter defaultFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");

    /**
     * Parses the String input and outputs a {@code LocalDateTime} object
     * based on a specified format.
     *
     * @param input the date and time in string
     * @return a {@code LocalDateTime} object in the format of yyyy-MM-dd HH:mm
     */
    public static LocalDateTime parseInput(String input) {
        return LocalDateTime.parse(input, defaultFormat);
    }

    /**
     * Formats the given {@code LocalDateTime} object into a string
     * for storage in a text file using the pattern: {@code MMM-dd-yyyy HH:mm}.
     *
     * @param dateTime the {@link LocalDateTime} to format
     * @return a formatted date-time string suitable for storage
     */
    public static String formatDateTimeStorage(LocalDateTime dateTime) {
        return dateTime.format(defaultFormat);
    }

    /**
     * Formats the given {@link LocalDateTime} object into a string for display
     * using the pattern {@code MMM-dd-yyyy HH:mm}.
     *
     * @param dateTime the {@link LocalDateTime} to format
     * @return a formatted date-time string suitable for user display
     */
    public static String formatDateTimeDisplay(LocalDateTime dateTime) {
        return dateTime.format(displayFormat);
    }
}
