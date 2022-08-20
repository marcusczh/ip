import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a task with a deadline
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseDate(by) + ")";
    }
}
