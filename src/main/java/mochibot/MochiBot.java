package mochibot;

import java.io.FileNotFoundException;

import mochibot.command.Command;
import mochibot.parser.Parser;
import mochibot.storage.Storage;
import mochibot.task.TaskList;
import mochibot.ui.Gui;
import mochibot.ui.Ui;

public class MochiBot {
    // public static ArrayList<MochiBot.Task> taskList = new ArrayList<>(100);
    private TaskList tasks;
    private final Ui ui;
    private final Gui gui;

    public MochiBot() {
        ui = new Ui();
        gui = new Gui();
        try {
            tasks = Storage.loadTaskList();
        } catch (FileNotFoundException e) {
            System.out.println("Scanner cannot find file to read.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.hasExit();
            } catch (MochiBotException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new MochiBot().run();
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
