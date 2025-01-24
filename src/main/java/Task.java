/**
 * This class represents the Task superclass.
 */
class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task class.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method obtains the current task status (marked/unmarked).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method helps to mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * This method helps to mark the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * This method represents task details in String form that can be printed.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
