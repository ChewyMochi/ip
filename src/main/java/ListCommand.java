public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.isEmpty()) {
            ui.printEmptyList();
        } else {
            ui.printList(tasks);
        }
    }
}
