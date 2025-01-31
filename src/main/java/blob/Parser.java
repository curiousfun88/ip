package blob;

/**
 * This class represents the Parser class, which processes commands.
 */
public class Parser {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for Parser class.
     *
     * @param tasks the TaskList involved
     * @param storage the Storage involved
     * @param ui the UI involved
     */
    public Parser(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * This method processes the commands
     *
     * @param command input command
     */
    public void processCommand(String command) {
        try {
            if (command.equals("list")) {
                tasks.listTasks();
            } else if (command.startsWith("deadlineslist")) {
                tasks.listSameDeadlineTasks(command);
            } else if (command.startsWith("mark")) {
                tasks.markTask(command);
                storage.saveTasks(tasks);
            } else if (command.startsWith("unmark")) {
                tasks.unmarkTask(command);
                storage.saveTasks(tasks);
            } else if (command.startsWith("todo")) {
                tasks.addTodoTask(command);
                storage.saveTasks(tasks);
            } else if (command.startsWith("deadline")) {
                tasks.addDeadlineTask(command);
                storage.saveTasks(tasks);
            } else if (command.startsWith("event")) {
                tasks.addEventTask(command);
                storage.saveTasks(tasks);
            } else if (command.startsWith("delete")) {
                tasks.deleteTask(command);
                storage.saveTasks(tasks);
            } else {
                ui.invalidCommandMessage();
            }
        } catch (BlobException e) {
            ui.error(e.getMessage());
        } catch (NumberFormatException e) {
            ui.error("Please specify which task!");
        } catch (Exception e) {
            ui.error("Please input again for blob.Blob!");
        }
    }
}

