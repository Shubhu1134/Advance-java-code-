
---

## 🗓️ **Day 3 – Hibernate CRUD Operations**

### 🎯 **Goal:**

Learn how to perform basic **database operations** using Hibernate Sessions —
how to save, fetch, update, and delete data easily using object-oriented code.

---

## 🧠 **1. What is CRUD?**

CRUD =

* **C**reate → Insert data
* **R**ead → Fetch data
* **U**pdate → Modify existing data
* **D**elete → Remove data

In JDBC, you do this using SQL queries.
In Hibernate, you do it directly with Java objects — Hibernate automatically creates SQL behind the scenes.

---

## ⚙️ **2. Hibernate Session Recap**

`Session` in Hibernate represents a single connection to the database.
You use it to perform CRUD operations.

**Common methods:**

| Method                            | Purpose                                     |
| --------------------------------- | ------------------------------------------- |
| `save()`                          | Insert a new record                         |
| `get()`                           | Fetch by ID (returns null if not found)     |
| `load()`                          | Fetch by ID (throws exception if not found) |
| `update()`                        | Update existing object                      |
| `delete()`                        | Remove object                               |
| `beginTransaction()` / `commit()` | For transaction control                     |

---

## 🧱 **3. Example Entity: Product**

Let’s use a simple example of a `Product` class.

```java
package com.info.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private String category;

    public Product() {}
    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // Getters and Setters
}
```

✅ Don’t forget to add this mapping in your `hibernate.cfg.xml`:

```xml
<mapping class="com.info.model.Product"/>
```

---

## 🧪 **4. CREATE Operation (Insert Data)**

```java
package com.info.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.info.model.Product;

public class CreateProduct {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();

        Product p1 = new Product("Laptop", 60000, "Electronics");
        Product p2 = new Product("Keyboard", 1200, "Accessories");

        session.save(p1);
        session.save(p2);

        session.getTransaction().commit();
        session.close();

        System.out.println("✅ Products inserted successfully!");
    }
}
```

🧩 Hibernate automatically generates:

```sql
insert into products (category, name, price) values (?, ?, ?)
```

---

## 🔍 **5. READ Operation (Fetch Data)**

```java
SessionFactory factory = new Configuration().configure().buildSessionFactory();
Session session = factory.openSession();

// Fetch product by ID
Product product = session.get(Product.class, 1);  // ID = 1
if (product != null) {
    System.out.println("Product: " + product.getName() + " - " + product.getPrice());
} else {
    System.out.println("Product not found!");
}
session.close();
```

🧠 **Difference between `get()` and `load()`**

| Method   | Behavior                                      |
| -------- | --------------------------------------------- |
| `get()`  | Returns null if not found                     |
| `load()` | Throws `ObjectNotFoundException` if not found |

---

## ✏️ **6. UPDATE Operation**

```java
Session session = factory.openSession();
session.beginTransaction();

Product product = session.get(Product.class, 1);
if (product != null) {
    product.setPrice(65000); // change value
    session.update(product);
    session.getTransaction().commit();
    System.out.println("✅ Product updated successfully!");
} else {
    System.out.println("Product not found!");
}
session.close();
```

🧩 Hibernate automatically generates:

```sql
update products set price=? where id=?
```

---

## ❌ **7. DELETE Operation**

```java
Session session = factory.openSession();
session.beginTransaction();

Product product = session.get(Product.class, 2); // delete product with id 2
if (product != null) {
    session.delete(product);
    session.getTransaction().commit();
    System.out.println("🗑️ Product deleted successfully!");
} else {
    System.out.println("Product not found!");
}
session.close();
```

🧩 Hibernate generates:

```sql
delete from products where id=?
```

---

## 🧠 **8. What You Learned Today**

✅ How to perform all CRUD operations
✅ How Session and Transaction work together
✅ Difference between `save()`, `get()`, `update()`, `delete()`
✅ Hibernate automatically manages SQL

---

### 🧩 **Task for You**

* Create an entity `Employee` with fields: `id`, `name`, `salary`, `designation`.
* Perform:

  1. Insert 3 employees
  2. Fetch one by ID
  3. Update one’s salary
  4. Delete one employee
* Observe SQL logs in the console.

---

