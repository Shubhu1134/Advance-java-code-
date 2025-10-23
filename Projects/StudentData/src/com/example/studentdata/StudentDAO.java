package com.example.studentdata;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students (roll_no, name, age, grade) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, s.getRollNo());
            pst.setString(2, s.getName());
            pst.setInt(3, s.getAge());
            pst.setString(4, s.getGrade());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
        return false;
    }

    public Student fetchStudent(int rollNo) {
        String sql = "SELECT * FROM students WHERE roll_no = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, rollNo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getInt("roll_no"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("grade")
                );
            }
        } catch (Exception e) {
            System.out.println("Error fetching student: " + e.getMessage());
        }
        return null;
    }

    public boolean removeStudent(int rollNo) {
        String sql = "DELETE FROM students WHERE roll_no = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, rollNo);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error removing student: " + e.getMessage());
        }
        return false;
    }

    public List<Student> viewAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("roll_no"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("grade")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error viewing students: " + e.getMessage());
        }
        return list;
    }
}
