package mochibot.command;

import mochibot.MochiBotException;

import mochibot.task.TaskList;

import mochibot.ui.Ui;

/**
 * This abstract class provides the abstract method {@code execute} for
 * child classes to implement, and {@code hasExit} method to indicate the user
 * exiting the program.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui) throws MochiBotException;

    public boolean hasExit() {
        return false;
    }
}
