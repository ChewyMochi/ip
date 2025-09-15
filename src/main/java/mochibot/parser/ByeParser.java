package mochibot.parser;

import mochibot.command.Command;
import mochibot.command.ExitCommand;

/**
 * This class is responsible for handling the "bye" command input.
 */
public class ByeParser {

    /**
     * Parses a "bye" command input and returns a corresponding {@code ExitCommand}.
     *
     * @return an {@code ExitCommand} that terminates the application
     */
    public static Command parse() {
        return new ExitCommand();
    }
}
