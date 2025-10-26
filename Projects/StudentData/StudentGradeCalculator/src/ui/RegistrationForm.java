package ui;

import db.DBConnection;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegistrationForm() {
        setTitle("Register");
        setSize(350, 250);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(40, 50, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(140, 50, 150, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(40, 90, 100, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 90, 150, 25);
        add(passwordField);

        registerButton = new JButton("Register");
        registerButton.setBounds(120, 140, 100, 30);
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO users (username, password) VALUES (?, ?)")) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registration successful!");
            dispose();

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Username already exists!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
