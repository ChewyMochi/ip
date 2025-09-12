package mochibot.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        assert description != null : "description must not be null";
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        assert description != null : "description must not be null";
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
