package mochibot.ui;

import java.util.Scanner;

import mochibot.task.Task;
import mochibot.task.TaskList;

/**
 * This class contains a list of methods to print out display messages
 * corresponding to the command given by the user.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void printGreeting() {
        System.out.println("______________________________________________");
        System.out.println("Hello! I'm MochiBot. ( '3')9.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");
    }

    public void printExit() {
        System.out.println("______________________________________________");
        System.out.println("Bye! Hope to see you again soon (>;-;)>.");
        System.out.println("______________________________________________");
    }

    public void printList(TaskList tasks) {
        System.out.println("______________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i));
        }
        System.out.println("______________________________________________");
    }

    public void printEmptyList() {
        System.out.println("______________________________________________");
        System.out.println("Your list is empty! Feel free to add tasks 6('v')9.");
        System.out.println("______________________________________________");
    }

    public void printAddTask(Task task, TaskList tasks) {
        System.out.println("______________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d task(s) in the list.%n", tasks.getSize());
        System.out.println("______________________________________________");
    }

    public void printRemoveTask(Task task, TaskList tasks) {
        System.out.println("______________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d task(s) in the list.%n", tasks.getSize());
        System.out.println("______________________________________________");
    }

    public void printMarkTask(Task task) {
        System.out.println("______________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("______________________________________________");
    }

    public void printUnmarkTask(Task task) {
        System.out.println("______________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("______________________________________________");
    }

    public void printFoundTasks(TaskList tasks) {
        System.out.println("______________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i));
        }
        System.out.println("______________________________________________");
    }

    public void printNoFoundTasks(String keyword) {
        System.out.println("______________________________________________");
        System.out.printf("No tasks were found given your keyword: %s%n", keyword);
        System.out.println("______________________________________________");
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
