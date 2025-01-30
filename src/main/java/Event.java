/**
 * This class represents the Event task type.
 */
class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for Event class.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * This method represents event details in String form that can be printed.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * This method convert the Event task from input form to data form.
     */
    @Override
    public String serialise() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " to " + to;
    }

}

