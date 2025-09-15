package mochibot.parser;

import java.util.Arrays;

import mochibot.MochiBotException;
import mochibot.command.AddCommand;
import mochibot.command.Command;
import mochibot.task.Todo;

public class TodoParser {

    public static Command parse(String[] inputs) throws MochiBotException {
        String taskName = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
        if (taskName.isEmpty()) {
            throw new MochiBotException.MissingTaskNameException();
        }
        Todo todo = new Todo(taskName);
        return new AddCommand(todo);
    }
}
