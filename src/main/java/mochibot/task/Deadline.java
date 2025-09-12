package mochibot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadlineDate;

    public Deadline(String description, LocalDateTime deadlineDate) {
        super(description);
        assert deadlineDate != null : "deadlineDate must not be null";
        this.deadlineDate = deadlineDate;
    }

    public Deadline(String description, boolean isDone, LocalDateTime deadlineDate) {
        super(description, isDone);
        assert deadlineDate != null : "deadlineDate must not be null";
        this.deadlineDate = deadlineDate;
    }

    public String getType() {
        return "[D]";
    }

    public LocalDateTime getDeadlineDate() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        return this.getType() + super.toString() + " "
                + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm"));
    }
}
