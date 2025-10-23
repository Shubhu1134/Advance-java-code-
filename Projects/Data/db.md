
### **Step 1 — Create the database**

We will create **one database** called `studentdb` for this project:

```sql
CREATE DATABASE studentdb;
USE studentdb;
```

**Reason:**

* Single database keeps all project tables together.
* Name `studentdb` is short, descriptive, and matches the project.
* Using `USE studentdb` sets it as the active database for all future commands.

---

# **Step 2 — Create the `users` table (for login)**

We need a table to store login credentials for users (like admin or teachers):

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);
```

**Explanation for each column:**

1. **id INT AUTO_INCREMENT PRIMARY KEY**

   * `INT` → Integer type, simple numeric ID.
   * `AUTO_INCREMENT` → Automatically increases for each new user.
   * `PRIMARY KEY` → Unique identifier for each user.

2. **username VARCHAR(50) NOT NULL UNIQUE**

   * `VARCHAR(50)` → Text, max 50 characters, enough for names/email.
   * `NOT NULL` → Cannot leave empty.
   * `UNIQUE` → Prevents two users having the same username.

3. **password VARCHAR(50) NOT NULL**

   * `VARCHAR(50)` → Text for storing password.
   * `NOT NULL` → Every user must have a password.
   * For a simple project, plain text is okay; in production, you’d hash passwords.

---

# **Step 3 — Create the `students` table (store student info and grades)**

```sql
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    subject VARCHAR(50) NOT NULL,
    marks INT NOT NULL CHECK (marks BETWEEN 0 AND 100)
);
```

**Column explanations:**

1. **id INT AUTO_INCREMENT PRIMARY KEY**

   * Unique student identifier. Same reasoning as `users.id`.

2. **name VARCHAR(100) NOT NULL**

   * Max 100 characters for full name.
   * `NOT NULL` → every student must have a name.

3. **subject VARCHAR(50) NOT NULL**

   * Stores the subject name (e.g., “Math”, “Technical”, “Softskill”).
   * Max 50 characters is enough for descriptive subject names.

4. **marks INT NOT NULL CHECK (marks BETWEEN 0 AND 100)**

   * `INT` → Whole number for marks.
   * `NOT NULL` → Every student record must have marks.
   * `CHECK (marks BETWEEN 0 AND 100)` → Ensures valid scores (0–100).

---

# **Step 4 — Insert a default admin user**

```sql
INSERT INTO users (username, password) VALUES ('admin', '1234');
```

**Reason:**

* Provides **initial login** to test the application.
* Username `admin` and password `1234` is simple for development.
* You can later add more users or create a registration form.

---

✅ **After this, your database structure is ready**.

Your tables will look like this:

### **users**

| id | username | password |
| -- | -------- | -------- |

### **students**

| id | name | subject | marks |
| -- | ---- | ------- | ----- |

---
