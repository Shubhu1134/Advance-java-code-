# ⚡️ Day 2 — JDBC Statements (Insert, Update, Delete, Select)

---

## 🧠 Objective:

By the end of today, you’ll know:
✅ Types of JDBC Statements
✅ How to run `INSERT`, `UPDATE`, `DELETE`, `SELECT` queries from Java
✅ How to handle results from the database
✅ Real examples for each

---

## 🔹 1. Types of JDBC Statements

There are **three main types** of JDBC Statements:

| Type | Class Name          | Description                                    | When to Use                    |
| ---- | ------------------- | ---------------------------------------------- | ------------------------------ |
| 1️⃣   | `Statement`         | Used for static SQL queries (no user input)    | Simple, fixed SQL              |
| 2️⃣   | `PreparedStatement` | Used for dynamic queries with parameters (`?`) | Safer and faster (Recommended) |
| 3️⃣   | `CallableStatement` | Used to call stored procedures                 | When using stored procedures   |

We’ll focus on **`Statement`** and **`PreparedStatement`** today.

---

## 🔹 2. Required Database Setup

Let’s use the **same database (`jdbc_demo`)** as Day 1.
Create a new table for practice:

```sql
USE jdbc_demo;

CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    salary DOUBLE,
    department VARCHAR(50)
);
```

---

## 🔹 3. JDBC Code Structure

Every JDBC program follows this structure 👇

```
1️⃣ Load Driver (optional in modern Java)
2️⃣ Create Connection
3️⃣ Create Statement / PreparedStatement
4️⃣ Execute Query
5️⃣ Process Result (if any)
6️⃣ Close Resources
```

---

## 💻 4. Example 1 — Insert Data (Using `Statement`)

**File:** `InsertEmployee.java`

```java
package clapsky;

import java.sql.Connection;
import java.sql.Statement;

public class InsertEmployee {
    public static void main(String[] args) {
        try (Connection con = GetConnection.getConnection();
             Statement stmt = con.createStatement()) {

            String sql = "INSERT INTO employee (name, salary, department) VALUES ('Krish', 45000, 'IT')";
            int rows = stmt.executeUpdate(sql);

            if (rows > 0) {
                System.out.println("✅ Data inserted successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

🧩 **Explanation:**

- `executeUpdate(sql)` → used for `INSERT`, `UPDATE`, `DELETE`
- It returns the number of affected rows.

---

## 💻 5. Example 2 — Insert Data (Using `PreparedStatement`) ✅ (Recommended)

**File:** `InsertEmployeePrepared.java`

```java
package clapsky;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertEmployeePrepared {
    public static void main(String[] args) {
        try (Connection con = GetConnection.getConnection()) {
            String query = "INSERT INTO employee (name, salary, department) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter employee name: ");
            String name = sc.nextLine();

            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();
            sc.nextLine(); // consume newline

            System.out.print("Enter department: ");
            String dept = sc.nextLine();

            ps.setString(1, name);
            ps.setDouble(2, salary);
            ps.setString(3, dept);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Record inserted successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

🧠 **Why `PreparedStatement` is better?**

- Prevents **SQL Injection**
- Reusable — faster for multiple executions
- Easier to handle user input

---

## 💻 6. Example 3 — Read Data (`SELECT` Query)

**File:** `SelectEmployee.java`

```java
package clapsky;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectEmployee {
    public static void main(String[] args) {
        try (Connection con = GetConnection.getConnection()) {
            String query = "SELECT * FROM employee";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("🧾 Employee Records:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                String dept = rs.getString("department");

                System.out.println(id + " | " + name + " | " + salary + " | " + dept);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

🧩 **Key Concepts:**

- `executeQuery()` → used for `SELECT` (returns `ResultSet`)
- `ResultSet` → cursor that iterates over rows of data

---

## 💻 7. Example 4 — Update & Delete Data

**Update:**

```java
String query = "UPDATE employee SET salary = ? WHERE name = ?";
PreparedStatement ps = con.prepareStatement(query);
ps.setDouble(1, 55000);
ps.setString(2, "Krish");
int rows = ps.executeUpdate();
System.out.println(rows + " row(s) updated!");
```

**Delete:**

```java
String query = "DELETE FROM employee WHERE id = ?";
PreparedStatement ps = con.prepareStatement(query);
ps.setInt(1, 3);
int rows = ps.executeUpdate();
System.out.println(rows + " row(s) deleted!");
```

---

## 🧹 8. Closing Resources Properly

Always close:

- `ResultSet`
- `Statement / PreparedStatement`
- `Connection`

Best way (since Java 7) 👉 **try-with-resources**

```java
try (Connection con = GetConnection.getConnection();
     PreparedStatement ps = con.prepareStatement("SELECT * FROM employee")) {
    // your code
}
```

Automatically closes all resources when done ✅

---

## 📋 9. Day 2 Summary

| Concept              | Description                          |
| -------------------- | ------------------------------------ |
| `Statement`          | Executes static SQL                  |
| `PreparedStatement`  | Executes dynamic SQL with parameters |
| `executeUpdate()`    | For INSERT, UPDATE, DELETE           |
| `executeQuery()`     | For SELECT                           |
| `ResultSet`          | Holds the data returned from a query |
| `try-with-resources` | Safely closes JDBC objects           |

---

## 🧠 Practice Task for You

1️⃣ Insert 3–4 employees using `PreparedStatement`
2️⃣ Display all employees using `ResultSet`
3️⃣ Update one record’s salary
4️⃣ Delete one employee by id
5️⃣ Try to print results in a tabular format in the console
