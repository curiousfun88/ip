package blob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class represents the Storage class for storing data.
 */
public class Storage {
    private final String filePath;
    private final String directoryPath;

    /**
     * Constructor for Storage.
     *
     * @param filePath File Path to the file involved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.directoryPath = new File(filePath).getParent(); // Extracts directory path
    }

    /**
     * This method saves the existing tasks to the TaskList.
     *
     * @param tasks the TaskList involved
     */
    public void saveTasks(TaskList tasks) {
        File directory = new File(directoryPath);

        //check if folder exists
        if (!directory.exists()) {
            directory.mkdir();
        }

        //if file does not exist, create new file
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.println(task.serialize());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * This method loads existing tasks in the TaskList.
     *
     * @return the previously saved TaskList
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        //check if file exists
        if (!file.exists()) {
            System.out.println("Data file does not exist. Returning empty task list.");
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
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}