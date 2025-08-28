package mochibot.command;

import mochibot.MochiBotException;

import mochibot.storage.Storage;

import mochibot.task.Task;
import mochibot.task.TaskList;

import mochibot.ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

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
