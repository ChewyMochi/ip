package mochibot.command;

import mochibot.MochiBotException;
import mochibot.storage.Storage;
import mochibot.task.Task;
import mochibot.task.TaskList;
import mochibot.ui.Ui;

/**
 * <p>
 *     This class represents an Unmark command, providing the method {@code execute}
 *     to unmark a task in the task list.
 * </p>
 * <p>
 *     The constructor takes in a {@code taskIndex} to indicate the task being unmarked.
 * </p>
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the {@code UnmarkCommand} object to unmark a task in the task list,
     * saves the task list to the local text file, and displays message indicating
     * task being unmarked.
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
        task.markNotDone();
        ui.printUnmarkTask(task);
        Storage.saveTaskList(tasks);
    }
}
