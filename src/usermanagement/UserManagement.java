package usermanagement;

import java.util.Scanner;
import static usermanagement.util.FileHandler.*;
import static usermanagement.Menu.*;
import static usermanagement.validation.UserInputValidator.*;

public class UserManagement {
    
    public static final String FILE_NAME = "data/userData.txt";

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
                    printListFromFile();
                    break;
                case 7: {
                    saveToFile(); // Save before exiting
                    System.out.println("Program terminated.");
                    sc.close();
                    return;
                }
                default:
                    System.out.println("Invalid option. Please try again.");
                    saveToFile(); // Save before exiting
                    System.out.println("Program terminated.");
                    sc.close();
            }
        }
    }

}
