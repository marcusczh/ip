package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Creates an event with a start time
     * @param description the description of the event
     * @param at the start time of the event
     */
    public Event(String description, String at) {
        super(description, "E");
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getAt() {
        return this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    /**
     * @return String representation of the event
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (at: " + parseDateTime(at) + ")";
    }
}