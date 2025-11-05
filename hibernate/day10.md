
---

## 🗓️ **Day 10 – HQL & Criteria API**

### 🎯 **Goal**

By the end of today, you’ll be able to:

* Understand **HQL syntax and queries**
* Perform CRUD operations using HQL
* Use **named parameters**
* Build dynamic, type-safe queries with the **Criteria API**

---

## 🧠 **1. What is HQL ?**

**Hibernate Query Language (HQL)** is an **object-oriented** query language — similar to SQL, but it works with **entities, not tables**.

💡 Example:
Instead of `SELECT * FROM student`, you write:

```sql
from Student
```

Hibernate automatically translates this into SQL.

---

## 🧱 **2. HQL – Basic Syntax**

| SQL                                            | HQL                                                 |
| ---------------------------------------------- | --------------------------------------------------- |
| `SELECT * FROM student;`                       | `from Student`                                      |
| `SELECT name FROM student WHERE city='Delhi';` | `select s.name from Student s where s.city='Delhi'` |

---

## 🧩 **3. Basic HQL Examples**

```java
Session session = factory.openSession();

// ✅ Fetch all students
Query q1 = session.createQuery("from Student", Student.class);
List<Student> list = q1.list();

// ✅ Filter using WHERE
Query q2 = session.createQuery("from Student where city = :c", Student.class);
q2.setParameter("c", "Delhi");
List<Student> delhiStudents = q2.list();

// ✅ Select specific columns
Query<Object[]> q3 = session.createQuery("select s.name, s.city from Student s");
for(Object[] obj : q3.list()) {
    System.out.println(obj[0] + " - " + obj[1]);
}

// ✅ Update
Transaction tx = session.beginTransaction();
Query q4 = session.createQuery("update Student set city = :city where id = :id");
q4.setParameter("city", "Mumbai");
q4.setParameter("id", 1);
int updated = q4.executeUpdate();
tx.commit();

// ✅ Delete
tx = session.beginTransaction();
Query q5 = session.createQuery("delete from Student where id = :id");
q5.setParameter("id", 2);
int deleted = q5.executeUpdate();
tx.commit();
```

---

## 🧠 **4. HQL – Aggregate Functions**

HQL supports all major SQL aggregates:

```java
Query<Long> q = session.createQuery("select count(*) from Student", Long.class);
Long total = q.uniqueResult();

Query<Double> q2 = session.createQuery("select avg(salary) from Employee s", Double.class);
Double avgSalary = q2.uniqueResult();
```

---

## 🧩 **5. Named Queries**

You can pre-define queries inside entity classes for reuse.

```java
@NamedQuery(
    name = "Student.byCity",
    query = "from Student where city = :city"
)
@Entity
public class Student {
    ...
}
```

Then call:

```java
Query<Student> q = session.createNamedQuery("Student.byCity", Student.class);
q.setParameter("city", "Delhi");
List<Student> students = q.list();
```

---

## 🧱 **6. Criteria API – Dynamic Queries**

If you want to build queries **programmatically** (not with strings), use the **Criteria API**.
It is **type-safe** and helps create **dynamic filters**.

---

### Example – Fetch Students with city = Delhi

```java
Session session = factory.openSession();

CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<Student> cq = cb.createQuery(Student.class);
Root<Student> root = cq.from(Student.class);

// add condition
cq.select(root).where(cb.equal(root.get("city"), "Delhi"));

Query<Student> query = session.createQuery(cq);
List<Student> results = query.getResultList();

for(Student s : results) {
    System.out.println(s.getName() + " - " + s.getCity());
}
```

---

### Example – Combine Conditions

```java
cq.select(root).where(
    cb.and(
        cb.equal(root.get("city"), "Delhi"),
        cb.gt(root.get("id"), 3)
    )
);
```

---

### Example – Sorting & Pagination

```java
cq.orderBy(cb.asc(root.get("name")));
Query<Student> q = session.createQuery(cq);
q.setFirstResult(0);
q.setMaxResults(5);
```

---

## 🧠 **7. When to Use What**

| Use Case                          | Choose           |
| --------------------------------- | ---------------- |
| Simple static queries             | **HQL**          |
| Complex filters built dynamically | **Criteria API** |
| Named reusable queries            | **@NamedQuery**  |

---

## 🧩 **8. What You Learned Today**

✅ HQL syntax and usage
✅ CRUD operations and aggregates using HQL
✅ Named queries
✅ Criteria API for type-safe dynamic queries
✅ Sorting and pagination

---

### 🧩 **Task for You**

1. Create entity `Employee(id, name, dept, salary)`
2. Write HQL queries for:

   * All employees of a specific dept
   * Update salary by 10% for one dept
   * Count total employees
3. Write a **Criteria API** query to fetch employees with salary > 50 000, ordered by name.

---

## 🌟 **🎓 Congratulations, Shubh Ji!**

You’ve now completed the **Advanced Hibernate 10-Day Course** 🎉

You know:

* ORM fundamentals
* Transactions, sessions, and relationships
* Inheritance & caching
* Advanced querying (HQL + Criteria API)

---

