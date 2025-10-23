package com.example.studentdata;

import java.util.List;
import java.util.Scanner;

public class StudentData {
    private static Scanner sc = new Scanner(System.in);
    private static StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1 --> Add student data");
            System.out.println("2 --> Fetch student by roll no.");
            System.out.println("3 --> Remove student data");
            System.out.println("4 --> View all students");
            System.out.println("5 --> Exit");
            System.out.print("Choose option: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: addStudent(); break;
                case 2: fetchStudent(); break;
                case 3: removeStudent(); break;
                case 4: viewAllStudents(); break;
                case 5: System.exit(0); break;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Roll No: "); int roll = Integer.parseInt(sc.nextLine());
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine());
        System.out.print("Grade: "); String grade = sc.nextLine();

        Student s = new Student(roll, name, age, grade);
        if (dao.addStudent(s)) System.out.println("Student added successfully!");
        else System.out.println("Failed to add student.");
    }

    private static void fetchStudent() {
        System.out.print("Enter Roll No: "); int roll = Integer.parseInt(sc.nextLine());
        Student s = dao.fetchStudent(roll);
        if (s != null) {
            System.out.println("Roll: " + s.getRollNo());
            System.out.println("Name: " + s.getName());
            System.out.println("Age: " + s.getAge());
            System.out.println("Grade: " + s.getGrade());
        } else System.out.println("Student not found!");
    }

    private static void removeStudent() {
        System.out.print("Enter Roll No to remove: "); int roll = Integer.parseInt(sc.nextLine());
        if (dao.removeStudent(roll)) System.out.println("Student removed!");
        else System.out.println("Student not found or error!");
    }

    private static void viewAllStudents() {
        List<Student> students = dao.viewAllStudents();
        for (Student s : students) {
            System.out.println("-----------------------");
            System.out.println("Roll: " + s.getRollNo());
            System.out.println("Name: " + s.getName());
            System.out.println("Age: " + s.getAge());
            System.out.println("Grade: " + s.getGrade());
        }
        if (students.isEmpty()) System.out.println("No students found.");
    }
}
