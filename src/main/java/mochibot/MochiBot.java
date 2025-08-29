package mochibot;

import java.io.FileNotFoundException;

import mochibot.command.Command;
import mochibot.parser.Parser;
import mochibot.storage.Storage;
import mochibot.task.TaskList;
import mochibot.ui.Ui;

public class MochiBot {
    // public static ArrayList<MochiBot.Task> taskList = new ArrayList<>(100);
    private TaskList tasks;
    private final Ui ui;

    public MochiBot() {
        ui = new Ui();
        try {
            tasks = Storage.loadTaskList();
        } catch (FileNotFoundException e) {
            System.out.println("Scanner cannot find file to read.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        boolean hasExit = false;
        while (!hasExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                hasExit = c.hasExit();
            } catch (MochiBotException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new MochiBot().run();
    }
}
