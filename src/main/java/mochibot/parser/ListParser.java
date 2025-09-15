package mochibot.parser;

import mochibot.command.Command;
import mochibot.command.ListCommand;

public class ListParser {

    public static Command parse() {
        return new ListCommand();
    }
}
