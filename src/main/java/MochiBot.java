import java.util.Scanner;

public class MochiBot {
    public static void main(String[] args) {
        boolean has_exit = false;
        greet();
        while (!has_exit) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if (input.equals("bye")) {
                exit();
                has_exit = true;
            } else {
                System.out.println("______________________________________________");
                System.out.println(input);
                System.out.println("______________________________________________");
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
}
