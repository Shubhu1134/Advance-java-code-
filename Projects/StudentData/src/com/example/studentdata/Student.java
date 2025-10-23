package com.example.studentdata;

public class Student {
    private int rollNo;
    private String name;
    private int age;
    private String grade;

    public Student(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGrade() { return grade; }
}
