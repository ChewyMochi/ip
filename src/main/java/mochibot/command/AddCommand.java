package mochibot.command;

import mochibot.storage.Storage;

import mochibot.task.Task;
import mochibot.task.TaskList;

import mochibot.ui.Ui;

/**
 * This class represents an Add command, providing the method to add tasks
 * into the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the {@link AddCommand} object to add tasks to the task list,
     * saves the task list to local text file and displays message
     * indicating task being added.
     *
     * @param tasks {@link TaskList} object storing a list of added tasks.
     * @param ui {@link Ui} object to print displays.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(this.task);
        ui.printAddTask(this.task, tasks);
        Storage.saveTaskList(tasks);
    }
}
