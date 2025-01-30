import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void helloMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Blob!!");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void byeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void loadingError() {
        System.out.println("Error loading data file. Starting with an empty task list.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void invalidCommandMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Please key in a valid task! Blob doesn't know what you want!!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays an error message with the given message.
     */
    public void error(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    ERROR: " + message);
        System.out.println("    ____________________________________________________________");
    }
}
