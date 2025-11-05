
---

## 🗓️ **Day 1 – Introduction to Hibernate**

### 🎯 Goal:

Understand what Hibernate is, why we use it instead of JDBC, and how to set up your first Hibernate project.

---

### 🧠 **1. What is Hibernate?**

Hibernate is a **framework for ORM (Object Relational Mapping)**.
It helps Java objects talk to a database **without writing SQL queries** manually.

➡️ Think of it like a translator between your Java classes and database tables.

| Java (Object) | Database (Table) |
| ------------- | ---------------- |
| Class         | Table            |
| Field         | Column           |
| Object        | Row              |

Example:
If you create a `User` class in Java, Hibernate can automatically create a table named `user` in the database.

---

### ⚙️ **2. Why Hibernate over JDBC?**

| JDBC                                       | Hibernate                      |
| ------------------------------------------ | ------------------------------ |
| You write SQL manually                     | Generates SQL automatically    |
| Requires handling connections & statements | Manages everything internally  |
| Difficult to manage relationships          | Simple annotations handle them |
| Vendor-dependent (MySQL, Oracle)           | Database-independent           |

So, Hibernate makes your code **cleaner, portable, and easy to maintain**.

---

### 🧩 **3. Hibernate Architecture (Simple Explanation)**

1. **Configuration:**
   Reads settings from `hibernate.cfg.xml` (database URL, username, etc.)
2. **SessionFactory:**
   A factory that gives you sessions (it’s like a database connection pool).
3. **Session:**
   Represents a single database connection.
4. **Transaction:**
   Manages commit/rollback.
5. **Query:**
   Executes HQL or SQL.
6. **Entity:**
   Java class mapped to a database table.

---

### 🛠️ **4. Hibernate Setup**

#### 🧰 Requirements:

* JDK 8+
* MySQL (or any DB)
* IDE (IntelliJ / Eclipse / NetBeans)
* Hibernate JARs or Maven dependencies

#### 📦 Add Hibernate Dependency (Maven):

```xml
<dependencies>
    <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.4.4.Final</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.0.33</version>
    </dependency>
</dependencies>
```

---

### 🧾 **5. Create First Hibernate Project**

#### Step 1: Create `hibernate.cfg.xml`

```xml
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
"-//Hibernate/Hibernate Configuration DTD 5.3//EN"
"http://hibernate.org/dtd/hibernate-configuration-5.3.dtd">
<hibernate-configuration>
 <session-factory>
   <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
   <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/testdb</property>
   <property name="hibernate.connection.username">root</property>
   <property name="hibernate.connection.password">root</property>
   <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
   <property name="hibernate.hbm2ddl.auto">update</property>
   <property name="hibernate.show_sql">true</property>

   <mapping class="com.info.model.User"/>
 </session-factory>
</hibernate-configuration>
```

---

#### Step 2: Create `User` entity class

```java
package com.info.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    public User() {}
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getters and setters
}
```

---

#### Step 3: Create `Main` class

```java
package com.info.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.info.model.User;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();

        User user = new User("Shubh", "shubh@example.com");
        session.save(user);

        session.getTransaction().commit();
        session.close();

        System.out.println("✅ User saved successfully!");
    }
}
```

---

### 🧠 **6. What You Learned Today**

✅ ORM concept
✅ Hibernate basics
✅ Hibernate architecture
✅ Setting up Hibernate project
✅ Performing first save operation

---

### 🧩 **Task for You**

* Create a new Hibernate project.
* Use the above code to save your first record in MySQL.
* Try changing the username, email, and re-run to see automatic SQL logs.

---

