package mochibot.parser;

import mochibot.command.Command;
import mochibot.command.ExitCommand;

public class ByeParser {

    public static Command parse() {
        return new ExitCommand();
    }
}
