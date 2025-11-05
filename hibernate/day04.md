
---

## 🗓️ **Day 4 – Primary Key & Identity Generation Strategies**

### 🎯 **Goal:**

Understand how Hibernate generates primary keys automatically and when to choose each strategy (`AUTO`, `IDENTITY`, `SEQUENCE`, `TABLE`, or custom).

---

## 🧠 **1. Why Primary Key Is Important**

In any database table, each record must have a unique identifier — that’s the **primary key**.
Hibernate uses it to:

* Track which objects are saved/updated/deleted
* Maintain relationships between tables
* Avoid duplicate data

If an entity has no primary key, Hibernate will **throw an exception**.

---

## 🧩 **2. Defining a Primary Key**

You define it in your entity with:

```java
@Id
```

Example:

```java
@Id
private int id;
```

To let Hibernate generate IDs automatically, add:

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

---

## ⚙️ **3. ID Generation Strategies Explained**

| Strategy     | Description                                              | When to Use                     |
| ------------ | -------------------------------------------------------- | ------------------------------- |
| **AUTO**     | Hibernate picks the best strategy based on your database | Quick setup for most cases      |
| **IDENTITY** | Uses DB auto-increment column                            | Common for MySQL / PostgreSQL   |
| **SEQUENCE** | Uses a database sequence object                          | Ideal for Oracle, PostgreSQL    |
| **TABLE**    | Uses a separate table to track IDs                       | Database-independent but slower |
| **NONE**     | You manually assign IDs                                  | When ID logic is custom         |

---

## 🧱 **4. Example: Product Entity with Different ID Strategies**

### Example 1 – `IDENTITY`

```java
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;

    // constructors, getters, setters
}
```

🧩 **MySQL** will auto-increment the ID column.

---

### Example 2 – `SEQUENCE`

*(Works better with databases like Oracle or PostgreSQL)*

```java
@Id
@SequenceGenerator(name = "prod_seq", sequenceName = "product_sequence", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_seq")
private int id;
```

Here:

* `@SequenceGenerator` defines a sequence name.
* Hibernate will automatically call `nextval('product_sequence')`.

---

### Example 3 – `TABLE`

```java
@Id
@TableGenerator(name = "prod_gen", table = "id_generator", pkColumnName = "gen_name",
    valueColumnName = "gen_value", initialValue = 100, allocationSize = 1)
@GeneratedValue(strategy = GenerationType.TABLE, generator = "prod_gen")
private int id;
```

🧠 Hibernate will create a small table `id_generator` that stores and updates the next ID manually.

---

### Example 4 – `AUTO`

```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
```

🧩 Hibernate automatically chooses the best strategy depending on the dialect.

---

## 🧪 **5. Testing the Generation**

Let’s use a quick example.

```java
SessionFactory factory = new Configuration().configure().buildSessionFactory();
Session session = factory.openSession();
session.beginTransaction();

Product product1 = new Product("Mouse", 800);
Product product2 = new Product("Monitor", 15000);

session.save(product1);
session.save(product2);

session.getTransaction().commit();
session.close();

System.out.println("✅ IDs auto-generated successfully!");
```

You’ll notice Hibernate logs like:

```
insert into products (name, price) values (?, ?)
```

And your database will automatically assign IDs (e.g., 1, 2).

---

## 🧠 **6. Composite Keys (Advanced Concept)**

If your table has **multiple columns as a primary key**, use an **EmbeddedId** or **IdClass**.

### Example: `Order` with composite key (`orderId` + `productId`)

```java
@Embeddable
public class OrderId implements Serializable {
    private int orderId;
    private int productId;
    // getters & setters
}

@Entity
@Table(name = "orders")
public class Order {
    @EmbeddedId
    private OrderId id;

    private int quantity;
    private Date orderDate;
}
```

Hibernate will treat both columns as the primary key.

---

## 🧩 **7. Summary**

✅ Every Hibernate entity must have a unique `@Id`
✅ Use `@GeneratedValue` for auto-generated IDs
✅ Choose strategy based on your database
✅ You can define custom or composite keys

---

### 🧠 **Task for You**

1. Create an entity `Employee` with fields: `id`, `name`, `designation`, `salary`.
2. Try all 4 generation types (`AUTO`, `IDENTITY`, `SEQUENCE`, `TABLE`).
3. Observe how the IDs are generated differently in MySQL logs.

---

