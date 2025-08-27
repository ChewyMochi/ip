public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui) throws MochiBotException;

    public boolean hasExit() {
        return false;
    }
}
