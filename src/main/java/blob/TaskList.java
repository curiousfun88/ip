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
     * This method adds the task to the TaskList.
     *
     * @param task the Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * This method lists the existing tasks in the TaskList.
     */
    public void listTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            Task content = tasks.get(i);
            System.out.println("    " + index + ". " + content);
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * This method marks the task as done.
     *
     * @param command the input that houses the task index to be marked.
     * @throws BlobException if input does not match task format.
     */
    public void markTask(String command) throws BlobException {
        if (command.split(" ").length < 2) {
            throw new BlobException("WAIT!!! Please tell Blob which task to mark as done!.");
        }
        int index = Integer.parseInt(command.split(" ")[1]);
        Task selected = tasks.get(index - 1);
        selected.markAsDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("        " + selected);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * This method unmarks the tasks as undone.
     *
     * @param command the input that houses the task index to be unmarked.
     * @throws BlobException if input does not match task format.
     */
    public void unmarkTask(String command) throws BlobException {
        if (command.split(" ").length < 2) {
            throw new BlobException("WAIT!!! Please tell Blob which task to mark as undone!.");
        }
        int index = Integer.parseInt(command.split(" ")[1]);
        Task selected = tasks.get(index - 1);
        selected.markAsNotDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("        " + selected);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * This method adds the Todo task to the TaskList.
     *
     * @param command the input that houses the Todo Task to be added.
     * @throws BlobException if input does not match task format.
     */
    public void addTodoTask(String command) throws BlobException {
        String description = command.substring(4).trim();
        if (description.isEmpty()) {
            throw new BlobException("Key in your task! You can't be doing nothing lazy bum!!");
        }
        Task todo = new Todo(description);
        tasks.add(todo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + todo);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * This method adds the Deadline task to the TaskList.
     *
     * @param command the input that houses the Deadline Task to be added.
     * @throws BlobException if input does not match task format.
     */
    public void addDeadlineTask(String command) throws BlobException {
        int startIndex = 8;
        int endIndex = command.indexOf("/");
        int byIndex = endIndex + 4;
        if (endIndex == -1 || command.length() <= byIndex) {
            throw new BlobException("HOLD ON! Blob doesn't know when your task is due! Please key in!!");
        }
        String description = command.substring(startIndex, endIndex).trim();
        String due = command.substring(byIndex).trim();
        Task deadline = new Deadline(description, due);
        tasks.add(deadline);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + deadline);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * This method adds the Event task to the TaskList.
     *
     * @param command the input that houses the Event Task to be added.
     * @throws BlobException if input does not match task format.
     */
    public void addEventTask(String command) throws BlobException {
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
            throw new BlobException("Please ensure all event details are entered! blob.Blob can't read your mind!!");
        }
        Task event = new Event(description, startTime, endTime);
        tasks.add(event);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + event);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * This method deletes the task from the TaskList.
     *
     * @param command the input that houses the task index to be deleted.
     * @throws BlobException if input does not match task format.
     */
    public void deleteTask(String command) throws BlobException {
        if (command.split(" ").length < 2) {
            throw new BlobException("WAIT!!! Please tell Blob which task to delete!!");
        }
        int index = Integer.parseInt(command.split(" ")[1]);
        Task selected = tasks.get(index - 1);
        tasks.remove(selected);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Blob has removed the task below! Hooray!! Lesser tasks for Blob!");
        System.out.println("        " + selected);
        System.out.println("    Now you have " + tasks.size() + " tasks left to do!! Faster do faster Blob can sleep!!!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * This method finds all tasks with the keyword from the TaskList.
     *
     * @param command which contains the keyword.
     */
    public void findTask(String command) throws BlobException {
        if (command.split(" ").length < 2) {
            throw new BlobException("WAIT!!! No keyword! How do you expect Blob to find?!");
        }
        String keyword = command.split(" ")[1];
        TaskList result = new TaskList();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                result.add(task);
            }
        }

        if (result.isEmpty()) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    Blob cannot find any matching tasks!!");
            System.out.println("    ____________________________________________________________");
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("    Blob has kindly collated all tasks with the keyword " + keyword + "! No need thank Blob!!");
            for (int i = 0; i < result.size(); i++) {
                int index = i + 1;
                Task content = result.get(i);
                System.out.println("    " + index + ". " + content);
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    /**
     * This method ensures the TaskList is iterable.
     *
     * @return iterator for the TaskList.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * This method displays the list of tasks with the same deadline.
     *
     * @param command the input that houses the specific date to list the same day deadlines.
     */
    public void listSameDeadlineTasks(String command) {
        String[] components = command.split(" ", 2);
        if (components.length > 1) {
            String dateString = components[1];

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            try {
                LocalDate targetDate = LocalDate.parse(dateString, dateFormatter);
                List<Deadline> deadlines = Deadline.getDeadlinesOnSameDay(targetDate, tasks);

                System.out.println("    ____________________________________________________________");
                System.out.println("    Here's what's due on " + targetDate + "! Please thank Blob for reminding you!!");
                for (Deadline deadline : deadlines) {
                    System.out.println("    " + deadline);
                }
                System.out.println("    ____________________________________________________________");
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy/MM/dd.");
            }
        } else {
            System.out.println("Invalid input format! Please use the format: deadlineslist yyyy/MM/dd");
        }
    }

    /**
     * This method checks whether the TaskList is empty.
     *
     * @return boolean whether the TaskList is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * This method returns the number of tasks in the TaskList.
     *
     * @return size of TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * This method returns the specific task at index.
     *
     * @param index specific task index.
     * @return the Task at the corresponding index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
