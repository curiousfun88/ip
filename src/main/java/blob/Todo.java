package blob;

/**
 * This class represents the blob.Todo task type.
 */
class Todo extends Task {
    /**
     * Constructor for blob.Todo class.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * This method represents todo details in String form that can be printed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * This method converts the blob.Todo task from input form to data form.
     */
    @Override
    public String serialise() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

}
