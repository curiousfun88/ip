import java.util.*;

public class Blob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Blob!!");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {

            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            }

            System.out.println("    ____________________________________________________________");
            System.out.println("    " + command);
            System.out.println("    ____________________________________________________________");
        }
        scanner.close();
    }
}
