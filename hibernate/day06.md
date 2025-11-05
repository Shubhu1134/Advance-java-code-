
---

## 🗓️ **Day 6 – One-to-Many & Many-to-One Relationships**

### 🎯 **Goal**

By the end of today, you’ll:

* Understand **one-to-many** and **many-to-one** mappings.
* Learn the difference between **unidirectional** and **bidirectional** relationships.
* Implement them with a real example (`Department ↔ Employee`).

---

## 🧠 **1. Concept Overview**

| Relationship    | Meaning                                               | Example                         |
| --------------- | ----------------------------------------------------- | ------------------------------- |
| **One-to-Many** | One record relates to *many* records in another table | One Department → many Employees |
| **Many-to-One** | Many records relate to *one* record in another table  | Many Employees → one Department |

So it’s basically the **same link viewed from opposite sides**.

---

## 🧩 **2. Unidirectional vs Bidirectional**

| Type               | Meaning                                |
| ------------------ | -------------------------------------- |
| **Unidirectional** | Only one entity knows the relationship |
| **Bidirectional**  | Both entities reference each other     |

---

## 🧱 **3. Example: Department ↔ Employee**

We’ll first create a **bidirectional** mapping.

---

### 🧩 **Step 1 – Department Entity**

```java
package com.info.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // One department → many employees
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Department() {}
    public Department(String name) {
        this.name = name;
    }

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }
}
```

---

### 🧩 **Step 2 – Employee Entity**

```java
package com.info.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double salary;

    // Many employees → one department
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {}
    public Employee(String name, double salary, Department department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    // getters and setters
}
```

---

### 🧩 **Step 3 – Add Mappings in `hibernate.cfg.xml`**

```xml
<mapping class="com.info.model.Department"/>
<mapping class="com.info.model.Employee"/>
```

---

### ⚙️ **Step 4 – Insert Example Data**

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

        Department dept = new Department("IT");

        Employee e1 = new Employee("Shubh", 50000, dept);
        Employee e2 = new Employee("Rahul", 45000, dept);
        dept.setEmployees(Arrays.asList(e1, e2));

        session.save(dept); // cascade saves employees automatically

        session.getTransaction().commit();
        session.close();

        System.out.println("✅ Department and Employees saved successfully!");
    }
}
```

🧩 Hibernate will create tables:

```
departments (id, name)
employees (id, name, salary, department_id)
```

---

## 🧠 **5. Fetching Data**

You can load a Department and access its employees:

```java
Session session = factory.openSession();
Department dept = session.get(Department.class, 1);

System.out.println("Department: " + dept.getName());
dept.getEmployees().forEach(e ->
    System.out.println("Employee: " + e.getName() + " - " + e.getSalary())
);
session.close();
```

🧩 If you use `FetchType.LAZY` in `@OneToMany`, employees load **only when accessed** — saving performance.

---

## 🧠 **6. Cascade Types in One-to-Many**

| Cascade Type | Effect                                    |
| ------------ | ----------------------------------------- |
| `ALL`        | Apply all cascades (save, delete, update) |
| `PERSIST`    | Saves child with parent                   |
| `REMOVE`     | Deletes child when parent deleted         |
| `MERGE`      | Updates child with parent                 |

---

## 🧠 **7. Key Notes**

* The **foreign key** (`department_id`) sits in the *many* side (Employee table).
* Always maintain **both sides** of bidirectional mapping:

  ```java
  e1.setDepartment(dept);
  dept.getEmployees().add(e1);
  ```
* Use **`mappedBy`** on the *one* side to avoid creating extra join tables.

---

## 🧠 **8. What You Learned Today**

✅ Difference between One-to-Many and Many-to-One
✅ How to use `@OneToMany`, `@ManyToOne`, `@JoinColumn`, and `mappedBy`
✅ Cascade & fetch types in relationships
✅ How to create and fetch relational data

---

### 🧩 **Task for You**

1. Create entities: `Library` ↔ `Book`

   * One library can have many books.
   * Use cascade `ALL`.
2. Save one library with 3 books.
3. Fetch the library and print all its books.

---
