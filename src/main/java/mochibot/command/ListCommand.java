package mochibot.command;

import mochibot.task.TaskList;
import mochibot.ui.Gui;
import mochibot.ui.Ui;

/**
 *     This class represents a List command, providing the method {@code execute}
 *     to print all the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the {@code ListCommand} object to print all the tasks in the task list,
     * or print an empty task list message if the task list is empty.
     *
     * @param tasks {@link TaskList} object to stores all tasks.
     * @param ui {@link Ui} object to print displays.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.isEmpty()) {
            ui.printEmptyList();
        } else {
            ui.printList(tasks);
        }
    }

    @Override
    public String execute(TaskList tasks, Gui gui) {
        if (tasks.isEmpty()) {
            return gui.displayEmptyList();
        } else {
            return gui.displayList(tasks);
        }
    }
}
