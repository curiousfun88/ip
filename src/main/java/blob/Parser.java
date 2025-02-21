package blob;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Represents the Parser class, which processes commands.
 */
public class Parser {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for Parser class.
     *
     * @param tasks the TaskList involved.
     * @param ui the UI involved.
     */
    public Parser(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Processes the user command and delegates it to specific handlers based on the command.
     *
     * @param command The user input command to process.
     * @return A response message based on the processed command.
     */
    public String processCommand(String command) {
        try {
            if (command.equalsIgnoreCase("bye")) {
                return handleBye();
            } else if (command.equalsIgnoreCase("Hello") || command.equalsIgnoreCase("Hi")) {
                return ui.hiReplyMessage();
            } else if (command.equals("list")) {
                return tasks.listTasks();
            } else if (command.startsWith("deadlineslist")) {
                return tasks.listSameDeadlineTasks(command);
            } else if (command.startsWith("mark")) {
                return handleTaskUpdate(command, true);
            } else if (command.startsWith("unmark")) {
                return handleTaskUpdate(command, false);
            } else if (command.startsWith("todo")) {
                return handleTaskAddition(command, "todo");
            } else if (command.startsWith("deadline")) {
                return handleTaskAddition(command, "deadline");
            } else if (command.startsWith("event")) {
                return handleTaskAddition(command, "event");
            } else if (command.startsWith("delete")) {
                return handleTaskDeletion(command);
            } else if (command.startsWith("find")) {
                return tasks.findTask(command);
            } else {
                return ui.invalidCommandMessage();
            }
        } catch (BlobException e) {
            return ui.error(e.getMessage());
        } catch (NumberFormatException e) {
            return ui.error("Please specify which task!");
        } catch (Exception e) {
            return ui.error("Please input again for Blob!");
        }
    }

    /**
     * Handles the "bye" command with a delayed exit.
     *
     * @return The farewell message to the user.
     */
    private String handleBye() {
        String byeMessage = ui.byeMessage();
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
        return byeMessage;
    }

    /**
     * Handles task update operations like marking/unmarking tasks.
     *
     * @param command The user input command to update a task.
     * @param isMark True for marking tasks, false for unmarking.
     * @return The response message after updating the task.
     */
    private String handleTaskUpdate(String command, boolean isMark) throws BlobException {
        String response;
        if (isMark) {
            response = tasks.markTask(command);
        } else {
            response = tasks.unmarkTask(command);
        }
        storage.saveTasks(tasks);
        return response;
    }

    /**
     * Handles the addition of a new task based on the specified task type.
     *
     * @param command The user input command to add a new task.
     * @param type The type of task to add (e.g., "todo", "deadline", "event").
     * @return The response message after adding the task.
     */
    private String handleTaskAddition(String command, String type) throws BlobException {
        String response;
        switch (type) {
        case "todo":
            response = tasks.addTodoTask(command);
            break;
        case "deadline":
            response = tasks.addDeadlineTask(command);
            break;
        case "event":
            response = tasks.addEventTask(command);
            break;
        default:
            return ui.invalidCommandMessage();
        }
        storage.saveTasks(tasks);
        return response;
    }

    /**
     * Handles the deletion of a task based on the command.
     *
     * @param command The user input command to delete a task.
     * @return The response message after deleting the task.
     */
    private String handleTaskDeletion(String command) throws BlobException {
        String response = tasks.deleteTask(command);
        storage.saveTasks(tasks);
        return response;
    }
}
