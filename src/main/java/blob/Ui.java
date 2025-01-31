package blob;

import java.util.Scanner;

/**
 * This class represents the Ui class, the basic running of the application.
 */

public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for Ui class. Starts the input scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * This method returns the hello message.
     */
    public void helloMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Blob!!");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * This method returns the exit message.
     */
    public void byeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * This method reads the user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * This method loads the loading error message.
     */
    public void loadingError() {
        System.out.println("Error loading data file. Starting with an empty task list.");
    }

    /**
     * This method displays the given message.
     *
     * @param message the given message.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * This method displays invalid command message.
     */
    public void invalidCommandMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Please key in a valid task! blob.Blob doesn't know what you want!!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * The method isplays an error message with the given message.
     *
     * @param message the input message.
     */
    public void error(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    ERROR: " + message);
        System.out.println("    ____________________________________________________________");
    }
}
