### **StudentGradeCalculator ERD**

```
+------------------+             +------------------+
|      users       |             |     students     |
+------------------+             +------------------+
| id  (PK)         |             | id   (PK)        |
| username (U)     |             | name             |
| password         |             | subject          |
+------------------+             | marks            |
                                 +------------------+
```

---

### **Explanation of each part**

#### 1️⃣ Table: `users`

* **Purpose:** Store login credentials for users (admin, teachers).
* **Columns:**

  1. `id INT AUTO_INCREMENT PRIMARY KEY`

     * Unique numeric ID for each user.
     * AUTO_INCREMENT → automatically assigns next number.
     * PK → ensures each user is uniquely identified.
  2. `username VARCHAR(50) NOT NULL UNIQUE`

     * Text field for login name.
     * UNIQUE → no duplicates allowed.
     * NOT NULL → username is mandatory.
  3. `password VARCHAR(50) NOT NULL`

     * Text for password.
     * NOT NULL → password is required.

**Reasoning:**

* This table is **separate from students** to keep authentication logic isolated.
* Allows multiple users to login in future (admin, teachers, staff).

---

#### 2️⃣ Table: `students`

* **Purpose:** Store all student information and marks.
* **Columns:**

  1. `id INT AUTO_INCREMENT PRIMARY KEY`

     * Unique student ID.
     * Makes it easy to reference students if needed.
  2. `name VARCHAR(100) NOT NULL`

     * Stores student’s full name.
     * 100 chars is enough for full names.
  3. `subject VARCHAR(50) NOT NULL`

     * Name of the subject.
     * Example: “Softskill”, “Technical”, “Aptitude”.
     * 50 chars is sufficient.
  4. `marks INT NOT NULL CHECK (marks BETWEEN 0 AND 100)`

     * Stores student marks as a whole number.
     * CHECK ensures valid range 0–100.

**Reasoning:**

* Simple table design for this project.
* Each row = one student’s mark in one subject.
* Keeps table normalized (no repeated data like login info or grades elsewhere).

---

### **Relationships**

* Currently, there’s **no foreign key relationship** because:

  * `users` are independent from `students`.
  * Each user can access the app; students are only for storing grades.

> If you want later, we can add **teacher-student relationships** or multiple subjects per student, but for now **one table per student-subject is enough**.

---

### ✅ **Summary of Design Choices**

| Choice                            | Reason                                           |
| --------------------------------- | ------------------------------------------------ |
| `INT AUTO_INCREMENT` for IDs      | Simple unique identifier, easy indexing          |
| `VARCHAR(50/100)`                 | Enough space for names/subjects                  |
| `NOT NULL`                        | Data integrity; important fields cannot be empty |
| `UNIQUE` for username             | Prevent duplicate logins                         |
| `CHECK (marks BETWEEN 0 AND 100)` | Ensures valid marks only                         |

---

