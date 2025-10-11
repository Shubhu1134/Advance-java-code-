import java.sql.*;
import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            System.out.println("Failed to connect to database.");
            return;
        }

        try {
            System.out.println("Enter student first name:");
            String firstName = sc.nextLine();
            System.out.println("Enter student last name:");
            String lastName = sc.nextLine();
            System.out.println("Enter student email:");
            String email = sc.nextLine();
            System.out.println("Enter marks for 3 subjects:");
            int m1 = sc.nextInt();
            int m2 = sc.nextInt();
            int m3 = sc.nextInt();

            int total = m1 + m2 + m3;
            String grade = calculateGrade(total);

            String query = "INSERT INTO students (first_name, last_name, email, marks1, marks2, marks3, total, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setInt(4, m1);
            ps.setInt(5, m2);
            ps.setInt(6, m3);
            ps.setInt(7, total);
            ps.setString(8, grade);
            ps.executeUpdate();

            System.out.println("Student added successfully!\n");

            // Display all students
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("student_id") + " | " +
                    rs.getString("first_name") + " | " +
                    rs.getString("last_name") + " | " +
                    rs.getString("email") + " | " +
                    rs.getInt("marks1") + " | " +
                    rs.getInt("marks2") + " | " +
                    rs.getInt("marks3") + " | " +
                    rs.getInt("total") + " | " +
                    rs.getString("grade")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();StudentGradeApp.java
        }
    }

    private static String calculateGrade(int total) {
        int percentage = total / 3;
        if (percentage >= 90) return "A";
        else if (percentage >= 75) return "B";
        else if (percentage >= 50) return "C";
        else return "F";
    }
}
