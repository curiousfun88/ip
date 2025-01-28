import java.util.*;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

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
            throw new BlobException("Please ensure all event details are entered! Blob can't read your mind!!");
        }
        Task event = new Event(description, startTime, endTime);
        tasks.add(event);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + event);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void deleteTask(String command) throws BlobException {
        if (command.split(" ").length < 2) {
            throw new BlobException("WAIT!!! Please tell Blob which task to delete!.");
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
}
