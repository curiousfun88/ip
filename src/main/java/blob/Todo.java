package blob;

/**
 * This class represents the Todo task type.
 */
class Todo extends Task {
    /**
     * Constructor for Todo class.
     *
     * @param description Todo Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * This method represents todo details in String form that can be printed.
     *
     * @return Todo task in String format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * This method converts the Todo task from input form to data form.
     *
     * @return Todo task in storage format.
     */
    @Override
    public String serialize() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
