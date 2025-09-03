package mochibot.ui;

import mochibot.task.Task;
import mochibot.task.TaskList;

/**
 * This class contains a list of methods to print out display messages
 * corresponding to the command given by the user.
 */
public class Gui {

    public String displayGreeting() {
        return "Hello! I'm MochiBot. ( '3')9.\n" + "What can I do for you?";
    }

    public String displayExit() {
        return "Bye! Hope to see you again soon (>;-;)>.";
    }

    public String displayList(TaskList tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            stringBuilder.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n"));
        return stringBuilder.toString();
    }

    public String displayEmptyList() {
        return "Your list is empty! Feel free to add tasks 6('v')9.";
    }

    public String displayAddTask(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + String.format("Now you have %d task(s) in the list.%n", tasks.getSize());
    }

    public String displayRemoveTask(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + String.format("Now you have %d task(s) in the list.%n", tasks.getSize());
    }

    public String displayMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.toString() + "\n";
    }

    public String displayUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + task.toString() + "\n";
    }

    public String displayFoundTasks(TaskList tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            stringBuilder.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n"));
        return stringBuilder.toString();
    }

    public String displayNoFoundTasks(String keyword) {
        return String.format("No tasks were found given your keyword: %s%n", keyword);
    }

    public String displayErrorMessage(String errorMessage) {
        return errorMessage;
    }
}
