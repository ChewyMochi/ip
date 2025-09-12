package mochibot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  This class represents an {@code Event} task with a specified start and end datetime.
 */
public class Event extends Task {
    private final LocalDateTime eventStart;
    private final LocalDateTime eventEnd;

    /**
     * Constructs a new {@code Event} task with a description,
     * and a specified start and end datetime.
     *
     * @param description The description of the task.
     * @param eventStart The start datetime of the task.
     * @param eventEnd The end datetime of the task.
     */
    public Event(String description, LocalDateTime eventStart, LocalDateTime eventEnd) {
        super(description);
        assert eventStart != null && eventEnd != null : "eventStart must not be null";
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    /**
     * Constructs a new {@code Event} task with a description,
     * a specified start and end datetime and a completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @param eventStart The start datetime of the task.
     * @param eventEnd The end datetime of the task.
     */
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
