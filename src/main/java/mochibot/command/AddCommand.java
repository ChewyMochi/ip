package mochibot.command;

import mochibot.storage.Storage;
import mochibot.task.Task;
import mochibot.task.TaskList;
import mochibot.ui.Gui;
import mochibot.ui.Ui;

/**
 * <p>
 *     This class represents an Add command, providing the method {@code execute}
 *     to add tasks into the task list.
 * </p>
 * <p>
 *     The constructor for the {@code AddCommand} class takes in a {@link Task} object.
 * </p>
 */
public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the {@code AddCommand} object to add tasks to the task list,
     * saves the task list to local text file, and displays message
     * indicating task being added.
     *
     * @param tasks {@link TaskList} object that stores all tasks.
     * @param ui {@link Ui} object to print displays.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(this.task);
        ui.printAddTask(this.task, tasks);
        Storage.saveTaskList(tasks);
    }

    @Override
    public String execute(TaskList tasks, Gui gui) {
        tasks.addTask(this.task);
        Storage.saveTaskList(tasks);
        return gui.displayAddTask(this.task, tasks);
    }
}
