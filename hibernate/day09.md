
---

## 🗓️ **Day 9 – Hibernate Caching (First-Level, Second-Level & Query Cache)**

### 🎯 **Goal**

By the end of this day, you’ll:

* Understand why caching matters in Hibernate
* Learn about the **three types** of caches
* Implement **First-Level** and **Second-Level** caching
* Get an intro to the **Query Cache**

---

## 🧠 **1. What Is Caching?**

In simple terms:

> **Caching** means storing objects in memory temporarily so Hibernate doesn’t have to query the database repeatedly for the same data.

💡 Example:
If you fetch the same student record twice, Hibernate can return it from cache rather than hitting the database again.

---

## 🧱 **2. Types of Hibernate Caches**

| Cache Level            | Description                                    | Scope                   | Default                |
| ---------------------- | ---------------------------------------------- | ----------------------- | ---------------------- |
| **First-Level Cache**  | Stored within the Hibernate Session            | Per Session             | ✅ Enabled by default   |
| **Second-Level Cache** | Shared between sessions (SessionFactory level) | Application-wide        | ❌ Must enable manually |
| **Query Cache**        | Stores results of specific queries             | Works with Second-Level | ❌ Must enable manually |

---

## 🧩 **3. First-Level Cache (Session Cache)**

It is **automatic** — Hibernate keeps track of all objects loaded in the current session.
If you request the same object again, it fetches from memory, not the database.

### Example

```java
Session session = factory.openSession();

Student s1 = session.get(Student.class, 1); // hits DB
Student s2 = session.get(Student.class, 1); // fetched from cache (no DB call)

System.out.println(s1 == s2); // true
session.close();
```

✅ Hibernate logs will show only one SQL `SELECT` query.

---

## 🧩 **4. Second-Level Cache (SessionFactory Cache)**

Used to share cache **across sessions**.
It must be **manually enabled** in your configuration file and depends on a cache provider like:

* **Ehcache**
* **Hazelcast**
* **Infinispan**

---

### ⚙️ **Step 1 – Enable Second-Level Cache in `hibernate.cfg.xml`**

```xml
<hibernate-configuration>
 <session-factory>
   ...
   <property name="hibernate.cache.use_second_level_cache">true</property>
   <property name="hibernate.cache.region.factory_class">
       org.hibernate.cache.ehcache.EhCacheRegionFactory
   </property>
 </session-factory>
</hibernate-configuration>
```

---

### ⚙️ **Step 2 – Annotate Entity**

```java
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String city;

    public Student() {}
    public Student(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
```

---

### ⚙️ **Step 3 – Test Code**

```java
Session session1 = factory.openSession();
Student s1 = session1.get(Student.class, 1); // hits DB
session1.close();

Session session2 = factory.openSession();
Student s2 = session2.get(Student.class, 1); // comes from 2nd-level cache
session2.close();
```

✅ Result: Only one SQL query fired; second session loads from cache.

---

## 🧩 **5. Query Cache**

Used when you want Hibernate to cache **query results** (not just entities).

### Enable in `hibernate.cfg.xml`

```xml
<property name="hibernate.cache.use_query_cache">true</property>
```

### Example

```java
Query q1 = session.createQuery("from Student where city='Delhi'");
q1.setCacheable(true);
List<Student> list1 = q1.list(); // DB hit

List<Student> list2 = q1.list(); // From cache
```

💡 Requires **Second-Level Cache** to be active first.

---

## 🧠 **6. Cache Concurrency Strategies**

| Strategy               | When to Use                             |
| ---------------------- | --------------------------------------- |
| `READ_ONLY`            | Data never changes (like lookup tables) |
| `NONSTRICT_READ_WRITE` | Occasional updates OK                   |
| `READ_WRITE`           | Data updated often                      |
| `TRANSACTIONAL`        | Full ACID transactions                  |

---

## 🧩 **7. Cache Regions**

You can assign different entities to different cache regions:

```java
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "studentCache")
```

and configure them separately in the cache provider.

---

## 🧠 **8. What You Learned Today**

✅ Difference between First-, Second-, and Query-level caches
✅ How to enable and configure caching
✅ How Hibernate avoids duplicate DB calls
✅ Practical example with **Ehcache** integration

---

### 🧩 **Task for You**

1. Create entity `Employee`.
2. Enable **Second-Level Cache** using `@Cacheable`.
3. Open two separate sessions and fetch the same `Employee` twice.
   ➜ Observe only one SQL query executed.
4. Add `use_query_cache=true` and try caching a custom query.

---

