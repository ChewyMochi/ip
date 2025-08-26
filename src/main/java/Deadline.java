public class Deadline extends Task {
    private final String deadlineDate;

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public Deadline(String description, boolean isDone, String deadlineDate) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
    }


    public String getType() {
        return "[D]";
    }

    public String getDeadlineDate() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        return this.getType() + super.toString() + String.format(" (by: %s)", this.deadlineDate);
    }
}
