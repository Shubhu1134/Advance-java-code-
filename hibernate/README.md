
---

## 🧩 **Hibernate Mastery Course (15 Days Plan)**

**Goal:** From Hibernate beginner → confident backend developer capable of integrating Hibernate with Spring Boot.

---

### **🗓️ Day 1 – Introduction to Hibernate**

* What is ORM (Object Relational Mapping)
* Why Hibernate over JDBC
* Hibernate architecture (SessionFactory, Session, Transaction)
* Installing Hibernate (JARs / Maven dependencies)
* Setting up first project
  **🧠 Task:** Build a simple “User” entity and connect it to MySQL.

---

### **🗓️ Day 2 – Hibernate Configuration**

* Understanding `hibernate.cfg.xml`
* Hibernate properties (dialect, driver, URL, username, password)
* Using `AnnotationConfiguration`
* Difference between XML mapping and Annotation mapping
  **🧠 Task:** Create two entities using annotation-based mapping.

---

### **🗓️ Day 3 – Hibernate Entity & Basic CRUD**

* Mapping entity class to table
* @Entity, @Table, @Id, @Column annotations
* CRUD operations (save, get, update, delete)
* Hibernate methods: `save()`, `persist()`, `get()`, `load()`
  **🧠 Task:** Build CRUD for a “Product” entity.

---

### **🗓️ Day 4 – Primary Key & Identity Generation**

* @GeneratedValue strategies (AUTO, IDENTITY, SEQUENCE, TABLE)
* Composite keys using `@EmbeddedId`
  **🧠 Task:** Implement composite key in “Order” entity.

---

### **🗓️ Day 5 – Relationships (One-to-One)**

* @OneToOne mapping
* Cascade types & Fetch types
* Understanding lazy vs eager loading
  **🧠 Task:** Create “User” and “Profile” entities (One-to-One).

---

### **🗓️ Day 6 – Relationships (One-to-Many & Many-to-One)**

* @OneToMany, @ManyToOne annotations
* Bidirectional relationships
* JoinColumn and mappedBy explained
  **🧠 Task:** “Department” ↔ “Employee” example.

---

### **🗓️ Day 7 – Relationships (Many-to-Many)**

* @ManyToMany annotation
* JoinTable, JoinColumns explained
* Cascade and fetch options
  **🧠 Task:** “Student” ↔ “Course” mapping.

---

### **🗓️ Day 8 – Hibernate Query Language (HQL)**

* Writing HQL queries
* Named queries
* Query vs Criteria
* Using aggregate functions (count, avg, sum)
  **🧠 Task:** Query products with price > X.

---

### **🗓️ Day 9 – Criteria API**

* Creating dynamic queries
* Restrictions, Projections, Orders
* Pagination using Criteria
  **🧠 Task:** Implement pagination for “Product” table.

---

### **🗓️ Day 10 – Transactions & Rollbacks**

* Understanding ACID properties
* Auto-commit vs manual commit
* Transaction rollback scenarios
* Programmatic vs declarative transactions
  **🧠 Task:** Add rollback for failed order transaction.

---

### **🗓️ Day 11 – Caching in Hibernate**

* First-level cache vs Second-level cache
* Using EhCache or Hazelcast
* Query cache
  **🧠 Task:** Enable second-level cache in your app.

---

### **🗓️ Day 12 – Hibernate Inheritance Mapping**

* Single Table strategy
* Joined Table strategy
* Table per class hierarchy
  **🧠 Task:** Implement inheritance for Employee → Manager, Developer.

---

### **🗓️ Day 13 – Batch Processing & Performance**

* Batch insert/update
* Lazy initialization exception
* N+1 select problem and solutions
  **🧠 Task:** Optimize employee insertion for 1000 records.

---

### **🗓️ Day 14 – Integrating Hibernate with Spring Boot**

* Using Spring Data JPA
* Configuring DataSource & Hibernate properties in `application.properties`
* Repository pattern
* JPARepository and CrudRepository
  **🧠 Task:** Convert your Hibernate project into a Spring Boot app.

---

### **🗓️ Day 15 – Final Project & Review**

**Project:** “Employee Management System”

* Add, update, delete, and search employees
* Manage departments and roles
* Use caching and pagination
  **Review Topics:**
* Hibernate lifecycle
* Transactions & rollback
* Relationships summary
* Common interview questions

---

### 🎯 After Completion:

You’ll be able to:

* Work with Hibernate confidently in real projects
* Understand performance tuning
* Integrate Hibernate with Spring Boot or standalone apps
* Handle interviews on Hibernate with ease

---

