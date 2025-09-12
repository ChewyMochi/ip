package mochibot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime eventStart;
    private final LocalDateTime eventEnd;

    public Event(String description, LocalDateTime eventStart, LocalDateTime eventEnd) {
        super(description);
        assert eventStart != null && eventEnd != null : "eventStart must not be null";
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public Event(String description, boolean isDone, LocalDateTime eventStart, LocalDateTime eventEnd) {
        super(description, isDone);
        assert eventStart != null && eventEnd != null : "eventStart must not be null";
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public String getType() {
        return "[E]";
    }

    public LocalDateTime getEventStart() {
        return this.eventStart;
    }

    public LocalDateTime getEventEnd() {
        return this.eventEnd;
    }

    @Override
    public String toString() {
        return this.getType() + super.toString() + " from "
                + this.eventStart.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm"))
                + " to "
                + this.eventEnd.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm"));
    }
}
