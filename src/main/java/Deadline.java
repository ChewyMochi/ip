public class Deadline extends Task {
    private final String deadlineDate;

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadlineDate);
    }
}
