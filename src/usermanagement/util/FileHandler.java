package usermanagement.util;

import usermanagement.model.User;

import java.io.*;
import java.util.ArrayList;


// This class handles file operations for user data
public class FileHandler {

    // Saves user data to a file
    public static void saveToFile(String fileName, ArrayList<User> userData) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(userData);
            System.out.println("User data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }

    }

    // Loads user data from a file
    @SuppressWarnings("unchecked")
    public static ArrayList<User> loadUsersFromFile(String fileName) {
        ArrayList<User> userData = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList) {
                userData = (ArrayList<User>) obj;
                System.out.println("User data loaded from file.");
            } else {
                System.out.println("Unexpected data format in file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("User data file not found. Starting with empty database.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
        return userData;
    }

    // Prints a list of usernames from the loaded file
    public static void printListFromFile(ArrayList<User> userData) {
        for (User user : userData) {
            System.out.println(user.getUsername());
        }
    }
}