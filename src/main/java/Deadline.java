import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Deadline task type.
 */
class Deadline extends Task {
    private final LocalDateTime deadline;  // Store deadline as LocalDateTime

    /**
     * Constructor for Deadline class.
     */
    public Deadline(String description, String by) {
        super(description);
        this.deadline = parseDateTime(by);
    }

    /**
     * This method parses the date string to LocalDateTime.
     */
    private LocalDateTime parseDateTime(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d HHmm");  // input format: yyyy/mm/dd HHmm (24h format)
        return LocalDateTime.parse(by, formatter);
    }

    /**
     * This method represents the deadline task in a printable string form.
     */
    @Override
    public String toString() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + deadline.format(output) + ")";
    }

    /**
     * This method converts the Deadline task from input form to data form (for storage).
     */
    @Override
    public String serialise() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"); //output format: month/day/year time (24h format)
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(output);
    }

    /**
     * This method gets the deadline date.
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * This method obtains the deadlines that falls on the same date.
     */
    public static List<Deadline> getDeadlinesOnSameDay(LocalDate targetDate, ArrayList<Task> tasks) {
        List<Deadline> allDeadlines = loadTasksWithDeadlines(tasks);
        List<Deadline> sameDayDeadlines = new ArrayList<>();

        for (Deadline deadlineTask : allDeadlines) {
            LocalDate taskDate = deadlineTask.getDeadline().toLocalDate();  // obtain the date part of the deadline
            if (taskDate.equals(targetDate)) {
                sameDayDeadlines.add(deadlineTask);
            }
        }
        return sameDayDeadlines;
    }

    /**
     * This method loads all deadline tasks with the same date.
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
