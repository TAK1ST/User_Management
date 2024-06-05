/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermanagement.validation;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class UserInputValidation {
        // Validates general input based on regex pattern
    public static String getInput(Scanner sc, String fieldName, String regex, String errorMessage) {

        System.out.print("Enter " + fieldName + ": ");
        String input = sc.nextLine();
        if (!input.matches(regex)) {
            System.out.println(errorMessage);
            return null;
        }
        return input;
    }

}
