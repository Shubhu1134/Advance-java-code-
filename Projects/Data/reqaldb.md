### **Step 1 — Create the Database**

```sql
CREATE DATABASE studentdb;
USE studentdb;
```

**Reasoning for presentation:**

* `CREATE DATABASE studentdb` → We are creating a dedicated database for the **StudentGradeCalculator project**.
* Name `studentdb` is descriptive, short, and matches the project name.
* `USE studentdb` → Tells MySQL to apply all future commands (like creating tables) in this database.
* Keeping a separate database avoids mixing student project data with other databases on your system.

---

# **Step 2 — Create `users` table (for login)**

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);
```

**Explanation for presentation:**

| Column     | Type & Constraint                | Reason                                                                                                                                                |
| ---------- | -------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| `id`       | `INT AUTO_INCREMENT PRIMARY KEY` | Unique identifier for each user. AUTO_INCREMENT makes IDs automatically increase, PRIMARY KEY ensures no duplicates.                                  |
| `username` | `VARCHAR(50) NOT NULL UNIQUE`    | Stores login name. 50 characters is enough for names or email. UNIQUE prevents duplicate usernames. NOT NULL ensures every user must have a username. |
| `password` | `VARCHAR(50) NOT NULL`           | Stores password as text (development purpose). NOT NULL ensures password is mandatory.                                                                |

**Why separate table:** Keeps authentication logic isolated from students’ data, making the design clean and professional.

---

# **Step 3 — Create `students` table (to store marks)**

```sql
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    subject VARCHAR(50) NOT NULL,
    marks INT NOT NULL CHECK (marks BETWEEN 0 AND 100)
);
```

**Explanation for presentation:**

| Column    | Type & Constraint                              | Reason                                                                                                  |
| --------- | ---------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| `id`      | `INT AUTO_INCREMENT PRIMARY KEY`               | Unique identifier for each student record.                                                              |
| `name`    | `VARCHAR(100) NOT NULL`                        | Student full name. 100 characters is enough for any name. NOT NULL ensures we don’t save empty records. |
| `subject` | `VARCHAR(50) NOT NULL`                         | Name of subject. 50 characters is enough. NOT NULL ensures we know which subject this mark is for.      |
| `marks`   | `INT NOT NULL CHECK (marks BETWEEN 0 AND 100)` | Stores the score. INT because marks are whole numbers. CHECK ensures only valid marks (0–100).          |

**Why this design:**

* Each row represents **one student in one subject**.
* Simple and normalized structure — no redundant data.
* Easy to calculate grades or show results later.

---

# **Step 4 — Insert default admin user**

```sql
INSERT INTO users (username, password) VALUES ('admin', '1234');
```

**Reasoning:**

* Provides an **initial login account** to test the system.
* Username `admin` is common and easy to remember for testing.
* Password `1234` is simple for development; in real applications, you would hash it.

---

# **Step 5 — Verify Tables**

```sql
SHOW TABLES;
DESCRIBE users;
DESCRIBE students;
```

* Shows the structure of the tables with column names, types, and constraints.
* Helps during the presentation to **explain the schema** confidently.

---

### ✅ Summary for Presentation

* **Database:** `studentdb` → dedicated, avoids conflict with other data
* **Tables:** `users` and `students`
* **Separation of concerns:** login logic vs student marks
* **Data integrity:** `NOT NULL`, `UNIQUE`, `PRIMARY KEY`, `CHECK` constraints
* **Simple & normalized design:** one row = one student-subject record

---

