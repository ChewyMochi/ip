package mochibot;

import java.io.FileNotFoundException;

import mochibot.command.Command;
import mochibot.parser.Parser;
import mochibot.storage.Storage;
import mochibot.task.TaskList;
import mochibot.ui.Gui;

public class MochiBot {
    private TaskList tasks;
    private final Gui gui;

    public MochiBot() {
        gui = new Gui();
        try {
            tasks = Storage.loadTaskList();
        } catch (FileNotFoundException e) {
            System.out.println("Scanner cannot find file to read.");
            tasks = new TaskList();
        }
    }

    public String displayGreeting() {
        return gui.displayGreeting();
    }

    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, gui);
        } catch (MochiBotException e) {
            response = gui.displayErrorMessage(e.getMessage());
        }
        return response;
    }
}
