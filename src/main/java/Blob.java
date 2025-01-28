/**
 * This class represents Blob, the friendly chatbot
 */

import java.util.*;

public class Blob {
    /**
     * Implementation of Blob chatbot
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        helloMessage();

        TaskList tasks = new TaskList();

        while (true) {

            String command = scanner.nextLine();

            try {
                if (command.equals("bye")) {
                    byeMessage();
                    break;
                } else if (command.equals("list")) {
                    tasks.listTasks();
                } else if (command.startsWith("mark")) {
                    tasks.markTask(command);
                } else if (command.startsWith("unmark")) {
                    tasks.unmarkTask(command);
                } else if (command.startsWith("todo")) {
                    tasks.addTodoTask(command);
                } else if (command.startsWith("deadline")) {
                    tasks.addDeadlineTask(command);
                } else if (command.startsWith("event")) {
                    tasks.addEventTask(command);
                } else if (command.startsWith("delete")) {
                    tasks.deleteTask(command);
                } else {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Please key in a valid task! Blob doesn't know what you want!!");
                    System.out.println("    ____________________________________________________________");
                }
            } catch (BlobException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    ERROR: " + e.getMessage());
                System.out.println("    ____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    ERROR!! Please specify which task!!");
                System.out.println("    ____________________________________________________________");
            } catch (Exception e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    ERROR!! Please input again for Blob!");
                System.out.println("    ____________________________________________________________");
            }
        }
        scanner.close();
    }

    public static void helloMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Blob!!");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void byeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}