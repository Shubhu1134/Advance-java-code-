import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class StudentGradeApp extends JFrame {

    // Input fields
    private JTextField tfFirstName, tfLastName, tfEmail, tfM1, tfM2, tfM3;
    private JButton btnSave, btnRefresh;
    private JTable table;
    private DefaultTableModel model;

    public StudentGradeApp() {
        setTitle("🎓 Student Grade Calculator");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ------------------- FORM PANEL -------------------
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Enter Student Details"));

        formPanel.add(new JLabel("First Name:"));
        tfFirstName = new JTextField();
        formPanel.add(tfFirstName);

        formPanel.add(new JLabel("Last Name:"));
        tfLastName = new JTextField();
        formPanel.add(tfLastName);

        formPanel.add(new JLabel("Email:"));
        tfEmail = new JTextField();
        formPanel.add(tfEmail);

        formPanel.add(new JLabel("Marks 1:"));
        tfM1 = new JTextField();
        formPanel.add(tfM1);

        formPanel.add(new JLabel("Marks 2:"));
        tfM2 = new JTextField();
        formPanel.add(tfM2);

        formPanel.add(new JLabel("Marks 3:"));
        tfM3 = new JTextField();
        formPanel.add(tfM3);

        btnSave = new JButton("💾 Save Student");
        btnRefresh = new JButton("🔄 Refresh Table");
        formPanel.add(btnSave);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.NORTH);

        // ------------------- TABLE PANEL -------------------
        String[] columns = {"ID", "First Name", "Last Name", "Email", "M1", "M2", "M3", "Total", "Grade"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("All Students"));
        add(scrollPane, BorderLayout.CENTER);

        // ------------------- EVENT HANDLERS -------------------
        btnSave.addActionListener(e -> saveStudent());
        btnRefresh.addActionListener(e -> loadStudents());

        // Load data on start
        loadStudents();

        setVisible(true);
    }

    // ------------------- SAVE STUDENT -------------------
    private void saveStudent() {
        String firstName = tfFirstName.getText().trim();
        String lastName = tfLastName.getText().trim();
        String email = tfEmail.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all text fields!");
            return;
        }

        try {
            int m1 = Integer.parseInt(tfM1.getText().trim());
            int m2 = Integer.parseInt(tfM2.getText().trim());
            int m3 = Integer.parseInt(tfM3.getText().trim());
            int total = m1 + m2 + m3;
            String grade = calculateGrade(total);

            Connection conn = DBConnection.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed!");
                return;
            }

            String sql = "INSERT INTO students (first_name, last_name, email, marks1, marks2, marks3, total, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setInt(4, m1);
            ps.setInt(5, m2);
            ps.setInt(6, m3);
            ps.setInt(7, total);
            ps.setString(8, grade);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "✅ Student added successfully! Grade: " + grade);

            conn.close();
            clearForm();
            loadStudents();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Marks must be valid integers!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    // ------------------- LOAD STUDENTS -------------------
    private void loadStudents() {
        model.setRowCount(0); // clear table first
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) return;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("student_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getInt("marks1"),
                    rs.getInt("marks2"),
                    rs.getInt("marks3"),
                    rs.getInt("total"),
                    rs.getString("grade")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load students: " + e.getMessage());
        }
    }

    // ------------------- CALCULATE GRADE -------------------
    private String calculateGrade(int total) {
        int percent = total / 3;
        if (percent >= 90) return "A";
        else if (percent >= 75) return "B";
        else if (percent >= 50) return "C";
        else return "F";
    }

    // ------------------- CLEAR FORM -------------------
    private void clearForm() {
        tfFirstName.setText("");
        tfLastName.setText("");
        tfEmail.setText("");
        tfM1.setText("");
        tfM2.setText("");
        tfM3.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeApp::new);
    }
}
