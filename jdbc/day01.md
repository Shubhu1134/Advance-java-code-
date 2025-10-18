**📅 Day 1: JDBC Fundamentals & Setup**
We’ll focus on:
1️⃣ What JDBC is and why it exists
2️⃣ JDBC architecture & components
3️⃣ JDBC drivers (types, how they work)
4️⃣ Setting up MySQL + JDBC driver + Eclipse/VS Code
5️⃣ Your first database connection (Hello JDBC program)

---

## 🧩 1. What is JDBC?

**JDBC (Java Database Connectivity)** is a **Java API** that allows Java programs to interact with databases.

👉 It helps you:

- Connect Java applications to databases (like MySQL, PostgreSQL, Oracle, etc.)
- Send SQL queries (INSERT, UPDATE, SELECT, DELETE)
- Retrieve and process data from the result sets

Think of JDBC as a **bridge** between your Java code and the database.

---

## 💡 2. Why JDBC is Needed

Before JDBC, Java couldn’t talk to databases directly.
Without JDBC:

- Every database had its own protocol (MySQL, Oracle, SQL Server)
- You needed custom libraries for each — not portable!

With JDBC:
✅ Same API works for _all databases_ (just change driver)
✅ Java → JDBC → Driver → Database communication works smoothly
✅ Portable & maintainable code

---

## ⚙️ 3. JDBC Architecture

Here’s the **flow**:

```
Your Java App  →  JDBC API  →  JDBC Driver  →  Database
```

**Main JDBC Components:**

| Component                         | Description                                                |
| --------------------------------- | ---------------------------------------------------------- |
| **DriverManager**                 | Manages the list of database drivers                       |
| **Connection**                    | Represents the connection/session with a specific database |
| **Statement / PreparedStatement** | Used to send SQL queries to the database                   |
| **ResultSet**                     | Represents data returned from a `SELECT` query             |
| **SQLException**                  | Handles errors and exceptions                              |

---

## 🧰 4. JDBC Drivers

There are **4 types** of JDBC drivers.

| Type | Name             | Description                                                            |
| ---- | ---------------- | ---------------------------------------------------------------------- |
| 1    | JDBC-ODBC Bridge | Converts JDBC calls into ODBC calls — old, not used anymore            |
| 2    | Native API       | Converts JDBC to database-native API calls                             |
| 3    | Network Protocol | Uses middleware server between Java and DB                             |
| 4    | **Thin Driver**  | Pure Java driver — directly communicates with the database over TCP/IP |

✅ **We always use Type 4 (Thin Driver)** for MySQL.

Example:
For MySQL — driver class is:

```java
com.mysql.cj.jdbc.Driver
```

---

## 🧩 5. Setting Up JDBC (Step by Step)

### 🔹 Step 1: Install MySQL

- Download from [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/)
- Create a database, e.g.:

  ```sql
  CREATE DATABASE jdbc_demo;
  USE jdbc_demo;
  CREATE TABLE users (
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(50),
      email VARCHAR(50)
  );
  ```

---

### 🔹 Step 2: Download MySQL Connector/J (JDBC Driver)

- Download from: [https://dev.mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/)
- You’ll get a `.jar` file, e.g. `mysql-connector-j-9.0.0.jar`

Keep this `.jar` in your Java project directory.

---

### 🔹 Step 3: Set Up Project

Example folder:

```
jdbc_demo/
│
├── mysql-connector-j-9.0.0.jar
├── GetConnection.java
└── TestConnection.java
```

---

## 💻 6. Your First JDBC Code (Hello Connection)

### File 1 — `GetConnection.java`

```java
package clapsky;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            // Load driver (optional in new versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection info
            String url = "jdbc:mysql://localhost:3306/jdbc_demo";
            String user = "root";     // your MySQL username
            String pass = "yourpassword"; // your MySQL password

            // Establish connection
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Connection successful!");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found!");
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
        return con;
    }
}
```

---

### File 2 — `TestConnection.java`

```java
package clapsky;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection con = GetConnection.getConnection();
        if (con != null) {
            System.out.println("🎉 JDBC setup complete!");
        }
    }
}
```

---

### 🔹 Step 4: Compile and Run

**Compile:**

```bash
javac -cp ".;mysql-connector-j-9.0.0.jar" clapsky\*.java
```

**Run:**

```bash
java -cp ".;mysql-connector-j-9.0.0.jar" clapsky.TestConnection
```

If everything is fine, output:

```
✅ Connection successful!
🎉 JDBC setup complete!
```

---

## 🧠 Day 1 Recap

| Concept     | Description                                     |
| ----------- | ----------------------------------------------- |
| JDBC        | Java API to interact with databases             |
| Components  | DriverManager, Connection, Statement, ResultSet |
| Driver Type | Type 4 (Thin Driver) used for MySQL             |
| Setup       | MySQL + JDBC driver + Java project              |
| Practice    | Connected Java to MySQL successfully            |

---

## 🏁 Day 1 Task / Practice

✅ 1. Install MySQL and create a sample database
✅ 2. Connect Java to MySQL (code above)
✅ 3. Try changing DB name or credentials and see what happens
✅ 4. Read about `SQLException` and `DriverManager` in Java Docs

---
