package blob;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

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
     * @param tasks the TaskList involved.
     * @param ui the UI involved.
     */
    public Parser(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * This method processes the commands and returns the response for the GUI.
     *
     * @param command input command.
     * @return response message for the GUI.
     */
    public String processCommand(String command) {
        try {
            if (command.equals("bye")) {
                String byeMessage = ui.byeMessage();
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {
                    Platform.exit();
                });
                pause.play();
                return byeMessage;
            } else if (command.equals("list")) {
                return tasks.listTasks();
            } else if (command.startsWith("deadlineslist")) {
                return tasks.listSameDeadlineTasks(command);
            } else if (command.startsWith("mark")) {
                String response = tasks.markTask(command);
                storage.saveTasks(tasks);
                return response;
            } else if (command.startsWith("unmark")) {
                String response = tasks.unmarkTask(command);
                storage.saveTasks(tasks);
                return response;
            } else if (command.startsWith("todo")) {
                String response = tasks.addTodoTask(command);
                storage.saveTasks(tasks);
                return response;
            } else if (command.startsWith("deadline")) {
                String response = tasks.addDeadlineTask(command);
                storage.saveTasks(tasks);
                return response;
            } else if (command.startsWith("event")) {
                String response = tasks.addEventTask(command);
                storage.saveTasks(tasks);
                return response;
            } else if (command.startsWith("delete")) {
                String response = tasks.deleteTask(command);
                storage.saveTasks(tasks);
                return response;
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
}
