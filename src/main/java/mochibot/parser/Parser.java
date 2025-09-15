package mochibot.parser;

import mochibot.MochiBotException;
import mochibot.command.Command;


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
        CommandType command = parseCommand(inputs[0]);

        return switch (command) {
        case LIST -> ListParser.parse();
        case MARK -> MarkParser.parse(inputs);
        case UNMARK -> UnmarkParser.parse(inputs);
        case TODO -> TodoParser.parse(inputs);
        case DEADLINE -> DeadlineParser.parse(inputs);
        case EVENT -> EventParser.parse(inputs);
        case DELETE -> DeleteParser.parse(inputs);
        case FIND -> FindParser.parse(inputs);
        case BYE -> ByeParser.parse();
        };
    }
}
