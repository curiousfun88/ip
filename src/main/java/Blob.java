/**
 * This class represents Blob, the friendly chatbot
 */

public class Blob {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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

    public static void main(String[] args) {
        new Blob("data/blob.txt").run();
    }
}