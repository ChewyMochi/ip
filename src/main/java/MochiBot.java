import java.util.Scanner;
import java.util.ArrayList;

public class MochiBot {
    public static ArrayList<Task> itemList = new ArrayList<>(100);

    public static void main(String[] args) {
        boolean has_exit = false;

        greet();
        while (!has_exit) {
            Scanner scan = new Scanner(System.in);
            String command = scan.nextLine();
            switch(command) {
                case "bye":
                    exit();
                    has_exit = true;
                    break;
                case "list":
                    displayItems();
                    break;
                default:
                    addTask(command);
            }
        }
    }

    public static void greet() {
        System.out.println("______________________________________________");
        System.out.println("Hello! I'm MochiBot.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");
    }

    public static void exit() {
        System.out.println("______________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("______________________________________________");
    }

    public static void addTask(String item) {
        Task task = new Task(item);
        itemList.add(task);
        System.out.println("______________________________________________");
        System.out.println("added: " + task.getDescription());
        System.out.println("______________________________________________");
    }

    public static void displayItems() {
        System.out.println("______________________________________________");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(i+1 + ". " + itemList.get(i));
        }
        System.out.println("______________________________________________");
    }
}
