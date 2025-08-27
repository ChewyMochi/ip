package mochibot.storage;

import mochibot.task.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./src/data/MochiBot.txt";

    /**
     * Writes contents of the task list into a text file on the device.
     *
     * @param tasks {@link TaskList} containing {@link Task} objects.
     * @throws RuntimeException If there is an error during the write operation into the text file.
     */
    public static void saveTaskList(TaskList tasks) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(formatSaveTask(tasks.getTask(i)) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Loads contents of text file containing tasks into the task list.
     *
     * @return {@link TaskList} containing {@link Task} objects.
     * @throws FileNotFoundException If the file cannot be found by the Scanner object
     */
    public static TaskList loadTaskList() throws FileNotFoundException {
        TaskList tasks = new TaskList();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String taskData = reader.nextLine();
                Task task = formatLoadTask(taskData);
                tasks.addTask(task);
            }
        }
        return tasks;
    }

    /**
     * Formats the tasks in the task list into a format suitable for the text file.
     *
     * @param task {@link Task} object
     * @return String representation of the tasks suitable for the text file.
     */
    private static String formatSaveTask(Task task) {
        String taskType = task.getType();
        return switch (taskType) {
            case "[T]" -> String.format("T | %B | %s", task.isDone(), task.getDescription());
            case "[D]" -> {
                Deadline deadline = (Deadline) task;
                yield String.format("D | %B | %s | ", task.isDone(), task.getDescription())
                        + deadline.getDeadlineDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
            case "[E]" -> {
                Event event = (Event) task;
                yield String.format("E | %B | %s | ", task.isDone(), task.getDescription())
                        + event.getEventStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                        + " | "
                        + event.getEventEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
            default -> "";
        };
    }

    /**
     * Converts string representation of tasks in the text file into MochiBot.Task objects
     *
     * @param taskData String representation of a single task from the text file.
     * @return {@link Task} object
     */
    private static Task formatLoadTask(String taskData) {
        String[] taskParams = taskData.split(" \\| ");
        String taskType = taskParams[0];
        boolean isDone = taskParams[1].equals("TRUE");
        String taskDescription = taskParams[2];

        switch (taskType) {
        case "T":
            return new Todo(taskDescription, isDone);
        case "D":
            String deadlineDate = taskParams[3];
            LocalDateTime deadlineDateTime = LocalDateTime
                    .parse(deadlineDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return new Deadline(taskDescription, isDone, deadlineDateTime);
        case "E":
            String eventStart = taskParams[3];
            String eventEnd = taskParams [4];
            LocalDateTime eventStartDateTime = LocalDateTime
                    .parse(eventStart, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime eventEndDateTime = LocalDateTime
                    .parse(eventEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return new Event(taskDescription, isDone, eventStartDateTime, eventEndDateTime);
        default:
            throw new IllegalStateException("Unexpected task received: " + taskData);
        }
    }
}
