public class Task {
    private final String taskName;
    private boolean isDone;

    public Task() {
        this.taskName = "";
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String printStatement = "";
        if (this.isDone) {
            printStatement += "[X] ";
        } else {
            printStatement += "[ ] ";
        }
        printStatement += this.taskName;
        return printStatement;
    }
}
