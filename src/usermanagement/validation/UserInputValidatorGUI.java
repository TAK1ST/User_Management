package usermanagement.validation;

import java.util.Base64;

public class UserInputValidatorGUI {

    private static final int MIN_PASSWORD_LENGTH = 6; // Minimum length for a password

    public static String encryptPassword(String password) {
        // Simple encryption for demonstration (replace with actual encryption logic)
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
