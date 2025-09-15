package mochibot.parser;

import mochibot.command.Command;
import mochibot.command.ListCommand;

/**
 * This class is responsible for handling the "list" command input.
 */
public class ListParser {

    /**
     * Parses a "list" command input and returns a corresponding {@code ListCommand}.
     *
     * @return a {@code ListCommand} to display the list of tasks
     */
    public static Command parse() {
        return new ListCommand();
    }
}
