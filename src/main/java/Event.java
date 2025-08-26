public class Event extends Task {
    private final String eventStart;
    private final String eventEnd;

    public Event(String description, String eventStart, String eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public Event(String description, boolean isDone, String eventStart, String eventEnd) {
        super(description, isDone);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public String getType() {
        return "[E]";
    }

    public String getEventStart() {
        return this.eventStart;
    }

    public String getEventEnd() {
        return this.eventEnd;
    }

    @Override
    public String toString() {
        return this.getType() + super.toString() + String.format(" (from: %s to: %s)", this.eventStart, this.eventEnd);
    }
}
