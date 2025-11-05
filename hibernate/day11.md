
---

## 🗓️ **Day 11 – Performance Optimization & Best Practices**

### 🎯 **Goal**

By the end of today, you’ll know how to:

* Improve performance using **batch fetching**, **lazy loading**, and **caching**
* Optimize queries and reduce database hits
* Avoid common performance pitfalls

---

## 🧠 **1. Lazy vs Eager Fetching**

Fetching defines *how related entities are loaded*.

### 💡 Lazy Fetching (default)

Loads related entities **only when accessed**.

```java
@OneToMany(mappedBy="student", fetch = FetchType.LAZY)
private List<Course> courses;
```

👉 Saves memory and avoids unnecessary joins.
Hibernate loads the `courses` **only when** `getCourses()` is called.

---

### 🚀 Eager Fetching

Loads related entities **immediately** with the parent.

```java
@OneToMany(mappedBy="student", fetch = FetchType.EAGER)
private List<Course> courses;
```

👉 Useful when you *always* need the relationship.
⚠️ But overuse leads to performance drops due to large joins.

---

## 🧩 **2. N+1 Select Problem (and How to Fix It)**

### ❌ The Problem

```java
List<Student> list = session.createQuery("from Student").list();

for(Student s : list) {
    System.out.println(s.getCourses());
}
```

Hibernate runs **1 query for Students + N queries for Courses** → bad performance.

### ✅ The Fix

Use **JOIN FETCH**:

```java
List<Student> list = session.createQuery(
    "select s from Student s join fetch s.courses", Student.class
).list();
```

Now Hibernate loads all data in **one SQL query**.

---

## ⚙️ **3. Batch Fetching**

When relationships are large, use **batch fetching** to reduce round trips.

### Example:

```java
@Entity
@BatchSize(size = 10)
public class Student {
    ...
}
```

→ Loads students in batches of 10 instead of one by one.

Or globally:

```xml
<property name="hibernate.default_batch_fetch_size" value="10"/>
```

---

## 🧠 **4. Second-Level Cache**

Hibernate caches objects across sessions to avoid hitting the database repeatedly.

### 🗄️ Cache Levels

| Level            | Description           | Scope           |
| ---------------- | --------------------- | --------------- |
| 1️⃣ First-Level  | Default session cache | Session-only    |
| 2️⃣ Second-Level | Optional shared cache | All sessions    |
| 3️⃣ Query Cache  | Stores query results  | Across sessions |

---

### ⚙️ Enable Second-Level Cache

In `hibernate.cfg.xml`:

```xml
<property name="hibernate.cache.use_second_level_cache">true</property>
<property name="hibernate.cache.region.factory_class">
    org.hibernate.cache.ehcache.EhCacheRegionFactory
</property>
```

And annotate:

```java
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student { ... }
```

Now repeated queries for the same student are served from cache.

---

## 💾 **5. Query Cache**

Caches the *result set* of a query.

```java
Query q = session.createQuery("from Student");
q.setCacheable(true);
List<Student> list = q.list();
```

→ On next execution, Hibernate serves data from the query cache.

---

## ⚡ **6. Connection Pooling**

Opening a DB connection for each request is expensive.
Use a **connection pool** such as **HikariCP**, **C3P0**, or **Proxool**.

Example (C3P0):

```xml
<property name="hibernate.c3p0.min_size">5</property>
<property name="hibernate.c3p0.max_size">20</property>
<property name="hibernate.c3p0.timeout">300</property>
<property name="hibernate.c3p0.max_statements">50</property>
```

---

## 🧩 **7. Batch Insert and Update**

Hibernate can perform batch operations instead of one-by-one updates.

```xml
<property name="hibernate.jdbc.batch_size">30</property>
```

Example:

```java
for (int i = 0; i < 100; i++) {
    session.save(new Student(...));
    if (i % 30 == 0) {
        session.flush();
        session.clear();
    }
}
```

→ 3 batches of 30 inserts + cleanup.

---

## 🧠 **8. Best Practices**

| Tip                                                      | Description                            |
| -------------------------------------------------------- | -------------------------------------- |
| ✅ Use **Lazy Loading**                                   | Default for most collections           |
| ✅ Use **JOIN FETCH** carefully                           | For required relationships only        |
| ✅ Enable **2nd-Level Cache**                             | For read-heavy entities                |
| ✅ Avoid unnecessary session flush                        | Reduces SQL traffic                    |
| ✅ Monitor queries                                        | Use Hibernate Statistics / SQL logging |
| ✅ Use **Pagination** (`setFirstResult`, `setMaxResults`) | Prevents huge data loads               |

---

## 🧩 **9. Hands-On Challenge**

1. Create an entity `Department` and `Employee` (One-To-Many).
2. Load all departments with their employees — first *without*, then *with* `JOIN FETCH`.
3. Enable second-level cache and test how many queries Hibernate runs when you load the same entity twice.
4. Enable `hibernate.show_sql` to observe the difference.

---

## 🧠 **10. What You Learned Today**

✅ Lazy vs Eager Fetching
✅ Solved N+1 Problem with `JOIN FETCH`
✅ Implemented Batch Fetching
✅ Used Second-Level and Query Caches
✅ Enabled Connection Pooling
✅ Applied Batch Operations
✅ Learned Performance Best Practices

---

