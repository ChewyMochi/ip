import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class MochiBot {
    public static ArrayList<Task> taskList = new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int taskIndex;
        String[] taskNameArray;
        String task;

        greet();
        while (true) {
            String userInput = scan.nextLine();
            userInput = userInput.trim();
            String[] inputArray = userInput.split(" ");
            String command = inputArray[0];
            try {
                switch (command) {
                case "bye":
                    exit();
                    System.exit(0);
                case "list":
                    if (taskList.isEmpty()) {
                        System.out.println("______________________________________________");
                        System.out.println("Your list is empty! Feel free to add tasks 6('v')9.");
                        System.out.println("______________________________________________");
                    } else {
                        displayTasks();
                    }
                    break;
                case "mark":
                    try {
                        taskIndex = Integer.parseInt(inputArray[1]);
                    } catch (NumberFormatException e) {
                        throw new MochiBotException.InvalidTaskIndexException();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MochiBotException.MissingTaskIndexException();
                    }
                    if (isValidTaskNum(taskIndex)) {
                        markTask(taskIndex);
                    } else {
                        throw new MochiBotException.InvalidTaskIndexException();
                    }
                    break;
                case "unmark":
                    try {
                        taskIndex = Integer.parseInt(inputArray[1]);
                    } catch (NumberFormatException e) {
                        throw new MochiBotException.InvalidTaskIndexException();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MochiBotException.MissingTaskIndexException();
                    }
                    if (isValidTaskNum(taskIndex)) {
                        unmarkTask(taskIndex);
                    } else {
                        throw new MochiBotException.InvalidTaskIndexException();
                    }
                    break;
                case "todo":
                    taskNameArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);
                    task = String.join(" ", taskNameArray);
                    task = task.trim();
                    if (task.isEmpty()) {
                        throw new MochiBotException.MissingTaskNameException();
                    }
                    addTodo(task);
                    break;
                case "deadline":
                    int dateIndex = findStringIndex(inputArray, "/by");
                    if (dateIndex == -1) {
                        throw new MochiBotException.MissingDeadlineArgumentsException();
                    }
                    taskNameArray = Arrays.copyOfRange(inputArray, 1, dateIndex);
                    task = String.join(" ", taskNameArray);
                    task = task.trim();
                    if (task.isEmpty()) {
                        throw new MochiBotException.MissingTaskNameException();
                    }
                    String[] dateArray = Arrays.copyOfRange(inputArray, dateIndex + 1, inputArray.length);
                    String deadlineDate = String.join(" ", dateArray);
                    if (deadlineDate.isEmpty()) {
                        throw new MochiBotException.MissingDateException();
                    }
                    LocalDateTime deadlineDateTime = LocalDateTime
                            .parse(deadlineDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    addDeadline(task, deadlineDateTime);
                    break;
                case "event":
                    int dateStartIndex = findStringIndex(inputArray, "/from");
                    int dateEndIndex = findStringIndex(inputArray, "/to");
                    if (dateStartIndex == -1 || dateEndIndex == -1) {
                        throw new MochiBotException.MissingEventArgumentsException();
                    }
                    taskNameArray = Arrays.copyOfRange(inputArray, 1, dateStartIndex);
                    task = String.join(" ", taskNameArray);
                    task = task.trim();
                    if (task.isEmpty()) {
                        throw new MochiBotException.MissingTaskNameException();
                    }
                    String[] dateStartArray = Arrays.copyOfRange(inputArray, dateStartIndex + 1, dateEndIndex);
                    String eventStartDate = String.join(" ", dateStartArray);
                    String[] dateEndArray = Arrays.copyOfRange(inputArray, dateEndIndex + 1, inputArray.length);
                    String eventEndDate = String.join(" ", dateEndArray);
                    if (eventStartDate.isEmpty() || eventEndDate.isEmpty()) {
                        throw new MochiBotException.MissingDateException();
                    }
                    LocalDateTime eventStartDateTime = LocalDateTime
                            .parse(eventStartDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    LocalDateTime eventEndDateTime = LocalDateTime
                            .parse(eventEndDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    addEvent(task, eventStartDateTime, eventEndDateTime);
                    break;
                case "delete":
                    try {
                        taskIndex = Integer.parseInt(inputArray[1]);
                    } catch (NumberFormatException e) {
                        throw new MochiBotException.InvalidTaskIndexException();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MochiBotException.MissingTaskIndexException();
                    }
                    if (isValidTaskNum(taskIndex)) {
                        deleteTask(taskIndex);
                    } else {
                        throw new MochiBotException.InvalidTaskIndexException();
                    }
                    break;
                default:
                    throw new MochiBotException.InvalidCommandException();
                }
            } catch (MochiBotException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void greet() {
        System.out.println("______________________________________________");
        System.out.println("Hello! I'm MochiBot ( '3')9.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");
        try {
            taskList = Storage.loadTaskList();
        } catch (FileNotFoundException e) {
            System.out.println("Scanner cannot find file to read.");
        }
    }

    public static void exit() {
        Storage.saveTaskList(taskList);
        System.out.println("______________________________________________");
        System.out.println("Bye! Hope to see you again soon ( ;-;)>.");
        System.out.println("______________________________________________");
    }

    public static void displayTasks() {
        System.out.println("______________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
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

    public static void addDeadline(String task, LocalDateTime dateTime) {
        Deadline currDeadline = new Deadline(task, dateTime);
        taskList.add(currDeadline);
        System.out.println("______________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(currDeadline);
        System.out.printf("Now you have %d task(s) in the list.%n", taskList.size());
        System.out.println("______________________________________________");
    }

    public static void addEvent(String task, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Event currEvent = new Event(task, startDateTime, endDateTime);
        taskList.add(currEvent);
        System.out.println("______________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(currEvent);
        System.out.printf("Now you have %d task(s) in the list.%n", taskList.size());
        System.out.println("______________________________________________");
    }

    public static void deleteTask(int taskIndex) {
        Task currTask = taskList.remove(taskIndex - 1);
        System.out.println("______________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(currTask);
        System.out.printf("Now you have %d task(s) in the list.%n", taskList.size());
        System.out.println("______________________________________________");
    }

    public static boolean isValidTaskNum(int taskNum) {
        return taskNum <= taskList.size() && taskNum > 0;
    }
}
