### 🚀 **Day 4 — JDBC Transactions & Savepoints**

---

## 🎯 **Goal of the Day**

By the end of today, you’ll be able to:

✅ Understand what a **transaction** is
✅ Use **commit()**, **rollback()**, and **savepoints**
✅ Execute **atomic operations** (all-or-nothing logic)
✅ Combine **batch + transactions** safely
✅ Know how databases maintain **data integrity**

---

## 🧠 1. What Is a Transaction?

A **transaction** is a group of SQL statements that must execute _as a single unit_.

👉 Either **all succeed** or **none succeed**.

Example (bank transfer):

```text
1. Deduct ₹500 from Account A
2. Add ₹500 to Account B
```

If step 1 succeeds but step 2 fails → money disappears ❌
Hence both steps must happen together — that’s what a **transaction** guarantees.

---

## ⚙️ 2. JDBC Default Behavior

By default, JDBC uses **auto-commit mode = true**

Meaning:

- Every SQL statement is committed automatically after execution.
- So each query is treated as a separate transaction.

We usually disable it when we want _manual control_.

---

## 🧩 3. Turning Off Auto-Commit

```java
Connection con = GetConnection.getConnection();
con.setAutoCommit(false);  // manual transaction mode
```

Now you must explicitly call:

```java
con.commit();   // save changes permanently
con.rollback(); // undo changes if something fails
```

---

## 💻 4. Example — Transaction with Commit & Rollback

Let’s simulate a **money transfer**:

```java
package clapsky;

import java.sql.*;

public class BankTransaction {
    public static void main(String[] args) {
        try (Connection con = GetConnection.getConnection()) {

            con.setAutoCommit(false);  // Start transaction

            Statement st = con.createStatement();

            // Step 1: Deduct ₹500 from account 1
            st.executeUpdate("UPDATE accounts SET balance = balance - 500 WHERE id = 1");

            // Step 2: Add ₹500 to account 2
            st.executeUpdate("UPDATE accounts SET balance = balance + 500 WHERE id = 2");

            // If both succeed:
            con.commit();
            System.out.println("✅ Transaction Successful!");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("⚠️  Error occurred. Rolling back changes...");
                // rollback to last commit point
                Connection con = GetConnection.getConnection();
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
```

🧠 **Explanation:**

- If any SQL fails → we rollback all previous statements.
- No partial data corruption.

---

## 🔹 5. Using **Savepoints**

A **Savepoint** is a marker inside a transaction that allows partial rollback.

👉 Rollback only _up to_ a specific point instead of full transaction.

---

### 💻 Example — Transaction with Savepoint

```java
package clapsky;

import java.sql.*;

public class SavepointExample {
    public static void main(String[] args) {
        try (Connection con = GetConnection.getConnection()) {
            con.setAutoCommit(false);

            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO employee (name, salary, department) VALUES ('Raj', 50000, 'IT')");
            System.out.println("Inserted Raj");

            Savepoint sp1 = con.setSavepoint("AfterFirstInsert");

            st.executeUpdate("INSERT INTO employee (name, salary, department) VALUES ('Simran', 60000, 'HR')");
            System.out.println("Inserted Simran");

            // simulate an error
            int x = 10 / 0; // throws ArithmeticException

            st.executeUpdate("INSERT INTO employee (name, salary, department) VALUES ('Ravi', 45000, 'Finance')");

            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try (Connection con = GetConnection.getConnection()) {
                System.out.println("⚠️  Error! Rolling back to savepoint...");
                con.rollback();  // or con.rollback(sp1);
                con.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
```

💡 **Result:**
Only “Raj” is inserted; rollback prevents “Simran” & “Ravi” from being added.

---

## 🔐 6. Key JDBC Transaction Methods

| Method                      | Description                                                |
| --------------------------- | ---------------------------------------------------------- |
| `setAutoCommit(false)`      | Disables auto-commit (start manual transaction)            |
| `commit()`                  | Commits all statements executed since last commit/rollback |
| `rollback()`                | Cancels all uncommitted changes                            |
| `setSavepoint(String name)` | Creates a checkpoint inside a transaction                  |
| `rollback(Savepoint sp)`    | Rolls back to a particular savepoint                       |
| `releaseSavepoint(sp)`      | Removes a savepoint (optional)                             |

---

## ⚡ 7. Transaction + Batch Example

```java
package clapsky;

import java.sql.*;

public class BatchWithTransaction {
    public static void main(String[] args) {
        try (Connection con = GetConnection.getConnection()) {
            con.setAutoCommit(false);

            String sql = "INSERT INTO employee (name, salary, department) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "Nisha");
            ps.setDouble(2, 40000);
            ps.setString(3, "IT");
            ps.addBatch();

            ps.setString(1, "Rohan");
            ps.setDouble(2, 50000);
            ps.setString(3, "HR");
            ps.addBatch();

            int[] res = ps.executeBatch();
            con.commit(); // commit only if all succeed

            System.out.println("✅ " + res.length + " batch rows committed!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("⚠️  Error! Rolling back batch...");
            try (Connection con = GetConnection.getConnection()) {
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
```

---

## 🧠 8. Transaction Properties (Advanced Insight)

Databases use **ACID** principles:

| Property            | Description                                  |
| ------------------- | -------------------------------------------- |
| **A – Atomicity**   | All or nothing                               |
| **C – Consistency** | DB moves from one valid state to another     |
| **I – Isolation**   | Transactions don’t interfere with each other |
| **D – Durability**  | Once committed, changes are permanent        |

---

## 📋 9. Day 4 Summary

| Concept                | Description                         |
| ---------------------- | ----------------------------------- |
| `setAutoCommit(false)` | Start manual control                |
| `commit()`             | Save all pending changes            |
| `rollback()`           | Undo changes                        |
| `Savepoint`            | Partial rollback marker             |
| `Batch + Transaction`  | Combine performance + safety        |
| `ACID`                 | Guarantees reliable DB transactions |

---

## 🧪 **Practice Tasks for You**

1️⃣ Create a table `accounts(id, name, balance)`
2️⃣ Simulate a transfer between two accounts using commit/rollback
3️⃣ Insert multiple employees with a savepoint halfway; trigger an exception and rollback to the savepoint
4️⃣ Combine batch insert + transaction
5️⃣ Print console messages showing whether the transaction succeeded or rolled back

---

## 🚀 **Coming Tomorrow — Day 5: Connection Pooling & DataSources**

You’ll learn:

- Why opening new connections repeatedly is inefficient
- What is a **connection pool**
- How to use **`DataSource`** with MySQL
- Real-world example using **Apache DBCP / HikariCP**

---
