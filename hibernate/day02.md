
---

## 🗓️ **Day 2 – Hibernate Configuration & Annotation Mapping**

### 🎯 **Goal:**

By the end of today, you’ll understand how Hibernate connects to your database using configuration files and how entities are mapped using annotations instead of XML.

---

## 🧠 **1. Understanding Hibernate Configuration**

Hibernate needs a set of configurations to:

* Connect to the database
* Know which entities (classes) to manage
* Set up behavior like SQL logs, schema generation, etc.

There are **two main ways** to configure Hibernate:

1. **Using XML file** (`hibernate.cfg.xml`)
2. **Using Java-based Configuration**

---

### ⚙️ **Option 1: XML Configuration**

(You already saw this in Day 1)

The XML file tells Hibernate *which database* and *which entity classes* to use.

Example:

```xml
<hibernate-configuration>
 <session-factory>
   <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
   <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/testdb</property>
   <property name="hibernate.connection.username">root</property>
   <property name="hibernate.connection.password">root</property>
   <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
   <property name="hibernate.hbm2ddl.auto">update</property>
   <property name="hibernate.show_sql">true</property>

   <!-- Mapping -->
   <mapping class="com.info.model.User"/>
 </session-factory>
</hibernate-configuration>
```

---

### ⚙️ **Option 2: Java-based Configuration**

Instead of XML, you can write config directly in Java code.

Example:

```java
Configuration config = new Configuration();
config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/testdb");
config.setProperty("hibernate.connection.username", "root");
config.setProperty("hibernate.connection.password", "root");
config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
config.setProperty("hibernate.hbm2ddl.auto", "update");
config.setProperty("hibernate.show_sql", "true");
config.addAnnotatedClass(User.class);

SessionFactory factory = config.buildSessionFactory();
```

✅ **Note:** Both XML and Java-based configs are fine — use whichever your project prefers.

---

## 🧩 **2. Hibernate Annotations Overview**

Hibernate supports annotations from the **Jakarta Persistence (JPA)** API.
These annotations help you define **how Java classes map to database tables**.

| Annotation            | Purpose                                                |
| --------------------- | ------------------------------------------------------ |
| `@Entity`             | Marks class as a persistent entity (mapped to a table) |
| `@Table(name="...")`  | Specifies table name                                   |
| `@Id`                 | Marks primary key field                                |
| `@GeneratedValue`     | Defines auto-increment strategy                        |
| `@Column(name="...")` | Maps a field to a specific column                      |
| `@Transient`          | Field won’t be saved in database                       |

---

## 🧱 **3. Example: Annotation Mapping**

Let’s say we have a table `students`.

### 🧩 Entity Class:

```java
package com.info.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(name = "course")
    private String course;

    @Column(name = "email", unique = true)
    private String email;

    public Student() {}

    public Student(String name, String course, String email) {
        this.name = name;
        this.course = course;
        this.email = email;
    }

    // Getters & Setters
}
```

---

### 🧠 Explanation:

* `@Entity` → Hibernate will create a table for this class.
* `@Table(name = "students")` → The table name will be *students*.
* `@Id` → Primary key.
* `@GeneratedValue(strategy = GenerationType.IDENTITY)` → Auto-increment column.
* `@Column` → Used to define specific column names or rules.

---

## ⚙️ **4. Update Configuration File**

Add your entity mapping to `hibernate.cfg.xml`:

```xml
<mapping class="com.info.model.Student"/>
```

---

## 🧪 **5. Create Main Class to Save a Student**

```java
package com.info.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.info.model.Student;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();

        Student s1 = new Student("Rahul", "Java", "rahul@example.com");
        session.save(s1);

        session.getTransaction().commit();
        session.close();

        System.out.println("✅ Student record saved successfully!");
    }
}
```

---

## 🧠 **6. What You Learned Today**

✅ Different types of Hibernate configurations
✅ XML vs Java-based config
✅ Annotation-based mapping (modern way)
✅ How to create an entity with `@Entity`, `@Table`, and `@Column`
✅ How to save annotated entity objects into database

---

### 🧩 **Task for You**

* Create a new class `Employee` with fields (id, name, salary, department).
* Annotate it properly and save at least 2 employees into your database.
* Try changing `hibernate.hbm2ddl.auto` property to:

  * `create` (creates table each run)
  * `update` (updates schema)
  * `validate` (checks schema)

---

