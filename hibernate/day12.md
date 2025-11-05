
---

## 🗓️ **Day 12 – Spring Boot + Hibernate Integration**

### 🎯 **Goal**

By the end of today, you’ll be able to:

* Set up Hibernate automatically through **Spring Boot JPA**
* Configure your **application.properties** file
* Create **entities**, **repositories**, and **services**
* Run CRUD operations without manually managing sessions or transactions

---

## 🧩 **1️⃣ Why Use Spring Boot with Hibernate?**

| Feature         | Standalone Hibernate | Spring Boot Integration          |
| --------------- | -------------------- | -------------------------------- |
| Session Factory | Manual setup         | Auto-configured                  |
| Transactions    | Manual               | Auto managed by `@Transactional` |
| DAO Layer       | Custom               | Simplified via `JpaRepository`   |
| Configuration   | XML-heavy            | Lightweight properties file      |
| Testing         | Harder               | Very easy with Boot              |

👉 Spring Boot + Hibernate = Clean, fast, production-ready.

---

## ⚙️ **2️⃣ Create a New Spring Boot Project**

**Using Spring Initializr**
👉 [https://start.spring.io/](https://start.spring.io/)

Select:

* **Project:** Maven
* **Language:** Java
* **Spring Boot:** Latest stable version
* **Dependencies:**

  * Spring Web
  * Spring Data JPA
  * MySQL Driver (or H2 for testing)

Click **Generate**, unzip, and open in your IDE (IntelliJ / VSCode / NetBeans / Eclipse).

---

## 🧠 **3️⃣ Project Structure**

```
src/
 ├─ main/
 │   ├─ java/com/example/demo/
 │   │   ├─ entity/
 │   │   │   └─ Student.java
 │   │   ├─ repository/
 │   │   │   └─ StudentRepository.java
 │   │   ├─ service/
 │   │   │   └─ StudentService.java
 │   │   └─ controller/
 │   │       └─ StudentController.java
 │   └─ resources/
 │       ├─ application.properties
 │       └─ data.sql (optional)
 └─ pom.xml
```

---

## 🗄️ **4️⃣ application.properties**

```properties
# Database Connection
spring.datasource.url=jdbc:mysql://localhost:3306/hibernatedb
spring.datasource.username=root
spring.datasource.password=root

# Hibernate Properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server
server.port=8080
```

💡 Tip:
`ddl-auto` options → `create`, `update`, `validate`, `none`

---

## 🧱 **5️⃣ Entity Class**

```java
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String email;

    public Student() {}
    public Student(String name, String city, String email) {
        this.name = name;
        this.city = city;
        this.email = email;
    }

    // Getters and Setters
}
```

---

## 🧰 **6️⃣ Repository Layer**

Instead of manually managing `Session`, Spring provides `JpaRepository` which abstracts CRUD.

```java
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // You can add custom methods if needed, like:
    List<Student> findByCity(String city);
}
```

---

## ⚙️ **7️⃣ Service Layer**

You can create a service class to add business logic.

```java
package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student addStudent(Student s) {
        return repo.save(s);
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}
```

---

## 🌐 **8️⃣ Controller Layer**

```java
package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student s) {
        return service.addStudent(s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteStudent(id);
    }
}
```

---

## 🧪 **9️⃣ Run and Test**

Start the project → it runs on **[http://localhost:8080](http://localhost:8080)**

Try with Postman:

* **GET:** `http://localhost:8080/students`
* **POST:** `http://localhost:8080/students`

  ```json
  {
    "name": "Shubham",
    "city": "Delhi",
    "email": "shubham@gmail.com"
  }
  ```
* **DELETE:** `http://localhost:8080/students/1`

✅ You’ve now connected Hibernate to Spring Boot successfully!

---

## 🧠 **10️⃣ What You Learned Today**

✅ How Spring Boot simplifies Hibernate setup
✅ How to configure JPA properties
✅ How to build a layered architecture: Entity → Repository → Service → Controller
✅ How to use REST APIs with Hibernate ORM

---

### 🧩 **Mini-Task for You**

Build a new entity `Employee` with fields (id, name, department, salary)

* Create CRUD endpoints
* Add `findByDepartment(String dept)` method in the repository
* Test using Postman

---

