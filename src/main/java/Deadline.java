/**
 * This class represents the Deadline task type.
 */
class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline class.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This method represents deadline details in String form that can be printed.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * This method converts the Deadline task from input form to data form.
     */
    @Override
    public String serialise() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
