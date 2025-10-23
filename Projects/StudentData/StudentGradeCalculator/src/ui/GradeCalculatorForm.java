package ui;

import dao.StudentDAO;
import model.Student;
import javax.swing.*;
import java.awt.event.*;

public class GradeCalculatorForm extends JFrame {
    JTextField nameField, subjectField, marksField;
    JButton saveButton, gradeButton;
    JLabel gradeLabel;

    public GradeCalculatorForm() {
        setTitle("Student Grade Calculator");
        setSize(420, 360);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 40, 100, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 40, 220, 25);
        add(nameField);

        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setBounds(30, 80, 100, 25);
        add(subjectLabel);
        subjectField = new JTextField();
        subjectField.setBounds(150, 80, 220, 25);
        add(subjectField);

        JLabel marksLabel = new JLabel("Marks:");
        marksLabel.setBounds(30, 120, 100, 25);
        add(marksLabel);
        marksField = new JTextField();
        marksField.setBounds(150, 120, 220, 25);
        add(marksField);

        saveButton = new JButton("Save");
        saveButton.setBounds(70, 170, 120, 30);
        add(saveButton);

        gradeButton = new JButton("Calculate Grade");
        gradeButton.setBounds(210, 170, 160, 30);
        add(gradeButton);

        gradeLabel = new JLabel("Grade: -");
        gradeLabel.setBounds(150, 220, 200, 25);
        add(gradeLabel);

        saveButton.addActionListener(e -> saveStudent());
        gradeButton.addActionListener(e -> calculateGrade());
    }

    private void saveStudent() {
        try {
            String name = nameField.getText();
            String subject = subjectField.getText();
            int marks = Integer.parseInt(marksField.getText());

            Student s = new Student(name, subject, marks);
            StudentDAO dao = new StudentDAO();

            if (dao.saveStudent(s)) {
                JOptionPane.showMessageDialog(this, "Student saved successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error saving student!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter valid details!");
        }
    }

    private void calculateGrade() {
        try {
            int marks = Integer.parseInt(marksField.getText());
            String grade;
            if (marks >= 90) grade = "A+";
            else if (marks >= 75) grade = "A";
            else if (marks >= 60) grade = "B";
            else if (marks >= 40) grade = "C";
            else grade = "F";
            gradeLabel.setText("Grade: " + grade);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter marks first!");
        }
    }
}
