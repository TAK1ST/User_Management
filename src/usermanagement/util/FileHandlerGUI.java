//package usermanagement.util;
//
//import usermanagement.model.UserGUI;
//import java.io.*;
//import java.util.ArrayList;
//
//public class FileHandlerGUI {
//
//    public static void saveToFile(String fileName, ArrayList<UserGUI> userData) {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
//            oos.writeObject(userData);
//            System.out.println("User data saved to file.");
//        } catch (IOException e) {
//            System.out.println("Error saving to file: " + e.getMessage());
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    public static ArrayList<UserGUI> loadUsersFromFile(String fileName) {
//        ArrayList<UserGUI> userData = new ArrayList<>();
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
//            Object obj = ois.readObject();
//            if (obj instanceof ArrayList) {
//                userData = (ArrayList<UserGUI>) obj;
//                System.out.println("User data loaded from file.");
//            } else {
//                System.out.println("Unexpected data format in file.");
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("User data file not found. Starting with empty database.");
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error loading from file: " + e.getMessage());
//        }
//        return userData;
//    }
//}
