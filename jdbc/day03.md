### 🚀 Day 3 — ResultSet, Metadata & Batch Processing

---

## 🎯 Learning Goals Today

By the end of today, you’ll confidently know:

✅ How `ResultSet` works (reading data row by row)
✅ Different types of `ResultSet` (scrollable, updatable)
✅ How to fetch database & result metadata (`DatabaseMetaData`, `ResultSetMetaData`)
✅ How to perform **batch operations** (insert/update multiple rows in one go)
✅ Hands-on examples for all of these

---

## 🧩 1. What is `ResultSet`?

Whenever you execute a `SELECT` query, JDBC returns a `ResultSet` object.
It acts like a **cursor** that moves through rows one by one.

Example:

```java
ResultSet rs = ps.executeQuery();
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
}
```

---

## ⚙️ 2. How ResultSet Cursor Works

When you call `executeQuery()`:

- The cursor is **before the first row**
- `rs.next()` → moves to the next row
- Returns `false` when there are no more rows

```
Before first → [Row1] → [Row2] → [Row3] → After last
```

You can move using:

| Method            | Description             |
| ----------------- | ----------------------- |
| `next()`          | Moves to next row       |
| `previous()`      | Moves to previous row   |
| `first()`         | Moves to first row      |
| `last()`          | Moves to last row       |
| `absolute(int n)` | Moves to a specific row |

⚠️ But these work **only in specific ResultSet types** (see next section 👇)

---

## 🧠 3. Types of ResultSet

When you create a statement, you can specify its type & concurrency.

| Type                      | Description                                                   |
| ------------------------- | ------------------------------------------------------------- |
| `TYPE_FORWARD_ONLY`       | Default – can move only forward                               |
| `TYPE_SCROLL_INSENSITIVE` | Can scroll (forward/backward), but doesn’t reflect DB changes |
| `TYPE_SCROLL_SENSITIVE`   | Can scroll and reflects DB changes made by others             |

| Concurrency        | Description                                 |
| ------------------ | ------------------------------------------- |
| `CONCUR_READ_ONLY` | Read-only                                   |
| `CONCUR_UPDATABLE` | You can update rows directly from ResultSet |

---

### 🧩 Example — Scrollable & Updatable ResultSet

```java
package clapsky;

import java.sql.*;

public class ScrollableResultSet {
    public static void main(String[] args) {
        try (Connection con = GetConnection.getConnection()) {

            Statement stmt = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );

            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

            rs.last(); // move to last row
            System.out.println("Last Employee: " + rs.getString("name"));

            rs.first(); // move to first row
            System.out.println("First Employee: " + rs.getString("name"));

            rs.absolute(2); // move to 2nd row
            System.out.println("2nd Employee: " + rs.getString("name"));

            // Updating data directly in ResultSet
            rs.updateDouble("salary", 99999);
            rs.updateRow(); // commit change to DB
            System.out.println("✅ 2nd Employee salary updated via ResultSet!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 🧩 4. Metadata in JDBC

### 📘 a. DatabaseMetaData

Gives info about the **database and driver** you’re connected to.

```java
package clapsky;

import java.sql.*;

public class DatabaseInfo {
    public static void main(String[] args) {
        try (Connection con = GetConnection.getConnection()) {
            DatabaseMetaData meta = con.getMetaData();

            System.out.println("Database Name: " + meta.getDatabaseProductName());
            System.out.println("Database Version: " + meta.getDatabaseProductVersion());
            System.out.println("Driver Name: " + meta.getDriverName());
            System.out.println("User: " + meta.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

### 📗 b. ResultSetMetaData

Gives info about **the columns in your query result**.

```java
package clapsky;

import java.sql.*;

public class ResultSetMetaExample {
    public static void main(String[] args) {
        try (Connection con = GetConnection.getConnection()) {
            String query = "SELECT * FROM employee";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();

            int columnCount = meta.getColumnCount();
            System.out.println("Total Columns: " + columnCount);

            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Column " + i + ": " + meta.getColumnName(i)
                    + " (" + meta.getColumnTypeName(i) + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

🧩 **Why Useful?**

- Helps when building **dynamic reports** or working with **unknown schemas**.
- Used by ORM frameworks (like Hibernate) internally.

---

## ⚡ 5. Batch Processing in JDBC

Imagine you need to insert 100 records.
If you run `INSERT` 100 times, it’ll be very slow ⚠️

👉 Batch processing lets you **group multiple SQL statements** and send them together to the database — faster and efficient!

---

### 💻 Example — Batch Insert (using `PreparedStatement`)

```java
package clapsky;

import java.sql.*;

public class BatchInsert {
    public static void main(String[] args) {
        try (Connection con = GetConnection.getConnection()) {
            String sql = "INSERT INTO employee (name, salary, department) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            String[][] employees = {
                {"Amit", "40000", "IT"},
                {"Riya", "55000", "HR"},
                {"Rohan", "60000", "Finance"}
            };

            for (String[] emp : employees) {
                ps.setString(1, emp[0]);
                ps.setDouble(2, Double.parseDouble(emp[1]));
                ps.setString(3, emp[2]);
                ps.addBatch();
            }

            int[] results = ps.executeBatch();
            System.out.println("✅ " + results.length + " records inserted successfully (batch)!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

🧠 **Key Methods:**

| Method           | Description                          |
| ---------------- | ------------------------------------ |
| `addBatch()`     | Adds SQL command to batch            |
| `executeBatch()` | Executes all added commands together |
| `clearBatch()`   | Clears batch before adding new       |

---

## ⚡ Bonus Tip — Transaction + Batch (for reliability)

```java
con.setAutoCommit(false);

try {
    ps.addBatch();
    ps.addBatch();
    ps.executeBatch();
    con.commit(); // commit all
} catch (Exception e) {
    con.rollback(); // rollback if any fails
}
```

---

## 🧹 6. Cleanup Reminder

Always close:

- `ResultSet`
- `Statement`
- `Connection`

Use **try-with-resources** to automatically handle it.
You’re already doing this perfectly 👌

---

## 📋 7. Day 3 Summary

| Topic               | Description                                 |
| ------------------- | ------------------------------------------- |
| `ResultSet`         | Object for reading query results row by row |
| `ResultSet Types`   | Forward-only, Scrollable, Updatable         |
| `DatabaseMetaData`  | Info about DB and driver                    |
| `ResultSetMetaData` | Info about query columns                    |
| `Batch Processing`  | Execute multiple SQLs at once (fast)        |
| `Transactions`      | Commit or rollback batches safely           |

---

## 💪 Practice Tasks

1️⃣ Display the 2nd employee’s details using `absolute(2)`
2️⃣ Print column names dynamically using `ResultSetMetaData`
3️⃣ Insert 5 rows using batch processing
4️⃣ Try updating a value using `ResultSet.updateRow()`
5️⃣ Print your database and driver details using `DatabaseMetaData`

---

## 🚀 Coming Tomorrow — **Day 4: JDBC Transactions & Savepoints**

You’ll learn:

- What transactions are
- Commit & rollback handling
- Using savepoints
- Ensuring data integrity in real-world applications

---
