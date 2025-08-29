package mochibot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

public class Parser {

    public static Command parse(String fullCommand) throws MochiBotException {
        fullCommand = fullCommand.trim();
        String[] inputs = fullCommand.split(" ");
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
            LocalDateTime deadlineDateTime = LocalDateTime
                    .parse(deadlineDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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
            LocalDateTime eventStartDateTime = LocalDateTime
                    .parse(eventStart, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime eventEndDateTime = LocalDateTime
                    .parse(eventEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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

    private static String joinArray(String[] array, int startIndex, int endIndex) {
        String[] newArray = Arrays.copyOfRange(array, startIndex, endIndex);
        return String.join(" ", newArray).trim();
    }

    private static int getStringIndex(String[] strArray, String item) {
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
}
