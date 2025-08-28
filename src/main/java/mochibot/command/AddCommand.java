package mochibot.command;

import mochibot.storage.Storage;

import mochibot.task.Task;
import mochibot.task.TaskList;

import mochibot.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(this.task);
        ui.printAddTask(this.task, tasks);
        Storage.saveTaskList(tasks);
    }
}
