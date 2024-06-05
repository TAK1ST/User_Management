package usermanagement;

import java.util.*;
import static usermanagement.Menu.*;
import usermanagement.util.FileHandler;
import static usermanagement.util.Service.*;

public class UserManagement {

    public static final String FILE_NAME = "userData.dat";

    public static void loadUsersFromFile() {
        USER_DATABASE = FileHandler.loadUsersFromFile(FILE_NAME);
    }

    public static void saveToFile() {
        FileHandler.saveToFile(FILE_NAME, USER_DATABASE);
    }

    public static void main(String[] args) {

        loadUsersFromFile();
        Scanner sc = new Scanner(System.in);

        while (true) {
            displayMainMenu();
            int option = getUserOption(sc);
            sc.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    createUserAccount(sc);
                    break;
                case 2:
                    checkExistUser(sc);
                    break;
                case 3:
                    searchUserInformation(sc);
                    break;
                case 4:
                    updateUser(sc);
                    break;
                case 5:
                    saveToFile();
                    break;
                case 6:
                    FileHandler.printListFromFile(USER_DATABASE);
                    break;
                case 7:
                    saveToFile(); // Save before exiting
                    System.out.println("Program terminated.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");

            }
        }
    }

}
