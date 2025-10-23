package model;

public class Student {
    private int id;
    private String name;
    private String subject;
    private int marks;

    public Student(String name, String subject, int marks) {
        this.name = name;
        this.subject = subject;
        this.marks = marks;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public String getSubject() { return subject; }
    public int getMarks() { return marks; }
}
