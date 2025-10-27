
---

## ⚙️ Step 1: Database Setup (in MySQL)

Run these commands in your MySQL terminal / MySQL Workbench:

```sql
CREATE DATABASE company_db;

USE company_db;

CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    salary DECIMAL(10,2),
    department VARCHAR(50)
);
```

✅ Your database is now ready.

---

## 🧱 Step 2: Project Folder Structure

```
src/
 ├── model/
 │     └── Employee.java
 ├── dao/
 │     ├── EmployeeDAO.java
 │     └── EmployeeDAOImpl.java
 ├── util/
 │     └── DBConnection.java
 └── main/
       └── EmployeeApp.java
```

---

## 🧩 Step 3: `DBConnection.java` (in `util` package)

```java
package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/company_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection con = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
```

✅ This handles your database connection.

---

## 🧍 Step 4: `Employee.java` (in `model` package)

```java
package model;

public class Employee {
    private int id;
    private String name;
    private String email;
    private double salary;
    private String department;

    public Employee() {}

    public Employee(String name, String email, double salary, String department) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = department;
    }

    public Employee(int id, String name, String email, double salary, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = department;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return String.format("%-5d %-20s %-25s %-10.2f %-15s",
                id, name, email, salary, department);
    }
}
```

✅ Represents one employee record.

---

## 💾 Step 5: `EmployeeDAO.java` (in `dao` package)

```java
package dao;

import java.util.List;
import model.Employee;

public interface EmployeeDAO {
    boolean addEmployee(Employee emp);
    List<Employee> getAllEmployees();
    boolean updateEmployee(Employee emp);
    boolean deleteEmployee(int id);
}
```

✅ Interface defines CRUD operations.

---

## ⚙️ Step 6: `EmployeeDAOImpl.java` (in `dao` package)

```java
package dao;

import model.Employee;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO employee(name, email, salary, department) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setDouble(3, emp.getSalary());
            ps.setString(4, emp.getDepartment());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getDouble("salary"),
                    rs.getString("department")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET name=?, email=?, salary=?, department=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setDouble(3, emp.getSalary());
            ps.setString(4, emp.getDepartment());
            ps.setInt(5, emp.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
```

✅ Implements actual CRUD using JDBC.

---

## 💻 Step 7: `EmployeeApp.java` (in `main` package)

```java
package main;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.Employee;
import java.util.*;

public class EmployeeApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAOImpl();

        while (true) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();

                    Employee emp = new Employee(name, email, salary, dept);
                    if (dao.addEmployee(emp))
                        System.out.println("✅ Employee added successfully!");
                    else
                        System.out.println("❌ Failed to add employee.");
                    break;

                case 2:
                    List<Employee> list = dao.getAllEmployees();
                    System.out.println("\nID    Name                 Email                     Salary     Department");
                    System.out.println("--------------------------------------------------------------------------");
                    for (Employee e : list)
                        System.out.println(e);
                    break;

                case 3:
                    System.out.print("Enter Employee ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter New Email: ");
                    String uemail = sc.nextLine();
                    System.out.print("Enter New Salary: ");
                    double usalary = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter New Department: ");
                    String udept = sc.nextLine();

                    Employee updateEmp = new Employee(uid, uname, uemail, usalary, udept);
                    if (dao.updateEmployee(updateEmp))
                        System.out.println("✅ Employee updated successfully!");
                    else
                        System.out.println("❌ Update failed.");
                    break;

                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    int did = sc.nextInt();
                    if (dao.deleteEmployee(did))
                        System.out.println("✅ Employee deleted successfully!");
                    else
                        System.out.println("❌ Deletion failed.");
                    break;

                case 5:
                    System.out.println("👋 Exiting... Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("⚠️ Invalid choice! Try again.");
            }
        }
    }
}
```

✅ This is your **main console UI** for interacting with the DAO.

---

## 🧩 Step 8: Add MySQL Connector JAR

1. Download **MySQL Connector/J** (if not already):
   [https://dev.mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/)

2. In **NetBeans / Eclipse**:

   * Right-click project → **Properties**
   * Go to **Libraries → Add JAR/Folder**
   * Select `mysql-connector-j-8.x.x.jar`
   * Apply → OK

---

## ✅ Final Output Example

```
===== Employee Management System =====
1. Add Employee
2. View All Employees
3. Update Employee
4. Delete Employee
5. Exit
Enter choice: 1
Enter Name: Shubh Ji
Enter Email: shubh@example.com
Enter Salary: 50000
Enter Department: IT
✅ Employee added successfully!

===== Employee Management System =====
1. Add Employee
2. View All Employees
...
```

---

