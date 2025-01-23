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
