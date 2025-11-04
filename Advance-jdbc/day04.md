
---

## 🗓️ **Day 4 – Hibernate Caching (First-Level, Second-Level & Query Cache) + Performance Tuning**

### 🎯 **Goal**

By the end of today, you’ll clearly understand:
✅ What caching is and why Hibernate uses it.
✅ The difference between **first-level**, **second-level**, and **query** cache.
✅ How to enable and configure them properly.
✅ Practical tuning tips for real-world projects.

---

## 🔹 **1. What Is Caching in Hibernate?**

Caching means **storing frequently used data in memory** so Hibernate doesn’t hit the database again and again.

Think of it like:

> “If I already fetched a user once, why go back to the DB again if I just need the same info?”

Caching improves:

* ⏩ Speed (fewer DB queries)
* ⚙️ Efficiency (less load on DB)
* 💾 Cost (reuses same data objects)

---

## 🔹 **2. First-Level Cache (Session Cache)**

### 🧩 What It Is:

* **Built-in** cache — always active.
* Works **per Hibernate session**.
* Hibernate automatically stores all fetched entities inside the current `Session` object.

### 🧠 How It Works:

```java
Session session = sessionFactory.openSession();
User user1 = session.get(User.class, 1); // SQL query fired
User user2 = session.get(User.class, 1); // No query fired — fetched from cache
```

✅ The **second call** doesn’t hit the database — Hibernate returns the same object from memory.

⚠️ Once you close the session, the cache is cleared.

### 💡 Key Point:

> You can’t disable first-level cache — it’s part of Hibernate’s core design.

---

## 🔹 **3. Second-Level Cache**

### 🧩 What It Is:

* Works **across sessions** (shared at the `SessionFactory` level).
* Stores entities so that if one session fetched something, another session can reuse it.
* Optional — must be **explicitly enabled**.

### ⚙️ How to Enable Second-Level Cache:

#### Step 1 – Add dependencies

Example (using **Ehcache**, a popular caching provider):

```xml
<dependency>
  <groupId>org.hibernate.orm</groupId>
  <artifactId>hibernate-ehcache</artifactId>
</dependency>
```

#### Step 2 – Configure in `hibernate.cfg.xml`:

```xml
<property name="hibernate.cache.use_second_level_cache">true</property>
<property name="hibernate.cache.region.factory_class">
  org.hibernate.cache.ehcache.EhCacheRegionFactory
</property>
```

#### Step 3 – Mark entities as cacheable:

```java
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
   @Id
   private int id;
   private String name;
}
```

Now, the first time you fetch a `User`, it’s saved in the second-level cache.
Next time any session requests the same entity → **no SQL query fired**.

---

### 💡 Cache Concurrency Strategies

| Strategy             | Description                   | Use Case                 |
| -------------------- | ----------------------------- | ------------------------ |
| READ_ONLY            | For data that never changes   | Reference or config data |
| NONSTRICT_READ_WRITE | Allows occasional stale reads | Data rarely updated      |
| READ_WRITE           | Ensures strong consistency    | Normal entities          |
| TRANSACTIONAL        | Used with JTA transactions    | Enterprise-level apps    |

---

## 🔹 **4. Query Cache**

### 🧩 What It Is:

* Stores results of **HQL or Criteria** queries.
* Helps when the **same query** (not just same entity) runs multiple times.

⚠️ It **depends on** the second-level cache — must be enabled after it.

#### Enable Query Cache:

```xml
<property name="hibernate.cache.use_query_cache">true</property>
```

#### Example:

```java
Query query = session.createQuery("FROM User WHERE city = :city");
query.setParameter("city", "Delhi");
query.setCacheable(true);

List<User> list1 = query.list(); // Query executed, results cached
List<User> list2 = query.list(); // Fetched from cache
```

✅ Result: Only first execution hits the DB.
✅ Second run fetches from memory instantly.

---

## 🔹 **5. Performance Tuning Tips**

| Tip                                    | Description                |
| -------------------------------------- | -------------------------- |
| ✅ Use caching for frequently read data | Speeds up repeated access  |
| ⚠️ Avoid caching volatile data         | Prevents stale results     |
| ⚙️ Use `@Cacheable` only where needed  | Don’t over-cache           |
| 🔍 Monitor SQL logs (`show_sql`)       | Spot repeated queries      |
| 🧠 Combine `JOIN FETCH` with caching   | Balance between cache & DB |
| 🪶 Keep cache size limited             | Avoid memory overhead      |

---

### 🧩 **Example: All Caches in Action**

```java
Session s1 = sessionFactory.openSession();
User u1 = s1.get(User.class, 1); // Hits DB → cached in both L1 + L2
s1.close();

Session s2 = sessionFactory.openSession();
User u2 = s2.get(User.class, 1); // Served from L2 cache, no DB hit
s2.close();
```

✅ **First-Level** cache active in each session.
✅ **Second-Level** cache shared between sessions.
✅ **Query cache** used when you enable caching for HQL queries.

---

### 🧩 **Mini Task for Today**

1. Add **Ehcache** or **HikariCP** + Hibernate in a small project.
2. Enable first-level (default) and second-level cache.
3. Compare performance (with vs. without cache).
4. Use `show_sql` to confirm when queries stop firing.

---

### 🧠 **Day 4 Summary**

| Cache Type       | Scope              | Configuration       | Description                |
| ---------------- | ------------------ | ------------------- | -------------------------- |
| **First-Level**  | Per Session        | Default             | Always enabled             |
| **Second-Level** | Per SessionFactory | Manual              | Shared across sessions     |
| **Query Cache**  | Query-level        | Manual              | Caches query results       |
| **Goal**         | Reduce DB hits     | Improve performance | Use wisely for stable data |

---

