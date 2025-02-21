package blob;

import java.util.Scanner;

/**
 * Represents the Ui class, the basic running of the application.
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
     * Returns the hello message.
     */
    public static String helloMessage() {
        return "Welcome sleepyhead! I'm Blob!!\n"
                + "I help you do the things that you are too lazy to do yourself...like tracking your tasks!"
                + " Let's begin :)";
    }

    /**
     * Returns the exit message.
     */
    public String byeMessage() {
        return "Thank you. Before you go, huggie for Blob? ";
    }

    /**
     * Reads the user input.
     *
     * @return the input command in String format.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Loads the loading error message.
     */
    public String loadingError() {
        return "Error loading data file. Starting with an empty task list.";
    }

    /**
     * Displays the given message.
     *
     * @param message the given message.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Displays invalid command message.
     */
    public String invalidCommandMessage() {
        return "Please key in a valid task! Blob doesn't know what you want!!";
    }

    /**
     * Plays an error message with the given message.
     *
     * @param message the input message.
     */
    public String error(String message) {
        return "ERROR: " + message;
    }

    /**
     * Returns a reply to the user's repeated Hi(s);
     */
    public String hiReplyMessage() {
        return "yes hello! Cut to the chase. Zzz Blob ain't got all day. Blob needs to sleep!! -.-";
    }
}
