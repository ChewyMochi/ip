package mochibot.command;

import mochibot.MochiBotException;
import mochibot.storage.Storage;
import mochibot.task.Task;
import mochibot.task.TaskList;
import mochibot.ui.Gui;
import mochibot.ui.Ui;

/**
 * <p>
 *     This class represents a Mark command, providing the method {@code execute}
 *     to mark a task in the task list.
 * </p>
 * <p>
 *     The constructor takes in a {@code taskIndex} to indicate the task being marked.
 * </p>
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the {@code MarkCommand} object to mark a task in the task list,
     * saves the task list to the local text file, and displays message indicating
     * task being marked.
     *
     * @param tasks {@link TaskList} object to stores all tasks.
     * @param ui {@link Ui} object to print displays.
     * @throws MochiBotException If taskIndex >= size of task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws MochiBotException {
        if (this.taskIndex >= tasks.getSize()) {
            throw new MochiBotException.InvalidTaskIndexException();
        }
        Task task = tasks.getTask(this.taskIndex);
        task.markDone();
        ui.printMarkTask(task);
        Storage.saveTaskList(tasks);
    }

    @Override
    public String execute(TaskList tasks, Gui gui) throws MochiBotException {
        if (this.taskIndex >= tasks.getSize()) {
            throw new MochiBotException.InvalidTaskIndexException();
        }
        Task task = tasks.getTask(this.taskIndex);
        task.markDone();
        Storage.saveTaskList(tasks);
        return gui.displayMarkTask(task);
    }
}
