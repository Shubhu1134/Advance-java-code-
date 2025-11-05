
---

## 🗓️ **Day 13 – Advanced Relationships & REST APIs in Spring Boot + Hibernate**

### 🎯 **Goal**

By the end of today, you’ll know how to:

* Create `@OneToMany`, `@ManyToOne`, and `@ManyToMany` relationships in Spring Boot JPA
* Build REST APIs to fetch related entities
* Handle JSON serialization issues (e.g., infinite recursion)

---

## 🧩 **1️⃣ Relationships Overview**

| Relationship                | Example               | Meaning                                            |
| --------------------------- | --------------------- | -------------------------------------------------- |
| `@OneToOne`                 | User ↔ Profile        | One user → one profile                             |
| `@OneToMany` / `@ManyToOne` | Department ↔ Employee | One department → many employees                    |
| `@ManyToMany`               | Student ↔ Course      | Students can join multiple courses, and vice-versa |

---

## 🧱 **2️⃣ Example 1 – @OneToMany / @ManyToOne**

Let’s model:

> One `Department` has many `Employees`.

### **Department.java**

```java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // One department → many employees
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Department() {}
    public Department(String name) { this.name = name; }

    // Getters and Setters
}
```

### **Employee.java**

```java
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String position;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {}
    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    // Getters and Setters
}
```

---

## ⚙️ **3️⃣ Repository Layer**

```java
package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentName(String name);
}
```

```java
package com.example.demo.repository;

import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}
```

---

## 🧠 **4️⃣ Service + Controller**

### **DepartmentController.java**

```java
package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepository repo;

    public DepartmentController(DepartmentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Department> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Department add(@RequestBody Department d) {
        return repo.save(d);
    }
}
```

### **EmployeeController.java**

```java
package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Employee> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Employee add(@RequestBody Employee e) {
        return repo.save(e);
    }

    @GetMapping("/department/{name}")
    public List<Employee> getByDept(@PathVariable String name) {
        return repo.findByDepartmentName(name);
    }
}
```

---

## 🧩 **5️⃣ Avoid Infinite Recursion**

When you fetch a `Department`, it also fetches its `Employee` list,
and each `Employee` fetches the `Department` again → infinite loop.

**Fix using Jackson annotations:**

```java
@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
@JsonManagedReference
private List<Employee> employees;
```

and

```java
@ManyToOne
@JsonBackReference
private Department department;
```

---

## 🧩 **6️⃣ Example 2 – @ManyToMany (Student ↔ Course)**

### **Student.java**

```java
@Entity
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    public Student() {}
}
```

### **Course.java**

```java
@Entity
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    public Course() {}
}
```

---

## 💡 **7️⃣ Example API Calls**

| Method | URL                        | Description           |
| ------ | -------------------------- | --------------------- |
| `GET`  | `/departments`             | Fetch all departments |
| `POST` | `/departments`             | Add department        |
| `GET`  | `/employees`               | Fetch all employees   |
| `POST` | `/employees`               | Add employee          |
| `GET`  | `/employees/department/IT` | Employees by dept     |

---

## 🧠 **8️⃣ Key Notes**

* Always manage relationships using `cascade` and `mappedBy`.
* Use DTOs if you want to control JSON structure.
* Use `@Transactional` for service-level operations involving multiple tables.
* Always test relationships with `fetch = FetchType.LAZY` to avoid performance issues.

---

## 🧩 **9️⃣ Practice Task**

Build an **API system** where:

* A `Library` has many `Books`.
* Each `Book` can have multiple `Authors` (`ManyToMany`).
* Create endpoints to:

  * Add Library
  * Add Book with authors
  * Get all books from a library

---

## ✅ **10️⃣ What You Learned Today**

✅ `@OneToMany`, `@ManyToOne`, `@ManyToMany` relationships
✅ Building REST APIs around relationships
✅ Preventing infinite recursion
✅ Querying related entities easily
✅ Clean architecture using JPA Repositories

---
