Below is the **full, clean, simple SOLUTION** for the machine test.
I used **only the concepts you already know** — JPA, EntityManager, Embeddable, EmbeddedId.

---

# ✅ **✔ COMPLETE SOLUTION**

---

# **1️⃣ StudentCourseKey.java**

```java
package com.info.jpaapp.entity;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class StudentCourseKey {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof StudentCourseKey)) return false;

        StudentCourseKey key = (StudentCourseKey) obj;
        return this.studentId == key.studentId &&
               this.courseId == key.courseId;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
```

---

# **2️⃣ StudentCourse.java**

```java
package com.info.jpaapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "students_courses")
public class StudentCourse {

    @EmbeddedId
    private StudentCourseKey key;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    public StudentCourseKey getKey() {
        return key;
    }
    public void setKey(StudentCourseKey key) {
        this.key = key;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
```

---

# **3️⃣ User.java**

```java
package com.info.jpaapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String name;
    private int age;
    private String password;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
```

---

# **4️⃣ persistence.xml**

Create inside:

```
src/main/resources/META-INF/persistence.xml
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
             version="3.0">

    <persistence-unit name="my-persistence-unit">

        <class>com.info.jpaapp.entity.User</class>
        <class>com.info.jpaapp.entity.StudentCourse</class>
        <class>com.info.jpaapp.entity.StudentCourseKey</class>

        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/jpadb"/>
            <property name="jakarta.persistence.jdbc.user" value="root"/>
            <property name="jakarta.persistence.jdbc.password" value="root"/>

            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
        </properties>

    </persistence-unit>
</persistence>
```

---

# **5️⃣ JPAUtil.java**

```java
package com.info.jpaapp.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory factory = null;

    public static EntityManagerFactory getFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("my-persistence-unit");
        }
        return factory;
    }
}
```

---

# **6️⃣ Test Class – Insert, Fetch, Update, Delete**

Create:

```
com.info.jpaapp.test.AppTest
```

```java
package com.info.jpaapp.test;

import com.info.jpaapp.entity.StudentCourse;
import com.info.jpaapp.entity.StudentCourseKey;
import com.info.jpaapp.entity.User;
import com.info.jpaapp.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AppTest {
    public static void main(String[] args) {

        EntityManager em = JPAUtil.getFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // -----------------------------
            // INSERT USER
            // -----------------------------
            User user = new User();
            user.setName("Shubh");
            user.setEmail("shubh@gmail.com");
            user.setAge(23);
            user.setPassword("1234");
            em.persist(user);

            // -----------------------------
            // INSERT STUDENT COURSE
            // -----------------------------
            StudentCourseKey key = new StudentCourseKey();
            key.setStudentId(1);
            key.setCourseId(101);

            StudentCourse sc = new StudentCourse();
            sc.setKey(key);
            sc.setStudentName("Aman");
            sc.setCourseName("Java");

            em.persist(sc);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        // -----------------------------
        // FETCH USER
        User u = em.find(User.class, 1);
        System.out.println("User found = " + (u != null ? u.getName() : "Not Found"));

        // -----------------------------
        // FETCH STUDENT COURSE
        StudentCourseKey fetchKey = new StudentCourseKey();
        fetchKey.setStudentId(1);
        fetchKey.setCourseId(101);

        StudentCourse fetchedSC = em.find(StudentCourse.class, fetchKey);
        System.out.println("SC Found = " +
            (fetchedSC != null ? fetchedSC.getStudentName() : "Not Found"));

        // -----------------------------
        // UPDATE USER EMAIL
        em.getTransaction().begin();
        User updateUser = em.find(User.class, 1);
        updateUser.setEmail("updated@gmail.com");
        em.merge(updateUser);
        em.getTransaction().commit();

        // -----------------------------
        // DELETE STUDENT COURSE
        em.getTransaction().begin();
        StudentCourse toDelete = em.find(StudentCourse.class, fetchKey);
        em.remove(toDelete);
        em.getTransaction().commit();

        em.close();
    }
}
```

---

# ✅ **YOUR MACHINE TEST SOLUTION IS COMPLETE**

If you want:
📌 One **.docx file**
📌 One **PDF**
📌 One **ZIP project folder**

Just tell me:
**"Generate DOCX"** or **"Generate ZIP"**
