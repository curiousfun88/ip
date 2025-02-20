package blob;

/**
 * This class represents Blob, the friendly chatbot
 */

public class Blob {
    private TaskList tasks;
    private Storage storage;
    private final Ui ui;

    /**
     * Constructs a Blob instance with the specified file path for task storage.
     */
    public Blob() {
        ui = new Ui();
        try {
            this.storage = new Storage("./ip/data/blob.txt");
            tasks = storage.loadTasks();
        } catch (Exception e) {
            ui.loadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Parser parse = new Parser(tasks, storage, ui);
        String result = parse.processCommand(input);
        return result;
    }
}
