import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Storage {
    private static final String FILE_PATH = "./src/data/MochiBot.txt";

    public static void saveTaskList(ArrayList<Task> taskList) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : taskList) {
                writer.write(formatTask(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static String formatTask(Task task) {
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
}
