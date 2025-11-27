Below is a **complete JPA Assignment + Clean Solution** exactly matching your requirement.
No advanced concepts — only what is normally taught: **JPA, EntityManager, Persistence.xml, relationships, CRUD**.

---

# ✅ **PROJECT STRUCTURE (simple JPA project)**

```
jpa-library-app/
│── src/main/java/
│   └── com.library/
│       │── MainApp.java
│       │
│       ├── entity/
│       │     ├── Category.java
│       │     └── Book.java
│       │
│       └── dao/
│             ├── CategoryDao.java
│             └── BookDao.java
│
│── src/main/resources/
│       └── META-INF/
│             └── persistence.xml
│
└── pom.xml
```

---

# 🔥 **1) ENTITY CLASSES**

## ✅ **Category.java**

```java
package com.library.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    // getters & setters

    public void addBook(Book book) {
        books.add(book);
        book.setCategory(this);
    }

    // toString()
}
```

---

## ✅ **Book.java**

```java
package com.library.entity;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String author;
    private double price;

    @ManyToOne
    private Category category;

    // getters & setters
}
```

---

# 🔥 **2) persistence.xml**

`src/main/resources/META-INF/persistence.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
    version="3.0">

    <persistence-unit name="libraryPU">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

        <class>com.library.entity.Category</class>
        <class>com.library.entity.Book</class>

        <properties>
            <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/jpadb"/>
            <property name="jakarta.persistence.jdbc.user" value="root"/>
            <property name="jakarta.persistence.jdbc.password" value="root"/>
            <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>

            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
        </properties>
    </persistence-unit>

</persistence>
```

---

# 🔥 **3) DAO LAYER**

## **CategoryDao.java**

```java
package com.library.dao;

import com.library.entity.Category;
import jakarta.persistence.*;

public class CategoryDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryPU");
    EntityManager em = emf.createEntityManager();

    public void saveCategory(Category category) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(category);
        tx.commit();
    }

    public Category findCategory(int id) {
        return em.find(Category.class, id);
    }

    public void deleteCategory(int id) {
        EntityTransaction tx = em.getTransaction();
        Category c = em.find(Category.class, id);
        if(c != null) {
            tx.begin();
            em.remove(c);   // will also delete books (cascade=ALL)
            tx.commit();
        }
    }
}
```

---

## **BookDao.java**

```java
package com.library.dao;

import com.library.entity.Book;
import jakarta.persistence.*;

public class BookDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryPU");
    EntityManager em = emf.createEntityManager();

    public void updateBook(Book b) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(b);
        tx.commit();
    }

    public void deleteBook(int id) {
        EntityTransaction tx = em.getTransaction();
        Book b = em.find(Book.class, id);
        if(b != null) {
            tx.begin();
            em.remove(b);
            tx.commit();
        }
    }
}
```

---

# 🔥 **4) MainApp.java (All operations)**

```java
package com.library;

import com.library.dao.BookDao;
import com.library.dao.CategoryDao;
import com.library.entity.Book;
import com.library.entity.Category;

public class MainApp {

    public static void main(String[] args) {

        CategoryDao categoryDao = new CategoryDao();
        BookDao bookDao = new BookDao();

        // 1️⃣ Add category with books
        Category cat = new Category();
        cat.setName("Programming");
        cat.setDescription("Books related to programming languages");

        Book b1 = new Book();
        b1.setTitle("Java Basics");
        b1.setAuthor("James");
        b1.setPrice(400);

        Book b2 = new Book();
        b2.setTitle("Hibernate In Action");
        b2.setAuthor("Gavin King");
        b2.setPrice(550);

        Book b3 = new Book();
        b3.setTitle("Spring Framework");
        b3.setAuthor("Rod Johnson");
        b3.setPrice(650);

        cat.addBook(b1);
        cat.addBook(b2);
        cat.addBook(b3);

        categoryDao.saveCategory(cat);
        System.out.println("Category with books saved!");

        // 2️⃣ Fetch category and list books
        Category fetched = categoryDao.findCategory(cat.getId());
        System.out.println("Category: " + fetched.getName());
        fetched.getBooks().forEach(book ->
                System.out.println(book.getTitle() + " - " + book.getAuthor())
        );

        // 3️⃣ Delete only one book
        bookDao.deleteBook(b1.getId());
        System.out.println("Book deleted!");

        // 4️⃣ Delete category → all books should delete
        // categoryDao.deleteCategory(cat.getId());

        // 5️⃣ Update book
        b2.setPrice(600);
        bookDao.updateBook(b2);
        System.out.println("Book updated!");
    }
}
```

---

# 🔥 OUTPUT (Expected)

```
Category with books saved!
Category: Programming
Java Basics - James
Hibernate In Action - Gavin King
Spring Framework - Rod Johnson

Book deleted!
Book updated!
```

---

# 👍 If you want, I can also generate:

✅ Full folder ZIP
✅ Step-by-step setup for Eclipse or IntelliJ
✅ SQL for DB
✅ More CRUD operations

Just tell me — **what do you want next?**
