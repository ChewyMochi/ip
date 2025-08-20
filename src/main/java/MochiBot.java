import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class MochiBot {
    public static ArrayList<Task> taskList = new ArrayList<>(100);

    public static void main(String[] args) {
        boolean has_exit = false;
        int taskIndex;
        String[] taskNameArray;
        String task;

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
                    displayTasks();
                    break;
                case "mark":
                    taskIndex = Integer.parseInt(inputArray[1]);
                    markTask(taskIndex);
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(inputArray[1]);
                    unmarkTask(taskIndex);
                    break;
                case "todo":
                    taskNameArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);
                    task = String.join(" ", taskNameArray);
                    addTodo(task);
                    break;
                case "deadline":
                    int dateIndex = findStringIndex(inputArray, "/by");
                    taskNameArray = Arrays.copyOfRange(inputArray, 1, dateIndex);
                    task = String.join(" ", taskNameArray);
                    String[] dateArray = Arrays.copyOfRange(inputArray, dateIndex + 1, inputArray.length);
                    String deadlineDate = String.join(" ", dateArray);
                    addDeadline(task, deadlineDate);
                    break;
                case "event":
                    int dateStartIndex = findStringIndex(inputArray, "/from");
                    int dateEndIndex = findStringIndex(inputArray, "/to");
                    taskNameArray = Arrays.copyOfRange(inputArray, 1, dateStartIndex);
                    task = String.join(" ", taskNameArray);
                    String[] dateStartArray = Arrays.copyOfRange(inputArray, dateStartIndex + 1, dateEndIndex);
                    String eventStartDate = String.join(" ", dateStartArray);
                    String[] dateEndArray = Arrays.copyOfRange(inputArray, dateEndIndex + 1, inputArray.length);
                    String eventEndDate = String.join(" ", dateEndArray);
                    addEvent(task, eventStartDate, eventEndDate);
                    break;
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

    public static void displayTasks() {
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

    public static void addTodo(String task) {
        Todo currTodo = new Todo(task);
        taskList.add(currTodo);
        System.out.println("______________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(currTodo);
        System.out.printf("Now you have %d task(s) in the list.%n", taskList.size());
        System.out.println("______________________________________________");
    }

    public static int findStringIndex(String[] strArray, String item) {
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public static void addDeadline(String task, String date) {
        Deadline currDeadline = new Deadline(task, date);
        taskList.add(currDeadline);
        System.out.println("______________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(currDeadline);
        System.out.printf("Now you have %d task(s) in the list.%n", taskList.size());
        System.out.println("______________________________________________");
    }

    public static void addEvent(String task, String startDate, String endDate) {
        Event currEvent = new Event(task, startDate, endDate);
        taskList.add(currEvent);
        System.out.println("______________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(currEvent);
        System.out.printf("Now you have %d task(s) in the list.%n", taskList.size());
        System.out.println("______________________________________________");
    }
}
