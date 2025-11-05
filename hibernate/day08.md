
---

## 🗓️ **Day 8 – Hibernate Inheritance Mapping**

### 🎯 **Goal**

By the end of today, you’ll:

* Understand how **inheritance works in Hibernate**.
* Learn about the **three inheritance mapping strategies**:

  1. **Single Table**
  2. **Joined Table**
  3. **Table Per Class**
* Implement and test each with code examples.

---

## 🧠 **1. Why Inheritance Mapping?**

In Java, we often have a base class with child classes:

```java
class Employee { … }
class FullTimeEmployee extends Employee { … }
class ContractEmployee extends Employee { … }
```

But relational databases don’t support inheritance directly — so Hibernate provides ways to map it.

---

## 🧩 **2. The Three Strategies**

| Strategy            | How it Works                                | Pros              | Cons                 |
| ------------------- | ------------------------------------------- | ----------------- | -------------------- |
| **Single Table**    | All classes in **one table**                | Fastest, simple   | Table has many nulls |
| **Joined Table**    | Parent + separate child tables joined by FK | Clean, normalized | Slower joins         |
| **Table Per Class** | Each subclass has its **own table**         | Independent       | Redundant data       |

---

## 🧱 **3. Common Base Class**

```java
package com.info.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // can change strategy
@DiscriminatorColumn(name = "emp_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double salary;

    public Employee() {}
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // getters and setters
}
```

---

## 🧩 **4. Child Classes**

### FullTimeEmployee

```java
@Entity
@DiscriminatorValue("FULLTIME")
public class FullTimeEmployee extends Employee {
    private double bonus;

    public FullTimeEmployee() {}
    public FullTimeEmployee(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    // getters and setters
}
```

### ContractEmployee

```java
@Entity
@DiscriminatorValue("CONTRACT")
public class ContractEmployee extends Employee {
    private int contractDuration; // in months

    public ContractEmployee() {}
    public ContractEmployee(String name, double salary, int contractDuration) {
        super(name, salary);
        this.contractDuration = contractDuration;
    }

    // getters and setters
}
```

---

## 🧩 **5. Add Mappings in `hibernate.cfg.xml`**

```xml
<mapping class="com.info.model.Employee"/>
<mapping class="com.info.model.FullTimeEmployee"/>
<mapping class="com.info.model.ContractEmployee"/>
```

---

## ⚙️ **6. Main Class – Testing**

```java
package com.info.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.info.model.*;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        FullTimeEmployee e1 = new FullTimeEmployee("Shubh", 60000, 10000);
        ContractEmployee e2 = new ContractEmployee("Rahul", 40000, 6);

        session.save(e1);
        session.save(e2);

        session.getTransaction().commit();
        session.close();
        factory.close();

        System.out.println("✅ Inheritance mapping data saved successfully!");
    }
}
```

---

## 🧩 **7. What Happens in Database**

With `SINGLE_TABLE`:

```
employees
--------------------------------------------
id | name  | salary | bonus | contractDuration | emp_type
1  | Shubh | 60000  | 10000 | null             | FULLTIME
2  | Rahul | 40000  | null  | 6                | CONTRACT
```

All entities stored in **one table**, with a `discriminator column` to mark the type.

---

## 🧱 **8. Other Strategies**

### (A) Joined Table

```java
@Inheritance(strategy = InheritanceType.JOINED)
```

🧩 Hibernate will create:

```
employees(id, name, salary)
fulltimeemployee(id, bonus)
contractemployee(id, contractDuration)
```

➡ Cleaner schema but uses **JOINs** while fetching.

---

### (B) Table Per Class

```java
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
```

🧩 Hibernate will create separate tables for each subclass with duplicated columns (`name`, `salary`).

➡ No joins, but not normalized.

---

## 🧠 **9. Best Practice**

| Use Case                               | Strategy          |
| -------------------------------------- | ----------------- |
| Simple hierarchy, performance critical | `SINGLE_TABLE`    |
| Data clarity, normalized structure     | `JOINED`          |
| Each subclass independent              | `TABLE_PER_CLASS` |

---

## 🧩 **10. What You Learned Today**

✅ Why inheritance mapping is needed
✅ 3 different strategies and their pros/cons
✅ Implementation of `SINGLE_TABLE` example
✅ Understanding of `@Inheritance`, `@DiscriminatorColumn`, and `@DiscriminatorValue`

---

### 🧩 **Task for You**

* Create a base entity `Vehicle` with fields `name` and `price`.
* Create subclasses: `Car` (extra field: seats) and `Bike` (extra field: type).
* Use **JOINED** strategy and print the records.

---

