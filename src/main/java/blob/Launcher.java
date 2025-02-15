package blob;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The entry point for launching the JavaFX application.
     * This method initiates the JavaFX application by calling the
     * launch method on the Main class, passing the command-line arguments.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

