
---

## 🗓️ **Day 5 – One-to-One Relationship in Hibernate**

### 🎯 **Goal**

By the end of this lesson, you’ll:

* Understand what a one-to-one relationship is.
* Know how to implement it using annotations.
* Learn about **cascade** and **fetch types**.
* Build a simple practical example.

---

## 🧠 **1. What Is a One-to-One Relationship?**

It means **one record in one table corresponds to exactly one record in another table**.

Example:

* One `User` has one `Profile`.
* One `Student` has one `Address`.

### Database view:

| Table   | Example                                   |
| ------- | ----------------------------------------- |
| user    | user_id, name, email                      |
| profile | profile_id, phone, city, **user_id (FK)** |

---

## 🧩 **2. Basic Concept**

In Hibernate, a one-to-one relationship is created using the annotation:

```java
@OneToOne
```

You can specify which entity “owns” the relationship using:

```java
@JoinColumn(name = "foreign_key_column")
```

---

## ⚙️ **3. Create Example: User ↔ Profile**

### Step 1 – `User` Entity

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

    // one-to-one mapping
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")  // foreign key column in users table
    private Profile profile;

    public User() {}
    public User(String name, String email, Profile profile) {
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

    // getters & setters
}
```

---

### Step 2 – `Profile` Entity

```java
package com.info.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String phone;
    private String city;

    public Profile() {}
    public Profile(String phone, String city) {
        this.phone = phone;
        this.city = city;
    }

    // getters & setters
}
```

---

### Step 3 – `hibernate.cfg.xml`

Add mappings:

```xml
<mapping class="com.info.model.User"/>
<mapping class="com.info.model.Profile"/>
```

---

### Step 4 – Insert Example

```java
package com.info.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.info.model.User;
import com.info.model.Profile;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();

        Profile profile = new Profile("9999999999", "Mumbai");
        User user = new User("Shubh", "shubh@example.com", profile);

        session.save(user); // cascade saves profile too

        session.getTransaction().commit();
        session.close();

        System.out.println("✅ User & Profile saved successfully!");
    }
}
```

---

## 🧠 **4. Cascade Types Explained**

Cascade means: if you perform an operation on one entity, Hibernate will also perform it on the related entity.

| Cascade Type | Description                               |
| ------------ | ----------------------------------------- |
| `ALL`        | Applies all cascades (save, delete, etc.) |
| `PERSIST`    | Saves child when parent saved             |
| `REMOVE`     | Deletes child when parent deleted         |
| `MERGE`      | Updates child when parent updated         |

So, using `CascadeType.ALL` saves both **User** and **Profile** together.

---

## 🧠 **5. Fetch Types Explained**

By default, `@OneToOne` uses **EAGER fetching**, meaning it loads both entities immediately.

You can change it:

```java
@OneToOne(fetch = FetchType.LAZY)
```

Now, the related entity (`Profile`) will only load when accessed.

---

## 🧩 **6. Bidirectional One-to-One**

If you want to access the `User` from `Profile` too, make it **bidirectional**:

### Update `Profile`:

```java
@OneToOne(mappedBy = "profile")
private User user;
```

This tells Hibernate that `Profile` is the *child* side of the relationship.

---

## 🧪 **7. Testing Output**

When you run your program:

```
insert into profiles (city, phone) values (?, ?)
insert into users (email, name, profile_id) values (?, ?, ?)
```

You’ll see both tables linked by `profile_id`.

---

## 🧠 **8. What You Learned Today**

✅ What is a One-to-One relationship
✅ How to map entities with `@OneToOne` and `@JoinColumn`
✅ Cascade and Fetch types
✅ How to make a relationship bidirectional

---

### 🧩 **Task for You**

* Create `Student` ↔ `Address` entities using One-to-One mapping.
* Use `CascadeType.ALL`.
* Try switching between `FetchType.LAZY` and `FetchType.EAGER` — observe SQL logs.


---
