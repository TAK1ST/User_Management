package usermanagement.encryption;

import java.security.MessageDigest; // hash functions -> Create the hash code of the password in the form of byte array
import java.security.NoSuchAlgorithmException; /* exeption thrown when the algorithm is not found during the encryption process. 
                                                                    In this case, it is used to handle cases when the hash algorithm is not available, such as SHA-256.*/

// This class handles password encryption using the SHA-256 algorithm
public class PasswordEncryptor {

    // Method to encrypt a password using SHA-256 algorithm
    public static String encryptPassword(String password) {
        try {
            // Get an instance of the SHA-256 algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Compute the hash of the password bytes
            byte[] hash = md.digest(password.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            // Return the hexadecimal string representation of the hash
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Throw a runtime exception if the SHA-256 algorithm is not available
            throw new RuntimeException("Error encrypting password", e);
        }
    }
}
