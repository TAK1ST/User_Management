package usermanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import usermanagement.model.UserGUI;
import usermanagement.util.FileHandlerGUI;
import static usermanagement.validation.UserInputValidatorGUI.*;

public class UserManagementGUI extends JFrame {

    private JTextField usernameField, firstNameField, lastNameField, phoneField, emailField, passwordField, confirmPasswordField, searchField;
    private JTextArea displayArea;
    private ArrayList<UserGUI> users;

    public UserManagementGUI() {
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("User Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create GUI components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        // Add input fields and labels
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        JButton createButton = new JButton("Create User");
        createButton.addActionListener(new CreateUserActionListener());
        panel.add(createButton);

        searchField = new JTextField();
        panel.add(new JLabel("Search User by Username:"));
        panel.add(searchField);

        JButton searchButton = new JButton("Search User");
        searchButton.addActionListener(new SearchUserActionListener());
        panel.add(searchButton);

        JButton updateButton = new JButton("Update User");
        updateButton.addActionListener(new UpdateUserActionListener());
        panel.add(updateButton);

        JButton deleteButton = new JButton("Delete User");
        deleteButton.addActionListener(new DeleteUserActionListener());
        panel.add(deleteButton);

        JButton saveButton = new JButton("Save to File");
        saveButton.addActionListener(new SaveToFileActionListener());
        panel.add(saveButton);

        JButton loadButton = new JButton("Load from File");
        loadButton.addActionListener(new LoadFromFileActionListener());
        panel.add(loadButton);

        JButton printListButton = new JButton("Print List of Users");
        printListButton.addActionListener(new PrintListActionListener());
        panel.add(printListButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadUsers() {
        users = FileHandlerGUI.loadUsersFromFile("userData.dat");
        displayArea.append("User data loaded from file.\n");
    }

    private class CreateUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String phoneNumber = phoneField.getText();
            String email = emailField.getText();
            String password = new String(((JPasswordField) passwordField).getPassword());
            String confirmPassword = new String(((JPasswordField) confirmPasswordField).getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match!");
                return;
            }

            String encryptedPassword = encryptPassword(password);
            UserGUI newUser = new UserGUI(username, firstName, lastName, encryptedPassword, phoneNumber, email);
            users.add(newUser);
            displayArea.append("User created successfully: " + username + "\n");
        }
    }

    private class SearchUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = searchField.getText();
            boolean found = false;

            displayArea.setText("");
            for (UserGUI user : users) {
                if (user.getUsername().contains(username)) {
                    displayArea.append("Found User: " + user.getUsername() + "\n");
                    displayArea.append("First Name: " + user.getFirstName() + "\n");
                    displayArea.append("Last Name: " + user.getLastName() + "\n");
                    displayArea.append("Email: " + user.getEmail() + "\n");
                    displayArea.append("Phone Number: " + user.getPhoneNumber() + "\n");
                    displayArea.append("---------------------------------\n");
                    found = true;
                }
            }

            if (!found) {
                displayArea.append("No matching users found.\n");
            }
        }
    }

    private class UpdateUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = searchField.getText();
            for (UserGUI user : users) {
                if (user.getUsername().equals(username)) {
                    String newPassword = new String(((JPasswordField) passwordField).getPassword());
                    user.setPassword(encryptPassword(newPassword));
                    displayArea.append("Password updated successfully for user: " + username + "\n");
                    return;
                }
            }
            displayArea.append("User not found: " + username + "\n");
        }
    }

    private class DeleteUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = searchField.getText();
            users.removeIf(user -> user.getUsername().equals(username));
            displayArea.append("User deleted: " + username + "\n");
        }
    }

    private class SaveToFileActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileHandlerGUI.saveToFile("userData.dat", users);
            displayArea.append("User data saved to file.\n");
        }
    }

    private class LoadFromFileActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadUsers();
        }
    }

    private class PrintListActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayArea.setText(""); // Clear the display area
            for (UserGUI user : users) {
                displayArea.append("Username: " + user.getUsername() + "\n");
                displayArea.append("---------------------------------\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserManagementGUI gui = new UserManagementGUI();
            gui.setVisible(true);
            gui.loadUsers();
        });
    }
}
