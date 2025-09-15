package mochibot.parser;

import java.util.Arrays;

import mochibot.MochiBotException;
import mochibot.command.Command;
import mochibot.command.FindCommand;

public class FindParser {

    public static Command parse(String[] inputs) throws MochiBotException {
        String keyword = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
        if (keyword.isEmpty()) {
            throw new MochiBotException.MissingFindKeywordException();
        }
        return new FindCommand(keyword);
    }
}
