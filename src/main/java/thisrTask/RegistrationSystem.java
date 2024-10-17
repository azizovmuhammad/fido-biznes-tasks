package thisrTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class RegistrationSystem {

    private final HashMap<String, String> users = new HashMap<>();
    private final HashSet<String> loggedInUsers = new HashSet<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        RegistrationSystem system = new RegistrationSystem();
        system.run();
    }

    private void run() {
        int n = getNumberOfOperations();

        for (int i = 0; i < n; i++) {
            displayMenu();
            String operation = getOperation();
            processOperation(operation);
        }

        closeScanner();
        System.out.println("Exiting the system. Goodbye!");
    }

    private int getNumberOfOperations() {
        return Integer.parseInt(getInput("Enter the number of operations: "));
    }

    private void displayMenu() {
        System.out.println("Welcome! Choose an operation:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Logout");
    }

    private String getOperation() {
        String choice = getInput("Choose an option (1, 2, or 3): ");
        return switch (choice) {
            case "1" -> "register";
            case "2" -> "login";
            case "3" -> "logout";
            default -> {
                System.out.println("Invalid option. Please try again.\n");
                yield getOperation();
            }
        };
    }

    private void processOperation(String operation) {
        String username = getInput("Enter username: ");
        String command;

        if ("register".equals(operation) || "login".equals(operation)) {
            String password = getInput("Enter password: ");
            command = operation + " " + username + " " + password;
        } else {
            command = operation + " " + username;
        }

        processCommand(command);
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private void processCommand(String command) {
        String[] parts = command.split(" ");
        String operation = parts[0];

        switch (operation) {
            case "register" -> registerUser(parts);
            case "login" -> loginUser(parts);
            case "logout" -> logoutUser(parts);
            default -> System.out.println("Invalid operation. Please use: register, login, or logout.\n");
        }
    }

    private void registerUser(String[] parts) {
        if (validateCommandFormat(parts, 3)) {
            String username = parts[1];
            String password = parts[2];
            if (users.containsKey(username)) {
                System.out.println("fail: user already exists\n");
            } else {
                users.put(username, password);
                System.out.println("success: new user added\n");
            }
        }
    }

    private void loginUser(String[] parts) {
        if (validateCommandFormat(parts, 3)) {
            String username = parts[1];
            String password = parts[2];
            if (!users.containsKey(username)) {
                System.out.println("fail: no such user\n");
            } else if (loggedInUsers.contains(username)) {
                System.out.println("fail: already logged in\n");
            } else if (!users.get(username).equals(password)) {
                System.out.println("fail: incorrect password\n");
            } else {
                loggedInUsers.add(username);
                System.out.println("success: user logged in\n");
            }
        }
    }

    private void logoutUser(String[] parts) {
        if (validateCommandFormat(parts, 2)) {
            String username = parts[1];
            if (!users.containsKey(username)) {
                System.out.println("fail: no such user\n");
            } else if (!loggedInUsers.contains(username)) {
                System.out.println("fail: already logged out\n");
            } else {
                loggedInUsers.remove(username);
                System.out.println("success: user logged out\n");
            }
        }
    }

    private boolean validateCommandFormat(String[] parts, int expectedLength) {
        if (parts.length != expectedLength) {
            System.out.println("Invalid command format. Expected format: " + getExpectedFormat(expectedLength));
            return false;
        }
        return true;
    }

    private String getExpectedFormat(int expectedLength) {
        return switch (expectedLength) {
            case 3 -> "register/login username password";
            case 2 -> "logout username";
            default -> "unknown format";
        };
    }

    private void closeScanner() {
        scanner.close();
    }
}


