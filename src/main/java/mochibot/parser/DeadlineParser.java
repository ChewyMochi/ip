package mochibot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import mochibot.MochiBotException;
import mochibot.command.AddCommand;
import mochibot.command.Command;
import mochibot.task.Deadline;
import mochibot.util.DateTimeParser;

public class DeadlineParser {

    public static Command parse(String[] inputs) throws MochiBotException {
        int dateByIndex = getStringIndex(inputs, "/by");
        if (dateByIndex == -1) {
            throw new MochiBotException.MissingDeadlineArgumentsException();
        }
        String taskName = String.join(" ", Arrays.copyOfRange(inputs, 1, dateByIndex));
        if (taskName.isEmpty()) {
            throw new MochiBotException.MissingTaskNameException();
        }
        String deadlineDate = String.join(" ", Arrays.copyOfRange(inputs, dateByIndex + 1, inputs.length));
        if (deadlineDate.isEmpty()) {
            throw new MochiBotException.MissingDateException();
        }
        try {
            LocalDateTime deadlineDateTime = DateTimeParser.parseInput(deadlineDate);
            Deadline deadline = new Deadline(taskName, deadlineDateTime);
            return new AddCommand(deadline);
        } catch (DateTimeParseException e) {
            throw new MochiBotException.InvalidDateTimeFormat();
        }
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
