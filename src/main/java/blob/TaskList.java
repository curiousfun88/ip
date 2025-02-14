package blob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the TaskList class that houses all task operations.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds the task to the TaskList after checking for duplicates.
     * If the task is a duplicate, it will not be added.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Checks if a Todo task with the specified description already exists in the task list.
     *
     * @param description The description of the Todo task.
     * @return true if a Todo task with the same description exists, false otherwise.
     */
    private boolean isDuplicateTodo(String description) {
        return tasks.stream()
                .anyMatch(task -> task.getDescription().equals(description));
    }

    /**
     * Checks if a Deadline task with the specified description and due date already exists in the task list.
     *
     * @param description The description of the Deadline task.
     * @param due The due date of the Deadline task.
     * @return true if a Deadline task with the same description and due date exists, false otherwise.
     */
    private boolean isDuplicateDeadline(String description, String due) {
        return tasks.stream()
                .filter(task -> task instanceof Deadline)
                .anyMatch(deadline -> deadline.getDescription().equals(description)
                        && ((Deadline) deadline).getDeadlineString().equals(due));
    }

    /**
     * Checks if an Event task with the specified description, start time, and end time already exists in the task list.
     *
     * @param description The description of the Event task.
     * @param startTime The start time of the Event.
     * @param endTime The end time of the Event.
     * @return true if an Event task with the same description, start time, and end time exists, false otherwise.
     */
    private boolean isDuplicateEvent(String description, String startTime, String endTime) {
        return tasks.stream()
                .filter(task -> task instanceof Event)
                .anyMatch(event -> event.getDescription().equals(description)
                        && ((Event) event).getStartTime().equals(startTime)
                        && ((Event) event).getEndTime().equals(endTime));
    }

    /**
     * Returns a formatted string of existing tasks in the TaskList.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "Your task list is empty!";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Marks the task as done.
     */
    public String markTask(String command) throws BlobException {
        int index = parseIndex(command);
        Task selected = tasks.get(index - 1);
        selected.markAsDone();
        return "Nice! I've marked this task as done:\n  " + selected;
    }

    /**
     * Unmarks the task as undone.
     */
    public String unmarkTask(String command) throws BlobException {
        int index = parseIndex(command);
        Task selected = tasks.get(index - 1);
        selected.markAsNotDone();
        return "OK, I've marked this task as not done yet:\n  " + selected;
    }

    /**
     * Adds a Todo task to the TaskList.
     */
    public String addTodoTask(String command) throws BlobException {
        String description = command.substring(4).trim();
        if (description.isEmpty()) {
            throw new BlobException("Key in your task! You can't be doing nothing lazy bum!!");
        }
        Task todo = new Todo(description);
        if (isDuplicateTodo(description)) {
            throw new BlobException("Duplicate todo detected. Stop re-keying the same task! "
                    + "Blob may be fat but I will not eat your stuff!!");
        }
        tasks.add(todo);
        return "Got it. I've added this task:\n  " + todo + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Adds a Deadline task to the TaskList.
     */
    public String addDeadlineTask(String command) throws BlobException {
        int startIndex = 8;
        int endIndex = command.indexOf("/");
        int byIndex = endIndex + 4;
        if (endIndex == -1 || command.length() <= byIndex) {
            throw new BlobException("HOLD ON! Blob doesn't know when your task is due! Please key in!!");
        }
        String description = command.substring(startIndex, endIndex).trim();
        String due = command.substring(byIndex).trim();
        Task deadline = new Deadline(description, due);
        if (isDuplicateDeadline(description, due)) {
            throw new BlobException("Duplicate deadline detected. Stop re-keying the same task! "
                    + "Blob may be fat but I will not eat your stuff!!");
        }
        tasks.add(deadline);
        return "Got it. I've added this task:\n  " + deadline
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Adds an Event task to the TaskList.
     */
    public String addEventTask(String command) throws BlobException {
        int startIndex = 5;
        int fromIndex = command.indexOf("/from");
        if (fromIndex == -1) {
            throw new BlobException("Please ensure you enter the required event details before telling Blob!!");
        }
        String description = command.substring(startIndex, fromIndex - 1).trim();
        int toIndex = command.indexOf("/to");
        if (toIndex == -1) {
            throw new BlobException("Please ensure you enter the required event details before telling Blob!!");
        }
        String startTime = command.substring(fromIndex + 5, toIndex).trim();
        String endTime = command.substring(toIndex + 3).trim();
        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new BlobException("Please ensure all event details are entered! Blob can't read your mind!!");
        }
        Task event = new Event(description, startTime, endTime);
        if (isDuplicateEvent(description, startTime, endTime)) {
            throw new BlobException("Duplicate event detected. Stop re-keying the same task! "
                    + "Blob may be fat but I will not eat your stuff!!");
        }
        tasks.add(event);
        return "Got it. I've added this task:\n  " + event + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Deletes a task from the TaskList.
     */
    public String deleteTask(String command) throws BlobException {
        int index = parseIndex(command);
        Task selected = tasks.remove(index - 1);
        return "Blob has removed the task below!\n  " + selected + "\nNow you have " + tasks.size() + " tasks left!";
    }

    /**
     * Finds tasks that contain the given keyword.
     */
    public String findTask(String command) throws BlobException {
        if (command.split(" ").length < 2) {
            throw new BlobException("WAIT!!! No keyword! How do you expect Blob to find?!");
        }
        String keyword = command.split(" ")[1];
        TaskList res = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                res.add(task);
            }
        }
        StringBuilder result = new StringBuilder("Here are the matching tasks:\n");
        boolean found = false;

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                result.append(task).append("\n");
                found = true;
            }
        }

        return found ? result.toString() : "No matching tasks found!";
    }

    /**
     * Lists tasks with the same deadline.
     */
    public String listSameDeadlineTasks(String command) {
        try {
            String dateString = command.split(" ", 2)[1];
            LocalDate targetDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            List<Deadline> deadlines = Deadline.getDeadlinesOnSameDay(targetDate, tasks);

            if (deadlines.isEmpty()) {
                return "No tasks found with deadline on " + targetDate + "!";
            }

            StringBuilder sb = new StringBuilder("Tasks due on " + targetDate + ":\n");
            for (Deadline deadline : deadlines) {
                sb.append(deadline).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Invalid date format. Please use yyyy/MM/dd.";
        }
    }

    /**
     * Checks whether the TaskList is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at a specific index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Ensures TaskList is iterable.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    // Helper methods
    /**
     * This method parses the index.
     */
    private int parseIndex(String command) throws BlobException {
        try {
            return Integer.parseInt(command.split(" ")[1]);
        } catch (Exception e) {
            throw new BlobException("Invalid task index!");
        }
    }
}
