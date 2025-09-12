package mochibot.command;

import mochibot.task.TaskList;
import mochibot.ui.Gui;
import mochibot.ui.Ui;

/**
 * This class represents a Find command, providing the method {@code execute}
 * to find tasks that match the specified keyword and print them.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            String currTask = tasks.getTask(i).toString();
            if (currTask.contains(this.keyword)) {
                foundTasks.addTask(tasks.getTask(i));
            }
        }
        if (foundTasks.isEmpty()) {
            ui.printNoFoundTasks(this.keyword);
        } else {
            ui.printFoundTasks(foundTasks);
        }
    }

    @Override
    public String execute(TaskList tasks, Gui gui) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            String currTask = tasks.getTask(i).toString();
            if (currTask.contains(this.keyword)) {
                foundTasks.addTask(tasks.getTask(i));
            }
        }
        if (foundTasks.isEmpty()) {
            return gui.displayNoFoundTasks(this.keyword);
        } else {
            return gui.displayFoundTasks(foundTasks);
        }
    }
}
