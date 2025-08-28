package mochibot.command;

import mochibot.MochiBotException;

import mochibot.task.TaskList;
import mochibot.task.Todo;

import mochibot.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {
    private TaskList tasks;
    private Ui ui;

    @BeforeEach
    void setup() {
        tasks = new TaskList();
        ui = new Ui();
        Todo todo = new Todo("eat bread");
        this.tasks.addTask(todo);
    }

    @Test
    public void execute_validTaskIndex_successDeleteTask() throws MochiBotException {
        DeleteCommand c = new DeleteCommand(0);
        c.execute(tasks, ui);
        assertTrue(tasks.isEmpty(), "Task was not deleted.");
    }

    @Test
    public void execute_invalidTaskIndex_exceptionThrown() {
        DeleteCommand c = new DeleteCommand(1);
        assertThrows(MochiBotException.InvalidTaskIndexException.class, () -> c.execute(tasks, ui));
    }
}
