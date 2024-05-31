package usermanagement.model;

import java.io.Serializable;

// Represents a user in the system
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String username; // User's username
    private final String firstName; // User's first name
    private final String lastName; // User's last name
    private String password; // User's password (encrypted)
    private final String phoneNumber; // User's phone number
    private final String email; // User's email address

    // Constructor to initialize user object with provided details
    public User(String username, String firstName, String lastName, String password, String phoneNumber, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getter method to retrieve the username
    public String getUsername() {
        return username;
    }

    // Getter method to retrieve the first name
    public String getFirstName() {
        return firstName;
    }

    // Getter method to retrieve the last name
    public String getLastName() {
        return lastName;
    }

    // Getter method to retrieve the password
    public String getPassword() {
        return password;
    }

    // Setter method to update the password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter method to retrieve the phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Getter method to retrieve the email address
    public String getEmail() {
        return email;
    }
}

