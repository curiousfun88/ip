/**
 * This class represents the Todo task type.
 */
class Todo extends Task {
    /**
     * Constructor for Todo class.
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
}
