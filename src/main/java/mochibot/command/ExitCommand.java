package mochibot.command;

import mochibot.storage.Storage;

import mochibot.task.TaskList;

import mochibot.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printExit();
        Storage.saveTaskList(tasks);
    }

    @Override
    public boolean hasExit() {
        return true;
    }
}
