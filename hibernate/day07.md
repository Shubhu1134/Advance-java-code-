
---

## 🗓️ **Day 7 – Many-to-Many Relationship**

### 🎯 **Goal**

By the end of today, you’ll be able to:

* Understand what **Many-to-Many** means in real-world data.
* Implement it in Hibernate using `@ManyToMany`.
* Configure the **join table** that connects two entities.
* Perform save + fetch operations.

---

## 🧠 **1. Concept Overview**

A **Many-to-Many** relationship means:

> Each record in one table can be related to *many* records in another table, and vice versa.

### 🧩 Examples:

* A **student** can enroll in *many courses*.
* A **course** can have *many students*.

So — it’s **two-way many**!

---

## 🧱 **2. How Hibernate Handles It**

Hibernate creates an **extra join table** to link both sides.

Example:

| student  | course    | student_course        |
| -------- | --------- | --------------------- |
| id, name | id, title | student_id, course_id |

---

## ⚙️ **3. Let’s Build: Student ↔ Course**

### 🧩 Step 1 – `Student` Entity

```java
package com.info.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // Many-to-Many
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    public Student() {}
    public Student(String name) {
        this.name = name;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}
```

---

### 🧩 Step 2 – `Course` Entity

```java
package com.info.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    // Bidirectional link
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    public Course() {}
    public Course(String title) {
        this.title = title;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
}
```

---

### 🧩 Step 3 – Add Mappings in `hibernate.cfg.xml`

```xml
<mapping class="com.info.model.Student"/>
<mapping class="com.info.model.Course"/>
```

---

### 🧩 Step 4 – Insert Example

```java
package com.info.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.info.model.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();

        // Create courses
        Course java = new Course("Advanced Java");
        Course hibernate = new Course("Hibernate Deep Dive");

        // Create students
        Student shubh = new Student("Shubh");
        Student rahul = new Student("Rahul");

        // Set relationships
        shubh.setCourses(Arrays.asList(java, hibernate));
        rahul.setCourses(Arrays.asList(java));

        java.setStudents(Arrays.asList(shubh, rahul));
        hibernate.setStudents(Arrays.asList(shubh));

        // Save parent entity
        session.save(shubh);
        session.save(rahul);

        session.getTransaction().commit();
        session.close();

        System.out.println("✅ Many-to-Many data saved successfully!");
    }
}
```

---

## 🧠 **5. How the Database Looks**

Hibernate will automatically create:

```text
students (id, name)
courses (id, title)
student_course (student_id, course_id)
```

and insert linking rows in the `student_course` table.

---

## 🧠 **6. Fetching Data**

You can fetch any student and print all courses:

```java
Session session = factory.openSession();
Student s = session.get(Student.class, 1);

System.out.println("Student: " + s.getName());
s.getCourses().forEach(c ->
    System.out.println("Course: " + c.getTitle())
);
session.close();
```

Or fetch a course and list all enrolled students.

---

## 🧠 **7. Key Notes**

* Always define **one side** with `@JoinTable` (owning side).
* Use `mappedBy` on the *other* side.
* Use `CascadeType.ALL` if you want related entities saved automatically.
* Use `FetchType.LAZY` for performance in large datasets.

---

## 🧩 **8. What You Learned Today**

✅ Concept of Many-to-Many relationships
✅ How to implement bidirectional mapping
✅ How Hibernate uses a **join table** automatically
✅ How to save and fetch related data

---

### 🧠 **Task for You**

Create your own example:

* Entities: `Teacher` ↔ `Subject`
* Each teacher can teach many subjects, and each subject can have many teachers.
* Use `@ManyToMany`, `@JoinTable`, and `mappedBy`.
* Save 2 teachers with overlapping subjects and print results.

---

