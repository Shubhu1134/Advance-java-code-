package ui;

import db.DBConnection;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        setTitle("Login");
        setSize(350, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        loginButton = new JButton("Login");
        loginButton.setBounds(120, 140, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });
    }

    private void authenticate() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?")) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose();
                new GradeCalculatorForm().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
