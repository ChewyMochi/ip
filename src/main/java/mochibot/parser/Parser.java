package mochibot.parser;

import java.time.LocalDateTime;
import java.util.Arrays;

import mochibot.MochiBotException;
import mochibot.command.AddCommand;
import mochibot.command.Command;
import mochibot.command.DeleteCommand;
import mochibot.command.ExitCommand;
import mochibot.command.FindCommand;
import mochibot.command.ListCommand;
import mochibot.command.MarkCommand;
import mochibot.command.UnmarkCommand;
import mochibot.task.Deadline;
import mochibot.task.Event;
import mochibot.task.Todo;
import mochibot.util.DateTimeParser;

/**
 * The {@code Parser} class is responsible for interpreting user input commands
 * and converting them into executable {@link Command} objects.
 * <p>
 * This class handles different command types such as {@code todo}, {@code deadline},
 * {@code event}, {@code mark}, {@code unmark}, {@code delete}, {@code find},
 * {@code list}, and {@code bye}. Invalid inputs will result in
 * {@link mochibot.MochiBotException} being thrown.
 * </p>
 */
public class Parser {

    /**
     * Parses the full command input from the user and returns the corresponding
     * {@link Command} object to be executed.
     *
     * @param fullCommand the raw user input string
     * @return a {@link Command} object representing the user's request
     * @throws MochiBotException if the command is invalid, missing required arguments,
     *     or contains malformed input such as a non-numeric index.
     */
    public static Command parse(String fullCommand) throws MochiBotException {
        String[] inputs = fullCommand.trim().split(" ");
        String command = inputs[0];
        String taskName;
        int taskIndex;

        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
            try {
                taskIndex = Integer.parseUnsignedInt(inputs[1]);
                if (command.equals("mark")) {
                    return new MarkCommand(taskIndex - 1);
                }
                return new UnmarkCommand(taskIndex - 1);
            } catch (NumberFormatException e) {
                throw new MochiBotException.InvalidTaskIndexException();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MochiBotException.MissingTaskIndexException();
            }
        case "todo":
            taskName = joinArray(inputs, 1, inputs.length);
            if (taskName.isEmpty()) {
                throw new MochiBotException.MissingTaskNameException();
            }
            Todo todo = new Todo(taskName);
            return new AddCommand(todo);
        case "deadline":
            int dateByIndex = getStringIndex(inputs, "/by");
            if (dateByIndex == -1) {
                throw new MochiBotException.MissingDeadlineArgumentsException();
            }
            taskName = joinArray(inputs, 1, dateByIndex);
            if (taskName.isEmpty()) {
                throw new MochiBotException.MissingTaskNameException();
            }
            String deadlineDate = joinArray(inputs, dateByIndex + 1, inputs.length);
            if (deadlineDate.isEmpty()) {
                throw new MochiBotException.MissingDateException();
            }
            LocalDateTime deadlineDateTime = DateTimeParser.parseInput(deadlineDate);
            Deadline deadline = new Deadline(taskName, deadlineDateTime);
            return new AddCommand(deadline);
        case "event":
            int dateFromIndex = getStringIndex(inputs, "/from");
            int dateToIndex = getStringIndex(inputs, "/to");
            if (dateFromIndex == -1 || dateToIndex == -1) {
                throw new MochiBotException.MissingEventArgumentsException();
            }
            taskName = joinArray(inputs, 1, dateFromIndex);
            if (taskName.isEmpty()) {
                throw new MochiBotException.MissingTaskNameException();
            }
            String eventStart = joinArray(inputs, dateFromIndex + 1, dateToIndex);
            String eventEnd = joinArray(inputs, dateToIndex + 1, inputs.length);
            if (eventStart.isEmpty() || eventEnd.isEmpty()) {
                throw new MochiBotException.MissingDateException();
            }
            LocalDateTime eventStartDateTime = DateTimeParser.parseInput(eventStart);
            LocalDateTime eventEndDateTime = DateTimeParser.parseInput(eventEnd);
            Event event = new Event(taskName, eventStartDateTime, eventEndDateTime);
            return new AddCommand(event);
        case "delete":
            try {
                taskIndex = Integer.parseUnsignedInt(inputs[1]);
                return new DeleteCommand(taskIndex - 1);
            } catch (NumberFormatException e) {
                throw new MochiBotException.InvalidTaskIndexException();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MochiBotException.MissingTaskIndexException();
            }
        case "find":
            String keyword = joinArray(inputs, 1, inputs.length);
            if (keyword.isEmpty()) {
                throw new MochiBotException.MissingFindKeywordException();
            }
            return new FindCommand(keyword);
        case "bye":
            return new ExitCommand();
        default:
            throw new MochiBotException.InvalidCommandException();
        }
    }

    /**
     * Joins a range of strings from the specified array into a single string
     * separated by spaces.
     *
     * @param array      the source array
     * @param startIndex the starting index (inclusive)
     * @param endIndex   the ending index (exclusive)
     * @return a single string with the elements joined by spaces, or an empty string if the range is empty
     */
    private static String joinArray(String[] array, int startIndex, int endIndex) {
        String[] newArray = Arrays.copyOfRange(array, startIndex, endIndex);
        return String.join(" ", newArray).trim();
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
