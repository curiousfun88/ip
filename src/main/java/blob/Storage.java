package blob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Handles loading and saving tasks from a specific file.
 */
public class Storage {
    private final String filePath;
    private final String directoryPath;

    /**
     * Constructs a Storage object for managing task persistence.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.directoryPath = new File(filePath).getParent(); // Extract directory path

        File file = new File(filePath);
        // Ensure the directory exists before creating a new file
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            file.createNewFile(); // Create a new empty file
        } catch (IOException e) {
            System.out.println("Error creating new file: " + e.getMessage());
        }
    }

    /**
     * Saves tasks to the specified file.
     *
     * @param tasks The TaskList to save.
     */
    public void saveTasks(TaskList tasks) {
        File directory = new File(directoryPath);

        // Ensure the directory exists before writing
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.println(task.serialize());
            }
            System.out.println("Tasks successfully saved to: " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving tasks to " + filePath + ": " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return A TaskList containing the saved tasks.
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Data file does not exist at " + filePath + ". Returning empty task list.");
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Task task = Task.deserialize(line);
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
            System.out.println("Tasks successfully loaded from: " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from " + filePath + ": " + e.getMessage());
        }
        return tasks;
    }
}
