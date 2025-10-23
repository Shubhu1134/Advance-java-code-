package dao;

import db.DBConnection;
import model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {
    public boolean saveStudent(Student student) {
        String query = "INSERT INTO students (name, subject, marks) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getSubject());
            ps.setInt(3, student.getMarks());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
