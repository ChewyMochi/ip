package mochibot.command;

import mochibot.MochiBotException;

import mochibot.task.TaskList;
import mochibot.task.Todo;

import mochibot.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkCommandTest {
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
    public void execute_validTaskIndex_successMarkTask() throws MochiBotException {
        MarkCommand c = new MarkCommand(0);
        c.execute(tasks, ui);
        assertEquals("X", tasks.getTask(0).getStatusIcon(), "Task was not marked.");
    }

    @Test
    public void execute_invalidTaskIndex_exceptionThrown() {
        MarkCommand c = new MarkCommand(1);
        assertThrows(MochiBotException.InvalidTaskIndexException.class, () -> c.execute(tasks, ui));
    }
}
