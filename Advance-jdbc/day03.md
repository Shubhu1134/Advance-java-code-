
---

## 🗓️ **Day 3 – Hibernate Query Optimization, Fetch Strategies & the N+1 Problem**

### 🎯 **Goal**

By the end of today, you’ll understand:
✅ How Hibernate fetches data from the database.
✅ How to choose between **Lazy** and **Eager** loading.
✅ What the **N+1 Select Problem** is — and how to fix it.
✅ Smart ways to **optimize queries** for better performance.

---

## 🔹 **1. How Hibernate Fetches Data**

When you use Hibernate, you deal with **Java entities**, not SQL queries directly.
But internally, Hibernate must translate your entity operations into **SQL SELECTs** to fetch data.

Example:

```java
User user = session.get(User.class, 1);
```

Hibernate runs:

```sql
SELECT * FROM user WHERE id = 1;
```

Now — what if the `User` entity has a relationship, say with `Address`?
Should Hibernate also fetch the `Address` automatically or not?
That’s where **fetch strategies** come in.

---

## 🔹 **2. Fetch Strategies: Lazy vs. Eager Loading**

Hibernate defines **two main fetching types**:

### 💤 **Lazy Loading (Recommended)**

* Hibernate **does not** load related data immediately.
* It loads it **only when you actually access it** in code.
* This saves time and memory.

Example:

```java
@Entity
public class User {
   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
   private List<Order> orders;
}
```

```java
User user = session.get(User.class, 1);
// Orders are not loaded yet
List<Order> orders = user.getOrders(); // Now Hibernate fetches them
```

✅ **Best for large datasets** or when you don’t always need related data.

---

### ⚡ **Eager Loading**

* Loads related entities **immediately** along with the main entity.
* Can cause performance issues if not used carefully.

Example:

```java
@Entity
public class User {
   @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
   private List<Order> orders;
}
```

Now, fetching 1 user automatically loads all orders too.

✅ Useful for small datasets or when related data is **always** needed.

---

## 🔹 **3. The N+1 SELECT Problem**

### 💥 The Problem

Let’s say you fetch all users and their orders:

```java
List<User> users = session.createQuery("FROM User", User.class).list();

for (User user : users) {
    System.out.println(user.getOrders().size());
}
```

Hibernate runs:

```
SELECT * FROM user;          → (1 query)
SELECT * FROM order WHERE user_id = 1;  → (for each user)
SELECT * FROM order WHERE user_id = 2;
SELECT * FROM order WHERE user_id = 3;
...
```

If there are 100 users, Hibernate fires **1 + 100 queries!** 😬
That’s the **N+1 problem** — 1 query to get users, then N queries for each related entity.

---

### 🧠 Why It Happens

Because of **lazy loading** without optimized fetching strategy.

---

### ✅ **Solutions**

#### 1. **Fetch Joins (Best Fix)**

Tell Hibernate to fetch both entities in one SQL query:

```java
List<User> users = session.createQuery(
    "SELECT u FROM User u JOIN FETCH u.orders", User.class).list();
```

SQL behind the scenes:

```sql
SELECT u.*, o.* FROM user u
JOIN order o ON u.id = o.user_id;
```

✅ Result: All users and their orders fetched in one query.

---

#### 2. **Entity Graphs (JPA 2.1+)**

Define what relationships to load dynamically.

Example:

```java
EntityGraph<User> graph = em.createEntityGraph(User.class);
graph.addAttributeNodes("orders");

Map<String, Object> props = new HashMap<>();
props.put("javax.persistence.fetchgraph", graph);

User user = em.find(User.class, 1L, props);
```

✅ Fine-grained control without changing annotations.

---

#### 3. **Batch Fetching**

Another optimization for lazy loading — Hibernate groups lazy loads into batches.

```xml
<property name="hibernate.default_batch_fetch_size">10</property>
```

So if 100 users are fetched, it loads 10 users’ orders per batch (instead of 1 at a time).

---

## 🔹 **4. Query Optimization Tips**

| Tip                                  | Description                                     |
| ------------------------------------ | ----------------------------------------------- |
| Use **JOIN FETCH**                   | Fix N+1 problem cleanly                         |
| Set `default_batch_fetch_size`       | Efficient lazy loading                          |
| Avoid **EAGER** fetch on collections | Causes large memory usage                       |
| Use **Pagination**                   | Always limit large results                      |
| Enable SQL logging (`show_sql`)      | See how many queries run                        |
| Profile queries                      | Tools like Hibernate Statistics or SQL Profiler |

---

## 🔹 **5. Example – Optimized Fetching**

```java
Session session = sessionFactory.openSession();

List<User> users = session.createQuery(
    "SELECT u FROM User u JOIN FETCH u.orders", User.class)
    .setMaxResults(20)
    .list();

for (User u : users) {
    System.out.println(u.getName() + " → " + u.getOrders().size());
}

session.close();
```

✅ Loads 20 users with all their orders efficiently.
✅ Solves N+1 problem.
✅ Limits unnecessary data.

---

### 🧩 **Mini Task for Today**

1. Create `User` and `Order` entities with `@OneToMany`.
2. Test with `FetchType.LAZY` and `FetchType.EAGER`.
3. Observe how many SQL queries Hibernate fires.
4. Solve N+1 using `JOIN FETCH`.

---

### 🧠 **Day 3 Summary**

| Concept        | Description                 | Best Practice             |
| -------------- | --------------------------- | ------------------------- |
| Lazy Loading   | Load data when needed       | ✅ Default & efficient     |
| Eager Loading  | Load all data immediately   | ❌ Use only when necessary |
| N+1 Problem    | Too many small SQL queries  | Fix using JOIN FETCH      |
| Batch Fetching | Load related data in groups | Good for large datasets   |
| Entity Graph   | Control fetch dynamically   | Advanced JPA feature      |

---

