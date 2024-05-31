package usermanagement;

import java.util.Scanner;

public class Menu {
    public static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Create User Account");
        System.out.println("2. Check Exist User");
        System.out.println("3. Search User Information");
        System.out.println("4. Update User");
        System.out.println("5. Save to File");
        System.out.println("6. Print List from File");
        System.out.println("7. End");
        System.out.print("Choose an option: ");
    }
    public static int getUserOption(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next();
        }
        return sc.nextInt();
    }
}
