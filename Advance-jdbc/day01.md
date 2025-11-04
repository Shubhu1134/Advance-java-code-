---

## 🗓️ **Day 1 – Understanding ResultSet, Commit, Auto-Commit & Transactions in Hibernate**

### 🎯 **Goal**

Before mastering advanced Hibernate, you must clearly understand how **database transactions** work — what **ResultSet**, **commit**, **auto-commit**, and **rollback** really mean behind the scenes.
This is the foundation for writing safe, reliable, and bug-free database operations.

---

### 🧩 **1. What is a ResultSet?**

When Hibernate (or plain JDBC) fetches data from the database, it uses something called a **ResultSet**.

Think of it as a **virtual table** containing the data returned from a query.

Example in JDBC:

```java
ResultSet rs = statement.executeQuery("SELECT * FROM users");
```

Hibernate does this internally — it executes SQL queries and gets a `ResultSet` to map results into Java objects (called **Entities**).

#### 🔹 Types of ResultSet:

There are mainly **3 types**:

1. **TYPE_FORWARD_ONLY** → You can only move forward through rows. (Default)
2. **TYPE_SCROLL_INSENSITIVE** → You can move both forward and backward, but it won’t reflect changes in DB made after the ResultSet was created.
3. **TYPE_SCROLL_SENSITIVE** → Reflects DB changes made while iterating.

Hibernate internally uses the **forward-only** ResultSet (for performance).
But understanding these helps when debugging or using custom native queries.

---

### 💾 **2. What is a Commit?**

When you perform an operation like **INSERT**, **UPDATE**, or **DELETE**, the changes don’t immediately become permanent in the database.

They’re temporarily stored — you must **commit** them to make them permanent.

Example (JDBC):

```java
connection.commit(); // makes changes permanent
```

In **Hibernate**, when you call:

```java
transaction.commit();
```

It commits all operations done in that transaction to the database.

---

### ⚙️ **3. Auto-Commit Mode**

* By default, most databases (like MySQL) run in **auto-commit mode**.
* That means: **every SQL statement is automatically committed** after it runs.

But this can be dangerous in large applications —
because if an error occurs in the middle of multiple operations, the earlier changes won’t be rolled back.

👉 **Hibernate disables auto-commit by default** and controls commits manually via `Transaction` objects.

Example:

```java
Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

User user = new User("Shubh", "shubh@example.com");
session.save(user);

tx.commit(); // explicitly committing the transaction
session.close();
```

---

### 🔁 **4. Transaction Rollback**

Rollback means **undo all the changes** made during a transaction.

If an error occurs (like a constraint violation or exception), you can call:

```java
tx.rollback();
```

This ensures your database remains consistent — no partial data or broken states.

#### 🔹 Example:

```java
Session session = sessionFactory.openSession();
Transaction tx = null;

try {
    tx = session.beginTransaction();

    User user1 = new User("A", "a@gmail.com");
    session.save(user1);

    int x = 10 / 0; // this will throw an exception

    User user2 = new User("B", "b@gmail.com");
    session.save(user2);

    tx.commit(); // won't be called because of exception

} catch (Exception e) {
    if (tx != null) tx.rollback(); // undo everything
    e.printStackTrace();
} finally {
    session.close();
}
```

✅ Result: Neither `user1` nor `user2` will be saved — because rollback undid all actions.

---

### 💡 **5. Hibernate Transaction Flow (Simplified)**

```
Open Session → Begin Transaction → Perform DB Operations → Commit or Rollback → Close Session
```

**Golden Rule:**

> “Always wrap Hibernate operations inside a transaction — even SELECT queries — for consistency.”

---

### 🧠 **Day 1 Summary**

| Concept     | Meaning                                 | Hibernate Example            |
| ----------- | --------------------------------------- | ---------------------------- |
| ResultSet   | Virtual table returned by query         | Internal mapping to entities |
| Commit      | Make changes permanent                  | `tx.commit()`                |
| Auto-Commit | Each statement auto-committed           | Hibernate disables it        |
| Rollback    | Undo all changes in current transaction | `tx.rollback()`              |

---

### 🧩 **Mini Task for Today**

* Create a Hibernate project with a simple `User` entity.
* Perform **insert**, **update**, and **rollback** using transactions manually.
* Observe what happens in the database when you **commit** vs **rollback**.

---

