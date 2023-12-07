package africa.semicolon.gossipVille;

import africa.semicolon.gossipVille.controllers.DiaryController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Main {
    private static DiaryController controller = new DiaryController();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        diaryMainMenu();
    }

    private static void diaryMainMenu() {
        String menu = """
                ==================
                1 -> Register
                2 -> Login
                3 -> Write
                4 -> Find Entries
                5 -> Exit
                ==================
                """;
       String response =  input(menu);
       services(response);
    }

    private static void services(String response) {
        switch(response){
            case "1" -> register();
            case "2" -> login();
            case "3" -> writeOn();
            case "4" -> findEntries();
            case "5" -> exit();
        }
    }

    private static void exit() {
        System.exit(9);
    }

    private static void findEntries() {
        String username = input("Enter ya user_name");
        System.out.println(controller.findEntryBelongingTo(username));
        diaryMainMenu();
    }

    private static void writeOn() {
        String name =  input("Enter your name: ");
        String title = input("Enter title:");
        String body = input("Enter ya body: ");
        System.out.println(controller.createEntry(name, title, body));
        diaryMainMenu();
    }

    private static void login() {
        String username =  input("Enter your name: ");
        String password = input("Enter your password: ");
        System.out.println(controller.login(username, password));
        diaryMainMenu();
    }

    private static void register() {
        String name =  input("Enter your name: ");
        String password = input("Enter your password: ");
//        System.out.println(controller.register(name, password));
        diaryMainMenu();
    }

    private static String input(String menu) {
        System.out.println(menu);
        return scanner.nextLine();
    }
}