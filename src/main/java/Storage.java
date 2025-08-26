import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./src/data/MochiBot.txt";

    /**
     * Writes contents of taskList into a text file on the device.
     *
     * @param taskList {@link ArrayList} containing {@link Task} objects.
     * @throws RuntimeException If there is an error during the write operation into the text file.
     */
    public static void saveTaskList(ArrayList<Task> taskList) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : taskList) {
                writer.write(formatSaveTask(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Loads contents of text file containing tasks into the Task List.
     *
     * @return {@link ArrayList} containing {@link Task} objects.
     * @throws FileNotFoundException If the file cannot be found by the Scanner object
     */
    public static ArrayList<Task> loadTaskList() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>(100);
        File file = new File(FILE_PATH);
        if (file.exists()) {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String taskData = reader.nextLine();
                Task task = formatLoadTask(taskData);
                taskList.add(task);
            }
        }
        return taskList;
    }

    /**
     * Formats the tasks in the Task List into a format suitable for the text file.
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
                yield String.format("E | %B | %s | %s | %s", task.isDone(), task.getDescription(), event.getEventStart(), event.getEventEnd());
            }
            default -> "";
        };
    }

    /**
     * Converts string representation of tasks in the text file into Task objects
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
            LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return new Deadline(taskDescription, isDone, deadlineDateTime);
        case "E":
            String eventStart = taskParams[3];
            String eventEnd = taskParams [4];
            return new Event(taskDescription, isDone, eventStart, eventEnd);
        default:
            throw new IllegalStateException("Unexpected task received: " + taskData);
        }
    }
}
