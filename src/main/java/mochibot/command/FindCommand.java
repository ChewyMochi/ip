package mochibot.command;

import mochibot.task.TaskList;

import mochibot.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            String currTask = tasks.getTask(i).toString();
            if (currTask.contains(this.keyword)) {
                foundTasks.addTask(tasks.getTask(i));
            }
        }
        if (foundTasks.isEmpty()) {
            ui.printNoFoundTasks(this.keyword);
        } else {
            ui.printFoundTasks(foundTasks);
        }
    }
}
