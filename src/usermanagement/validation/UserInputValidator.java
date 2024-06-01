package usermanagement.validation;

import java.util.ArrayList;
import usermanagement.model.User;

import java.util.Scanner;
import static usermanagement.Menu.*;
import static usermanagement.encryption.PasswordEncryptor.*;

// This class validates user input and performs user-related operations
public class UserInputValidator {

    private static final int MIN_USERNAME_LENGTH = 5; // Minimum length for a username
    private static final int MIN_PASSWORD_LENGTH = 6; // Minimum length for a password
    public static ArrayList<User> USER_DATABASE = new ArrayList<>(); // Database of users

    // Validates general input based on regex pattern
    public static String getInput(Scanner sc, String fieldName, String regex, String errorMessage) {
        System.out.println("Phone number must contain exactly 10 digits");
        System.out.print("Enter " + fieldName + ": ");
        String input = sc.nextLine();
        if (!input.matches(regex)) {
            System.out.println(errorMessage);
            return null;
        }
        return input;
    }

    // Validates username input
    public static String getUsername(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        if (username.length() < MIN_USERNAME_LENGTH || username.contains(" ")) {
            System.out.println("Username must be at least " + MIN_USERNAME_LENGTH + " characters long and contain no spaces. Try again.");
            return null;
        }
        for (User user : USER_DATABASE) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Try again.");
                return null;
            }
        }
        return username;
    }

    // Validates password input
    public static String getPassword(Scanner sc) {
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if (password.length() < MIN_PASSWORD_LENGTH || password.contains(" ")) {
            System.out.println("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long and contain no spaces. Try again.");
            return null;
        }
        return password;
    }

    // Creates a new user account
    public static void createUserAccount(Scanner sc) {
        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();

        
        String phoneNumber = getInput(sc, "phone number", "\\d{10}", "Phone number must contain exactly 10 digits. Try again.");
        if (phoneNumber == null) {
            return;
        }

        String email = getInput(sc, "email", "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", "Invalid email format. Try again.");
        if (email == null) {
            return;
        }

        String username = getUsername(sc);
        if (username == null) {
            return;
        }

        String password = getPassword(sc);
        if (password == null) {
            return;
        }

        System.out.print("Confirm password: ");
        String confirmPassword = sc.nextLine();
        if (!confirmPassword.equals(password)) {
            System.out.println("Passwords do not match. Try again.");
            return;
        }

        String encryptedPassword = encryptPassword(password);
        User newUser = new User(username, firstName, lastName, encryptedPassword, phoneNumber, email);
        USER_DATABASE.add(newUser);
        System.out.println("User account created successfully.");
    }

    // Checks if a user exists
    public static void checkExistUser(Scanner sc) {
        System.out.print("Enter username to check: ");
        String username = sc.nextLine();
        boolean exists = false;
        for (User user : USER_DATABASE) {
            if (user.getUsername().equals(username)) {
                exists = true;
                break;
            }
        }   
        if (exists) {
            System.out.println("Username existed.");
        }
        else System.out.println("Username does not exists.");
    }
    public static String padSpaces(String value, int length) {
        int spacesToAdd = length - value.length();
        StringBuilder spaces = new StringBuilder();

        for (int i = 0; i < spacesToAdd; i++) {
            spaces.append(" ");
        }

        return spaces.toString();
    }
    // Searches for user information
    public static void searchUserInformation(Scanner sc) {
        System.out.print("Enter username substring to search: ");
        String substring = sc.nextLine();
        boolean found = false;

        for (User user : USER_DATABASE) {
            if (user.getUsername().contains(substring)) {
                System.out.println("+----------------------------------------+");
                System.out.println("|          User Information              |");
                System.out.println("+----------------------------------------+");
                System.out.println("| Username:      " + user.getUsername() + padSpaces(user.getUsername(), 24) + "|");
                System.out.println("| First Name:    " + user.getFirstName() + padSpaces(user.getFirstName(), 24) + "|");
                System.out.println("| Last Name:     " + user.getLastName() + padSpaces(user.getLastName(), 24) + "|");
                System.out.println("| Email:         " + user.getEmail() + padSpaces(user.getEmail(), 24) + "|");
                System.out.println("| Phone Number:  " + user.getPhoneNumber() + padSpaces(user.getPhoneNumber(), 24) + "|");
                System.out.println("+----------------------------------------+");
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching users found.");
        }
    }

    // Updates user information
 public static void updateUser(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        User user = null;
        for (User u : USER_DATABASE) {
            if (u.getUsername().equals(username)) {
                user = u;
                break;
            }
        }

        if (user == null) {
            System.out.println("Username does not exist.");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (!user.getPassword().equals(encryptPassword(password))) {
            System.out.println("Incorrect password.");
            return;
        }

        updateUserOptions(sc, user);
    }

    // Provides options for updating user information
    public static void updateUserOptions(Scanner sc, User user) {
        System.out.println("1. Update Password");
        System.out.println("2. Delete User");
        int option = getUserOption(sc);
        sc.nextLine();  // Consume newline

        switch (option) {
            case 1:
                updatePassword(sc, user);
                break;
            case 2:
                deleteUser(user);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    // Updates user password
    public static void updatePassword(Scanner sc, User user) {
        System.out.print("Enter new password: ");
        String newPassword = sc.nextLine();
        if (newPassword.length() < MIN_PASSWORD_LENGTH || newPassword.contains(" ")) {
            System.out.println("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long and contain no spaces. Try again.");
            return;
        }
        user.setPassword(encryptPassword(newPassword));
        System.out.println("Password updated successfully.");
    }

    // Deletes a user
    public static void deleteUser(User user) {
        boolean removed = USER_DATABASE.remove(user);

        if (removed) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user. User not found.");
        }
    }
}
