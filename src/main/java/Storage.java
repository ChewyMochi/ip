import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./src/data/MochiBot.txt";

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

    public static String formatSaveTask(Task task) {
        String taskType = task.getType();
        return switch (taskType) {
            case "[T]" -> String.format("T | %B | %s", task.isDone(), task.getDescription());
            case "[D]" -> {
                Deadline deadline = (Deadline) task;
                yield String.format("D | %B | %s | %s", task.isDone(), task.getDescription(), deadline.getDeadlineDate());
            }
            case "[E]" -> {
                Event event = (Event) task;
                yield String.format("E | %B | %s | %s | %s", task.isDone(), task.getDescription(), event.getEventStart(), event.getEventEnd());
            }
            default -> "";
        };
    }

    public static Task formatLoadTask(String taskData) {
        String[] taskParams = taskData.split(" \\| ");
        String taskType = taskParams[0];
        boolean isDone = taskParams[1].equals("TRUE");
        String taskDescription = taskParams[2];

        switch (taskType) {
        case "T":
            return new Todo(taskDescription, isDone);
        case "D":
            String deadlineDate = taskParams[3];
            return new Deadline(taskDescription, isDone, deadlineDate);
        case "E":
            String eventStart = taskParams[3];
            String eventEnd = taskParams [4];
            return new Event(taskDescription, isDone, eventStart, eventEnd);
        default:
            throw new IllegalStateException("Unexpected task received: " + taskData);
        }
    }
}
