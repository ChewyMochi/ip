package mochibot.command;

import mochibot.MochiBotException;
import mochibot.storage.Storage;
import mochibot.task.Task;
import mochibot.task.TaskList;
import mochibot.ui.Ui;

/**
 * <p>
 *     This class represents a Delete command, providing the method {@code execute}
 *     to delete a task from the task list.
 * </p>
 * <p>
 *     The constructor takes in a {@code taskIndex} to indicate the task being deleted.
 * </p>
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the {@code DeleteCommand} object to delete a task from the task list,
     * saves the task list to the local text file, and displays message indicating
     * task deletion.
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
        Task task = tasks.removeTask(this.taskIndex);
        ui.printRemoveTask(task, tasks);
        Storage.saveTaskList(tasks);
    }
}
