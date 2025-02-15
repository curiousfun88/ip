package blob;

/**
 * This class represents the Event task type.
 */
class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for Event class.
     *
     * @param description Event description.
     * @param from Event start time.
     * @param to Event end time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * This method represents event details in String form that can be printed.
     *
     * @return Event Task in String format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * This method converts the Event task from input form to data form.
     *
     * @return Event Task in storage format.
     */
    @Override
    public String serialize() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " to " + to;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time as a string.
     */
    public String getStartTime() {
        return this.from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time as a string.
     */
    public String getEndTime() {
        return this.to;
    }

}

