import java.util.Scanner;
import java.util.ArrayList;

public class MochiBot {
    public static ArrayList<Task> taskList = new ArrayList<>(100);

    public static void main(String[] args) {
        boolean has_exit = false;
        int taskIndex;

        greet();
        while (!has_exit) {
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            String[] inputArray = userInput.split(" ");
            String command = inputArray[0];
            switch(command) {
                case "bye":
                    exit();
                    has_exit = true;
                    break;
                case "list":
                    displayItems();
                    break;
                case "mark":
                    taskIndex = Integer.parseInt(inputArray[1]);
                    markTask(taskIndex);
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(inputArray[1]);
                    unmarkTask(taskIndex);
                    break;
                default:
                    addTask(userInput);
            }
        }
    }

    public static void greet() {
        System.out.println("______________________________________________");
        System.out.println("Hello! I'm MochiBot.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");
    }

    public static void exit() {
        System.out.println("______________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("______________________________________________");
    }

    public static void addTask(String item) {
        Task task = new Task(item);
        taskList.add(task);
        System.out.println("______________________________________________");
        System.out.println("added: " + task.getDescription());
        System.out.println("______________________________________________");
    }

    public static void displayItems() {
        System.out.println("______________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + ". " + taskList.get(i));
        }
        System.out.println("______________________________________________");
    }

    public static void markTask(int taskIndex) {
        Task currTask = taskList.get(taskIndex - 1);
        currTask.markDone();
        System.out.println("______________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currTask);
        System.out.println("______________________________________________");
    }

    public static void unmarkTask(int taskIndex) {
        Task currTask = taskList.get(taskIndex - 1);
        currTask.markNotDone();
        System.out.println("______________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currTask);
        System.out.println("______________________________________________");
    }
}
