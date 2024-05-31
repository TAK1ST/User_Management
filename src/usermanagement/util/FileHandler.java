package usermanagement.util;

import usermanagement.model.User;
import java.io.*;
import java.util.*;
import static usermanagement.validation.UserInputValidator.*;
import static usermanagement.UserManagement.*;

// This class handles file operations for user data
public class FileHandler {

    // Saves user data to a file
    public static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(USER_DATABASE);
            System.out.println("User data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }

    }

    // Loads user data from a file
    @SuppressWarnings("unchecked")
    public static void loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            USER_DATABASE.putAll((HashMap<String, User>) ois.readObject());
        } catch (FileNotFoundException e) {
            System.out.println("User data file not found. Starting with empty database.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }

    // Prints a list of usernames from the loaded file
    public static void printListFromFile() {
    for (User user : USER_DATABASE.values()) {
        String[] components = user.getUsername().split("_");
        System.out.printf("%-15s %-15s%n", components[0], components[1]);
       }
    }
}
