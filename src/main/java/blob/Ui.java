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
    public static String helloMessage() {
        return "Welcome sleepyhead! I'm Blob!!\n"
                + "I help you do the things that you are too lazy to do yourself...like tracking your tasks!"
                + " Let's begin :)";
    }

    /**
     * This method returns the exit message.
     */
    public String byeMessage() {
        return "Thank you. Before you go, huggie for Blob? ";
    }

    /**
     * This method reads the user input.
     *
     * @return the input command in String format.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * This method loads the loading error message.
     */
    public String loadingError() {
        return "Error loading data file. Starting with an empty task list.";
    }

    /**
     * This method displays the given message.
     *
     * @param message the given message.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * This method displays invalid command message.
     */
    public String invalidCommandMessage() {
        return "Please key in a valid task! Blob doesn't know what you want!!";
    }

    /**
     * The method plays an error message with the given message.
     *
     * @param message the input message.
     */
    public String error(String message) {
        return "ERROR: " + message;
    }

    /**
     * The method returns a reply to the user's repeated Hi(s);
     */
    public String hiReplyMessage() {
        return "yes hello! Cut to the chase. Zzz Blob ain't got all day. Blob needs to sleep!! -.-";
    }
}
