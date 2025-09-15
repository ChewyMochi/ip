package mochibot.parser;

import java.time.LocalDateTime;
import java.util.Arrays;

import mochibot.MochiBotException;
import mochibot.command.AddCommand;
import mochibot.command.Command;
import mochibot.task.Event;
import mochibot.util.DateTimeParser;

public class EventParser {

    public static Command parse(String[] inputs) throws MochiBotException {
        int dateFromIndex = getStringIndex(inputs, "/from");
        int dateToIndex = getStringIndex(inputs, "/to");
        if (dateFromIndex == -1 || dateToIndex == -1) {
            throw new MochiBotException.MissingEventArgumentsException();
        }
        String taskName = String.join(" ", Arrays.copyOfRange(inputs, 1, dateFromIndex));
        if (taskName.isEmpty()) {
            throw new MochiBotException.MissingTaskNameException();
        }
        String eventStart = String.join(" ", Arrays.copyOfRange(inputs, dateFromIndex + 1, dateToIndex));
        String eventEnd = String.join(" ", Arrays.copyOfRange(inputs, dateToIndex + 1, inputs.length));
        if (eventStart.isEmpty() || eventEnd.isEmpty()) {
            throw new MochiBotException.MissingDateException();
        }
        LocalDateTime eventStartDateTime = DateTimeParser.parseInput(eventStart);
        LocalDateTime eventEndDateTime = DateTimeParser.parseInput(eventEnd);
        Event event = new Event(taskName, eventStartDateTime, eventEndDateTime);
        return new AddCommand(event);
    }

    /**
     * Searches the specified array for the first occurrence of the given item
     * and returns its index.
     *
     * @param strArray the array to search
     * @param item     the string to find
     * @return the index of the first occurrence of {@code item}, or -1 if not found
     */
    private static int getStringIndex(String[] strArray, String item) {
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
}
