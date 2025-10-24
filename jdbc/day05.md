### 🚀 **Day 5 — Connection Pooling & DataSource**

---

## 🎯 **Goal for Today**

By the end of Day 5, you’ll understand:

✅ Why connection pooling is needed
✅ How a pool manages multiple connections efficiently
✅ How to use the `DataSource` interface
✅ How to configure a real pool (Apache DBCP or HikariCP)
✅ How to test pooled connections in Java

---

## 🧩 1️⃣ Why We Need Connection Pooling

Each time you call:

```java
Connection con = DriverManager.getConnection(...);
```

the JVM:

1. Opens a new TCP socket to MySQL
2. Authenticates user/password
3. Allocates buffers in DB memory

⚠️ This is **slow (50–300 ms per connection)** and wastes CPU if done frequently.

### 💡 Solution: Connection Pooling

- A **pool** keeps a small set of ready-made connections.
- When your app needs one, it **borrows** it.
- When done, it **returns** it to the pool (not closes).

So you reuse existing connections — **fast + lightweight** ⚡

---

## 🧱 2️⃣ Architecture Overview

```
   ┌────────────────────────────┐
   │ Java Application           │
   │ (JDBC code using pool)     │
   └────────────┬───────────────┘
                │  borrow()
                ▼
      ┌──────────────────────┐
      │ Connection Pool      │
      │  - Active Connections│
      │  - Idle Connections  │
      └────────────┬─────────┘
                   │  open only few sockets
                   ▼
            ┌────────────┐
            │ MySQL DB   │
            └────────────┘
```

✅ Fewer real DB connections
✅ Reused for multiple users
✅ Performance improvement 10×–100×

---

## ⚙️ 3️⃣ What is a DataSource?

`DataSource` is an interface (in `javax.sql`) that replaces `DriverManager`.

- It’s used to **get pooled connections**.
- You usually configure it **once** and then call `getConnection()` whenever needed.

```java
DataSource ds = new MysqlDataSource();
Connection con = ds.getConnection();
```

---

## 💻 4️⃣ Example 1 — Basic MySQL DataSource (Without Pooling)

```java
package clapsky;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;

public class SimpleDataSourceDemo {
    public static void main(String[] args) {
        try {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/jdbc_demo");
            ds.setUser("root");
            ds.setPassword("yourpassword");

            try (Connection con = ds.getConnection()) {
                System.out.println("✅ Connection from DataSource established!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

This is a _simple non-pooled_ example — still opens/close per call.
Let’s now move to **real pooling** 👇

---

## ⚡ 5️⃣ Example 2 — Using Apache DBCP Connection Pool

Add **`commons-dbcp2` + `commons-pool2`** jars to your classpath (or Maven):

```xml
<dependency>
  <groupId>org.apache.commons</groupId>
  <artifactId>commons-dbcp2</artifactId>
  <version>2.12.0</version>
</dependency>
```

Then use:

```java
package clapsky;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;

public class DBCPExample {
    public static void main(String[] args) {
        BasicDataSource ds = new BasicDataSource();

        ds.setUrl("jdbc:mysql://localhost:3306/jdbc_demo");
        ds.setUsername("root");
        ds.setPassword("yourpassword");

        ds.setMinIdle(3);
        ds.setMaxIdle(8);
        ds.setMaxTotal(10);
        ds.setMaxWaitMillis(1000);

        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM employee");
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                System.out.println("Employee count: " + rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

🧠 **How it works:**

- When the app starts, DBCP creates 3 connections (minIdle).
- When needed, it grows up to 10 (maxTotal).
- Unused ones return to idle state.
- Reuse = instant performance boost.

---

## 💎 6️⃣ Alternative: HikariCP (Fastest & Modern)

Add dependency:

```xml
<dependency>
  <groupId>com.zaxxer</groupId>
  <artifactId>HikariCP</artifactId>
  <version>6.2.0</version>
</dependency>
```

Example:

```java
package clapsky;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;

public class HikariExample {
    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_demo");
        config.setUsername("root");
        config.setPassword("yourpassword");
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(10000);

        try (HikariDataSource ds = new HikariDataSource(config);
             Connection con = ds.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM employee")) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

🧩 HikariCP advantages:
✅ Extremely fast (used by Spring Boot)
✅ Very small overhead
✅ Auto-recovery and leak detection

---

## 📊 7️⃣ Comparison Summary

| Feature                | DriverManager | DataSource    | Connection Pool       |
| ---------------------- | ------------- | ------------- | --------------------- |
| Performance            | Slow          | Moderate      | ⚡ Fast               |
| Reuse Connections      | ❌            | ❌            | ✅                    |
| Configurable pool size | ❌            | ❌            | ✅                    |
| Best for               | Small apps    | Simple setups | Enterprise/production |

---

## 🧹 8️⃣ Best Practices

✅ Use `DataSource` everywhere – never `DriverManager` in production
✅ Close connections using `try-with-resources` (return to pool)
✅ Monitor pool stats (Active/Idle connections)
✅ Tune maxTotal and minIdle based on load
✅ Test with real DB load — you’ll see 10× speed up

---

## 📋 9️⃣ Day 5 Summary

| Concept            | Description                         |
| ------------------ | ----------------------------------- |
| Connection Pooling | Reusing DB connections for speed    |
| DataSource         | Interface to get pooled connections |
| Apache DBCP        | Classic JDBC pool implementation    |
| HikariCP           | Modern high-performance pool        |
| Benefits           | Speed, stability, scalability       |

---

## 💪 Practice Tasks

1️⃣ Configure a DBCP pool in your project
2️⃣ Run a loop that fetches connections 100× and compare speed with DriverManager
3️⃣ Switch to HikariCP and compare performance
4️⃣ Print `Thread.currentThread().getName()` to see how multiple connections serve parallel threads

---

## 🚀 Coming Tomorrow — **Day 6: JDBC & Swing Integration**

We’ll build a small **Swing GUI app** that connects to MySQL through JDBC (pool).
You’ll learn:

- How to connect UI buttons to JDBC code
- Display data in tables
- Insert and update records from forms

---
