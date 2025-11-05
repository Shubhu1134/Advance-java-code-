
---

## 🗓️ **Day 15 – Real-World Project + Deployment & Best Practices**

### 🎯 **Goal**

By the end of this session, you’ll be able to:

* Build a **complete Hibernate + Spring Boot project** (mini-app)
* Configure environments (dev / prod)
* Prepare for deployment (JAR / cloud)
* Apply key performance & architecture best practices

---

## 🧱 **1️⃣ Mini Project – Employee Management System**

**Tech Stack:**

* Spring Boot + Hibernate (JPA)
* MySQL Database
* REST APIs
* Postman (for testing)

---

### 🏗 **Entity: Employee.java**

```java
@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private double salary;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    public Employee() {}
    public Employee(String name, String role, double salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }
    // getters and setters
}
```

### 🏗 **Entity: Department.java**

```java
@Entity
@Table(name="departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy="department", cascade=CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();
}
```

---

## ⚙️ **2️⃣ Repositories**

```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentName(String deptName);
}
public interface DepartmentRepository extends JpaRepository<Department, Long> {}
```

---

## 🧠 **3️⃣ Service Layer**

```java
@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository repo;
    public EmployeeService(EmployeeRepository repo){ this.repo = repo; }

    public List<Employee> getAll(){ return repo.findAll(); }
    public Employee add(Employee e){ return repo.save(e); }
    public void delete(Long id){ repo.deleteById(id); }
}
```

---

## 🌐 **4️⃣ REST Controllers**

```java
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service){ this.service = service; }

    @GetMapping public List<Employee> all(){ return service.getAll(); }
    @PostMapping public Employee add(@RequestBody Employee e){ return service.add(e); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id); }
}
```

---

## 🗄 **5️⃣ application.properties**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/emsdb
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.port=8080
```

---

## 🚀 **6️⃣ Run and Test**

* Run via `mvn spring-boot:run`
* Test in Postman:

  * `POST /employee` → add employee
  * `GET /employee` → list all
  * `DELETE /employee/{id}` → remove

✅ Hibernate handles persistence automatically.

---

## 🧩 **7️⃣ Environment Configuration**

Create profile-specific property files:

```
application-dev.properties
application-prod.properties
```

Use:

```properties
spring.profiles.active=dev
```

This allows different DB URLs or credentials for local vs production.

---

## 🧠 **8️⃣ Packaging & Deployment**

### 🏗 **Build Executable JAR**

```
mvn clean package
```

Run with:

```
java -jar target/employee-management-0.0.1-SNAPSHOT.jar
```

### ☁️ **Deploy Options**

* **Dockerize** app (Dockerfile)
* Deploy to **AWS Elastic Beanstalk**, **Render**, or **Railway**
* Use **GitHub Actions** for CI/CD automation

---

## 🧾 **9️⃣ Best Practices Recap**

| Area               | Best Practice                                                  |
| ------------------ | -------------------------------------------------------------- |
| **Entity Design**  | Always add `@Table`, use `Long id`, define clear relationships |
| **Performance**    | Use **Lazy Loading**, **Batch Fetch**, and **Caching**         |
| **Transactions**   | Wrap service methods with `@Transactional`                     |
| **Error Handling** | Use `@ControllerAdvice` for global exceptions                  |
| **Validation**     | Apply `@Valid`, `@NotNull`, `@Email`, etc.                     |
| **DTO Layer**      | Avoid exposing entities directly in APIs                       |
| **Logging**        | Use `slf4j` or `logback` instead of `System.out`               |
| **Versioning**     | Maintain API version like `/api/v1/employees`                  |

---

## 🎓 **10️⃣ Final Outcome**

After **15 Days**, you now understand:

✅ Hibernate Core + Configuration
✅ Mappings & Relationships
✅ HQL & Criteria API
✅ Transactions & Caching
✅ Integration with Spring Boot
✅ REST API Design
✅ Deployment Workflow
✅ Enterprise-grade best practices

---

### 🧩 **Mini Challenge**

🎯 Deploy your Employee Management System on Render or Railway.
Then post it on LinkedIn with:

> “Completed 15-Day Advanced Hibernate + Spring Boot Journey 💻 — From ORM basics to full-stack backend deployment.”

---

