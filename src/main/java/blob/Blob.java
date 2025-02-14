package blob;

/**
 * This class represents Blob, the friendly chatbot
 */

public class Blob {
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Blob instance with the specified file path for task storage.
     */
    public Blob() {
        ui = new Ui();
        try {
            tasks = Storage.loadTasks();
        } catch (Exception e) {
            ui.loadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        assert input != null: "User input should not be null";
        Parser parse = new Parser(tasks, ui);
        String result = parse.processCommand(input);
        assert result != null : "Processed command result should not be null";
        return "Blob: " + result;
    }
}
