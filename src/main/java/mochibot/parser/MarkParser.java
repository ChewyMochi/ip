package mochibot.parser;

import mochibot.MochiBotException;
import mochibot.command.Command;
import mochibot.command.MarkCommand;

public class MarkParser {

    public static Command parse(String[] inputs) throws MochiBotException {
        try {
            int taskIndex = Integer.parseUnsignedInt(inputs[1]);
            return new MarkCommand(taskIndex - 1);
        } catch (NumberFormatException e) {
            throw new MochiBotException.InvalidTaskIndexException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MochiBotException.MissingTaskIndexException();
        }
    }
}
