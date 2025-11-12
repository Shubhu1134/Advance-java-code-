class Student {
    private int rollNumber;
    private String name; 
    private String email;

    // ✅ Constructor — fixed parameter name ("gamil" → "email")
    public Student(int rollNumber, String name, String email) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.email = email;
    }

    // ✅ Getters and Setters
    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name; 
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ✅ Display method
    public void displayInfo() {
        System.out.println("Name of the student: " + name);
        System.out.println("Roll number of the student: " + rollNumber);
        System.out.println("Email of the student: " + email);
    }
}

class Subject {
    private int subId;
    private String subName;
    private int credits;

    // ✅ Constructor
    public Subject(int subId, String subName, int credits) {
        this.subId = subId;
        this.subName = subName;
        this.credits = credits;
    }

    // ✅ Getters and Setters
    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    // ✅ Display method
    public void displaySubjectInfo() {
        System.out.println("Subject ID: " + subId);
        System.out.println("Subject Name: " + subName);
        System.out.println("Credits: " + credits);
    }
}

class Grade {
    private int gradeId;
    private Student student;  // Association (has-a relationship)
    private Subject subject;
    private int marks;
    private char grade;

    // ✅ Constructor
    public Grade(int gradeId, Student student, Subject subject, int marks) {
        this.gradeId = gradeId;
        this.student = student;
        this.subject = subject;
        this.marks = marks;
        this.grade = calculateGrade(); // Automatically calculate grade on creation
    }

    // ✅ Method to calculate grade character
    private char calculateGrade() {
        if (marks >= 90) {
            return 'A';
        } else if (marks >= 80) {
            return 'B';
        } else if (marks >= 70) {
            return 'C';
        } else if (marks >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    // ✅ Getters and Setters
    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
        this.grade = calculateGrade(); // Recalculate grade when marks change
    }

    public char getGrade() {
        return grade;
    }

    // ✅ Display details
    public void displayGradeInfo() {
        System.out.println("Grade ID: " + gradeId);
        System.out.println("Student: " + student.getName());
        System.out.println("Subject: " + subject.getSubName());
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
    }
}



class Main {
    public static void main(String[] args) {
        // Create Students
        Student s1 = new Student(1, "Amit", "amit@gmail.com");
        Student s2 = new Student(2, "Vikas", "vikas@gmail.com");

        // Create Subjects
        Subject sub1 = new Subject(11, "Maths", 4);
        Subject sub2 = new Subject(12, "English", 3);

        // Create Grades (Student + Subject + Marks)
        Grade g1 = new Grade(101, s1, sub1, 85);
        Grade g2 = new Grade(102, s2, sub2, 92);

        // Display grade info
        g1.displayGradeInfo();
        System.out.println("----------------");
        g2.displayGradeInfo();
    }
}
