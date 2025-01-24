import java.util.*;

public class Blob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Blob!!");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {

            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    int index = i + 1;
                    Task content = tasks.get(i);
                    System.out.println("    " + index + ". " + content);
                }
                System.out.println("    ____________________________________________________________");
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                Task selected = tasks.get(index - 1);
                selected.markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("        " + selected);
                System.out.println("    ____________________________________________________________");
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                Task selected = tasks.get(index - 1);
                selected.markAsNotDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("        " + selected);
                System.out.println("    ____________________________________________________________");
            } else if (command.startsWith("todo")) {
                Task todo = new Todo(command.substring(5));
                tasks.add(todo);
                System.out.println("    ____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + todo);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else if (command.startsWith("deadline")) {
                int startIndex = 9;
                int endIndex = command.indexOf("/");
                int byIndex = endIndex + 4;
                String description = command.substring(startIndex, endIndex);
                String due = command.substring(byIndex);
                Task deadline = new Deadline(description, due);
                tasks.add(deadline);
                System.out.println("    ____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + deadline);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else if (command.startsWith("event")) {
                int startIndex = 6;
                int fromIndex = command.indexOf("/from");
                String description = command.substring(startIndex, fromIndex);
                int toIndex = command.indexOf("/to");
                String startTime = command.substring(fromIndex + 6, toIndex);
                String endTime = command.substring(toIndex + 4);
                Task event = new Event(description, startTime, endTime);
                tasks.add(event);
                System.out.println("    ____________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + event);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");

            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: " + command);
                System.out.println("    ____________________________________________________________");
                tasks.add(new Task(command));
            }
        }
        scanner.close();
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
