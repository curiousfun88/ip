package blob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StorageTest {

    private static final String TEST_DIRECTORY_PATH = "test_data";
    private static final String TEST_FILE_PATH = TEST_DIRECTORY_PATH + File.separator + "test_blob.txt";
    private Storage storage;
    private TaskList tasks;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
        tasks = new TaskList();
    }

    @AfterEach
    public void revert() throws IOException {
        //Delete test files after each test
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
        Files.deleteIfExists(Paths.get(TEST_DIRECTORY_PATH));
    }

    @Test
    public void testSaveAndLoadTasks() {
        //Create and add tasks
        Task task1 = new Todo("Buy groceries");
        Task task2 = new Todo("Read book");
        tasks.add(task1);
        tasks.add(task2);

        storage.saveTasks(tasks);

        //Check if file was created
        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists(), "Task file should exist after saving tasks.");

        //Load tasks from file
        TaskList loadedTasks = storage.loadTasks();

        //Check if tasks are correctly loaded
        assertEquals(2, loadedTasks.size(), "Loaded task list should contain two tasks.");
        assertEquals("Buy groceries", loadedTasks.get(0).getDescription(), "First task description should match.");
        assertEquals("Read book", loadedTasks.get(1).getDescription(), "Second task description should match.");
    }

    @Test
    public void testLoadTasksFromNonExistentFile() {
        // Ensure the file does not exist before loading
        File file = new File(TEST_FILE_PATH);
        file.delete();

        //Load tasks from non-existent file
        TaskList loadedTasks = storage.loadTasks();

        //Return empty task list
        assertTrue(loadedTasks.isEmpty(), "Loading from a non-existent file should return an empty task list.");
    }

    @Test
    public void testSkipCorruptedLines() throws IOException {
        //Ensure test/data directory exists before writing to the file
        Files.createDirectories(Paths.get(TEST_DIRECTORY_PATH));

        //Create a test file with one valid and one corrupted line
        Files.write(Paths.get(TEST_FILE_PATH), List.of("T | 0 | Do laundry", "gibberish"));

        TaskList loadedTasks = storage.loadTasks();

        //Should only load the valid task
        assertEquals(1, loadedTasks.size(), "Only valid tasks should be loaded.");
        assertEquals("Do laundry", loadedTasks.get(0).getDescription(), "Valid task should be correctly loaded.");
    }
}
