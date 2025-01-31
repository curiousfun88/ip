package blob;

/**
 * This class represents Blob, the friendly chatbot
 */

public class Blob {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Blob instance with the specified file path for task storage.
     *
     * @param filePath The path to the storage file where tasks are saved and loaded.
     */
    public Blob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            ui.loadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Blob application, handling user commands in a loop
     * until the "bye" command is entered.
     */
    public void run() {
        ui.helloMessage();

        while (true) {
            String command = ui.readCommand();
            if (command.equals("bye")) {
                ui.byeMessage();
                break;
            }

            Parser parser = new Parser(tasks, storage, ui);
            parser.processCommand(command);
        }
    }

    /**
     * The main entry point for the Blob application.
     * Initialises and starts the program.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new Blob("data/blob.txt").run();
    }
}