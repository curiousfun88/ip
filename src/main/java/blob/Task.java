package blob;

/**
 * Represents the Task superclass.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task class.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Obtains the current task status (marked/unmarked).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Helps to mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Helps to mark the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns task details in String form that can be printed.
     *
     * @return Task in String format.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts Task from input to storage form.
     *
     * @return Task in storage format.
     */
    public String serialize() {
        return "";
    }

    /**
     * Converts Task from storage to input form.
     *
     * @param data specific storage data.
     * @return Task from the stored format.
     */
    public static Task deserialize(String data) {
        String[] components = data.split(" \\| ");
        String type = components[0];
        boolean isDone = components[1].equals("1");
        String description = components[2];

        switch (type) {
        case "T":
            Todo todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            String by = components[3];
            Deadline deadline = new Deadline(description, by);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            String[] times = components[3].split(" to ");
            Event event = new Event(description, times[0], times[1]);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }

    /**
     * Returns the task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }
}
