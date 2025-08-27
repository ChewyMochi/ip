package mochibot.command;

import mochibot.*;
import mochibot.storage.Storage;
import mochibot.task.Task;
import mochibot.task.TaskList;
import mochibot.ui.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

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
}
