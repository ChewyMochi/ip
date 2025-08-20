public class Event extends Task {
    private final String eventStart;
    private final String eventEnd;

    public Event(String description, String eventStart, String eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.eventStart, this.eventEnd);
    }
}
