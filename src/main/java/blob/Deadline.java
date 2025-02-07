package blob;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Deadline task type.
 */
class Deadline extends Task {
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter RUN_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private final LocalDateTime deadline; // Store deadline as LocalDateTime

    /**
     * Constructor for Deadline class.
     *
     * @param description Deadline Task description.
     * @param by Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.deadline = parseDateTime(by);
    }

    /**
     * This method parses the date string to LocalDateTime.
     *
     * @param by data string to be converted.
     * @return LocalDateTime.
     */
    public static LocalDateTime parseDateTime(String by) {
        try {
            return LocalDateTime.parse(by, STORAGE_FORMAT);
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(by, RUN_FORMAT);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Invalid date format!");
            }
        }
    }

    /**
     * This method represents the deadline task in a printable string form.
     *
     * @return Deadline Task in String format.
     */
    @Override
    public String toString() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + deadline.format(output) + ")";
    }

    /**
     * This method converts the blob.Deadline task from input form to data form (for storage).
     *
     * @return Deadline Task in storage format.
     */
    @Override
    public String serialize() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        //output format: month/day/year time (24h format)
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(output);
    }

    /**
     * This method gets the deadline date.
     *
     * @return LocalDateTime.
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * This method obtains the deadlines that falls on the same date.
     *
     * @param targetDate deadlines that fall on that specified date.
     * @param tasks the TaskList that houses the deadlines.
     * @return list of tasks that have same date deadlines.
     */
    public static List<Deadline> getDeadlinesOnSameDay(LocalDate targetDate, ArrayList<Task> tasks) {
        List<Deadline> allDeadlines = loadTasksWithDeadlines(tasks);
        List<Deadline> sameDayDeadlines = new ArrayList<>();

        for (Deadline deadlineTask : allDeadlines) {
            LocalDate taskDate = deadlineTask.getDeadline().toLocalDate(); // obtain the date part of the deadline
            if (taskDate.equals(targetDate)) {
                sameDayDeadlines.add(deadlineTask);
            }
        }
        return sameDayDeadlines;
    }

    /**
     * This method loads all deadline tasks with the same date.
     *
     * @param tasks the TaskList that houses the deadlines.
     * @return list of tasks with same date deadlines.
     */
    public static List<Deadline> loadTasksWithDeadlines(ArrayList<Task> tasks) {
        List<Deadline> deadlines = new ArrayList<>();

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                deadlines.add((Deadline) task);
            }
        }
        return deadlines;
    }
}
