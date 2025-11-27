Awesome — below is a complete, copy-paste-ready solution for the **full machine test** you asked for (All 3 associations + password auto-generation + BCrypt hashing + AES email encryption). I kept everything in your class/teaching style (plain Hibernate `SessionFactory`, console `Scanner`, DAO → Service → App), no Spring Boot app server.

I’ll give the **folder structure**, then every file you need (Java classes, `pom.xml`, `hibernate.cfg.xml`) and short **run instructions** at the end. Copy the files into the folders and run.

---

# Project folder structure

```
crudapp/
├── pom.xml
├── src/main/resources/
│   └── hibernate.cfg.xml
└── src/main/java/com/info/crudapp/
    ├── App.java
    ├── dao/
    │   ├── RoleDao.java
    │   └── UserDao.java
    ├── entity/
    │   ├── Address.java
    │   ├── Profile.java
    │   ├── Role.java
    │   └── User.java
    └── util/
        ├── DataConvertor.java
        ├── HibernateUtil.java
        ├── PasswordGenerator.java
        └── PasswordHashUtil.java
```

---

# 1) `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.info</groupId>
  <artifactId>crudapp</artifactId>
  <version>1.0-SNAPSHOT</version>
  <properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
  </properties>

  <dependencies>
    <!-- Hibernate -->
    <dependency>
      <groupId>org.hibernate.orm</groupId>
      <artifactId>hibernate-core</artifactId>
      <version>6.4.5.Final</version>
    </dependency>

    <!-- MySQL driver (change to your DB) -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-j</artifactId>
      <version>8.1.0</version>
    </dependency>

    <!-- Jakarta Persistence -->
    <dependency>
      <groupId>jakarta.persistence</groupId>
      <artifactId>jakarta.persistence-api</artifactId>
      <version>3.1.0</version>
    </dependency>

    <!-- BCrypt -->
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-crypto</artifactId>
      <version>6.2.0</version>
    </dependency>

    <!-- SLF4J simple for logs -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-simple</artifactId>
      <version>2.0.7</version>
    </dependency>
  </dependencies>
</project>
```

---

# 2) `src/main/resources/hibernate.cfg.xml`

(Modify DB URL / username / password to match your DB)

```xml
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
  <session-factory>
    <!-- JDBC connection -->
    <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/crudapp?useSSL=false&amp;serverTimezone=UTC</property>
    <property name="hibernate.connection.username">root</property>
    <property name="hibernate.connection.password">rootpassword</property>

    <!-- SQL dialect -->
    <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>

    <!-- show sql -->
    <property name="hibernate.show_sql">true</property>
    <property name="hibernate.format_sql">true</property>

    <!-- This will create/update tables automatically for testing -->
    <property name="hibernate.hbm2ddl.auto">update</property>

    <!-- Mapping classes (only necessary in older configs; Hibernate 6 auto-detects annotated classes if configured) -->
    <mapping class="com.info.crudapp.entity.User"/>
    <mapping class="com.info.crudapp.entity.Profile"/>
    <mapping class="com.info.crudapp.entity.Address"/>
    <mapping class="com.info.crudapp.entity.Role"/>
  </session-factory>
</hibernate-configuration>
```

---

# 3) Utility classes

### `DataConvertor.java` (encrypt/decrypt using AES-GCM; key must be set in env var `DATA_KEY` as base64)

```java
package com.info.crudapp.util;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import jakarta.persistence.AttributeConverter;

public class DataConvertor implements AttributeConverter<String, String> {
    private static final String ALGO = "AES/GCM/NoPadding";
    private static final int IV_LEN = 12;
    private static final int TAG_BIT = 128;

    private static byte[] getKeyBytes() {
        String b64 = System.getenv("DATA_KEY");
        if (b64 == null || b64.isBlank()) {
            throw new RuntimeException("DATA_KEY env variable is not set. Set a base64-encoded 16/24/32 bytes key.");
        }
        return Base64.getDecoder().decode(b64);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            if (attribute == null) return null;
            byte[] key = getKeyBytes();
            byte[] iv = new byte[IV_LEN];
            new SecureRandom().nextBytes(iv);

            Cipher cipher = Cipher.getInstance(ALGO);
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            GCMParameterSpec spec = new GCMParameterSpec(TAG_BIT, iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, spec);

            byte[] ct = cipher.doFinal(attribute.getBytes("UTF-8"));
            byte[] out = new byte[iv.length + ct.length];
            System.arraycopy(iv, 0, out, 0, iv.length);
            System.arraycopy(ct, 0, out, iv.length, ct.length);
            return Base64.getEncoder().encodeToString(out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null) return null;
            byte[] all = Base64.getDecoder().decode(dbData);
            byte[] key = getKeyBytes();
            byte[] iv = new byte[IV_LEN];
            System.arraycopy(all, 0, iv, 0, IV_LEN);
            byte[] ct = new byte[all.length - IV_LEN];
            System.arraycopy(all, IV_LEN, ct, 0, ct.length);

            Cipher cipher = Cipher.getInstance(ALGO);
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            GCMParameterSpec spec = new GCMParameterSpec(TAG_BIT, iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, spec);
            byte[] plain = cipher.doFinal(ct);
            return new String(plain, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```

---

### `PasswordGenerator.java`

```java
package com.info.crudapp.util;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%&*()-_=+[]{}<>?";
    private static final String ALL = UPPER + LOWER + DIGITS + SYMBOLS;
    private static final SecureRandom rnd = new SecureRandom();

    public static String generate(int length) {
        if (length < 8) throw new IllegalArgumentException("length >= 8 recommended");
        StringBuilder sb = new StringBuilder(length);
        sb.append(UPPER.charAt(rnd.nextInt(UPPER.length())));
        sb.append(LOWER.charAt(rnd.nextInt(LOWER.length())));
        sb.append(DIGITS.charAt(rnd.nextInt(DIGITS.length())));
        sb.append(SYMBOLS.charAt(rnd.nextInt(SYMBOLS.length())));
        for (int i = 4; i < length; i++) {
            sb.append(ALL.charAt(rnd.nextInt(ALL.length())));
        }
        // shuffle
        char[] a = sb.toString().toCharArray();
        for (int i = a.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            char t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        return new String(a);
    }
}
```

---

### `PasswordHashUtil.java`

```java
package com.info.crudapp.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordHashUtil {
    public static String hashPassword(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt(12));
    }
    public static boolean checkPassword(String plain, String hash) {
        return BCrypt.checkpw(plain, hash);
    }
}
```

---

### `HibernateUtil.java`

```java
package com.info.crudapp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory factory;

    public static synchronized SessionFactory getFactory() {
        try {
            if (factory == null) {
                Configuration cfg = new Configuration();
                cfg.configure("hibernate.cfg.xml");
                factory = cfg.buildSessionFactory();
            }
            return factory;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
```

---

# 4) Entities

### `User.java`

```java
package com.info.crudapp.entity;

import com.info.crudapp.util.DataConvertor;
import com.info.crudapp.util.PasswordHashUtil;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false, length=100)
    private String name;

    @Column(nullable=false, unique=true, length=255)
    @Convert(converter = DataConvertor.class)
    private String email;

    @Column(nullable=false, length=200)
    private String password; // BCrypt hash

    // One-to-one
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Profile profile;

    // One-to-many (addresses)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private java.util.List<Address> addresses = new java.util.ArrayList<>();

    // Many-to-many (roles)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String name, String email, String passwordPlain) {
        this.name = name;
        this.email = email;
        this.password = passwordPlain;
    }

    @PrePersist
    @PreUpdate
    public void hashPassword() {
        // If password already looks like bcrypt hash, skip
        if (password != null && !(password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$"))) {
            this.password = PasswordHashUtil.hashPassword(password);
        }
    }

    // helper methods
    public void addAddress(Address a) {
        addresses.add(a);
        a.setUser(this);
    }
    public void removeAddress(Address a) {
        addresses.remove(a);
        a.setUser(null);
    }
    public void setProfile(Profile p) {
        this.profile = p;
        if (p != null) p.setUser(this);
    }
    public void addRole(Role r) {
        roles.add(r);
        r.getUsers().add(this);
    }
    public void removeRole(Role r) {
        roles.remove(r);
        r.getUsers().remove(this);
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Profile getProfile() { return profile; }
    public java.util.List<Address> getAddresses() { return addresses; }
    public Set<Role> getRoles() { return roles; }
}
```

---

### `Profile.java`

```java
package com.info.crudapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String gender;
    private int age;
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public Profile() {}
    public Profile(String gender, int age, String phone) {
        this.gender = gender;
        this.age = age;
        this.phone = phone;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
```

---

### `Address.java`

```java
package com.info.crudapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String city;
    private String state;
    private String pincode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Address() {}
    public Address(String city, String state, String pincode) {
        this.city = city; this.state = state; this.pincode = pincode;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
```

---

### `Role.java`

```java
package com.info.crudapp.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    public Role() {}
    public Role(String roleName) { this.roleName = roleName; }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    public Set<User> getUsers() { return users; }
}
```

---

# 5) DAO layer

### `UserDao.java`

```java
package com.info.crudapp.dao;

import com.info.crudapp.entity.User;
import com.info.crudapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDao {
    public User save(User user) {
        SessionFactory sf = HibernateUtil.getFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        s.persist(user);
        tx.commit();
        s.close();
        return user;
    }

    public User findById(int id) {
        SessionFactory sf = HibernateUtil.getFactory();
        Session s = sf.openSession();
        User u = s.get(User.class, id);
        s.close();
        return u;
    }

    public void delete(User user) {
        SessionFactory sf = HibernateUtil.getFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        s.remove(user);
        tx.commit();
        s.close();
    }

    public User update(User user) {
        SessionFactory sf = HibernateUtil.getFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        s.merge(user);
        tx.commit();
        s.close();
        return user;
    }
}
```

---

### `RoleDao.java`

```java
package com.info.crudapp.dao;

import com.info.crudapp.entity.Role;
import com.info.crudapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RoleDao {
    public Role save(Role r) {
        SessionFactory sf = HibernateUtil.getFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        s.persist(r);
        tx.commit();
        s.close();
        return r;
    }

    public Role findByName(String name) {
        SessionFactory sf = HibernateUtil.getFactory();
        Session s = sf.openSession();
        Query<Role> q = s.createQuery("from Role r where r.roleName = :n", Role.class);
        q.setParameter("n", name);
        Role r = q.uniqueResult();
        s.close();
        return r;
    }

    public Role findById(int id) {
        SessionFactory sf = HibernateUtil.getFactory();
        Session s = sf.openSession();
        Role r = s.get(Role.class, id);
        s.close();
        return r;
    }
}
```

---

# 6) Service layer (business logic)

### `UserService` (in same package as DAOs or anywhere; here simple)

You can keep simple flows in `App.java`. I will implement light helper methods inside `App.java` to keep parity with your class style — DAO classes above handle persistence. (This keeps things simple and matches your earlier pattern.)

---

# 7) `App.java` (console menu + full flows)

```java
package com.info.crudapp;

import com.info.crudapp.dao.RoleDao;
import com.info.crudapp.dao.UserDao;
import com.info.crudapp.entity.Address;
import com.info.crudapp.entity.Profile;
import com.info.crudapp.entity.Role;
import com.info.crudapp.entity.User;
import com.info.crudapp.util.HibernateUtil;
import com.info.crudapp.util.PasswordGenerator;
import com.info.crudapp.util.PasswordHashUtil;
import org.hibernate.SessionFactory;

import java.util.Scanner;
import java.util.Set;

public class App {
    private static final UserDao userDao = new UserDao();
    private static final RoleDao roleDao = new RoleDao();

    public static void main(String[] args) {
        // Ensure DATA_KEY is set
        try {
            SessionFactory f = HibernateUtil.getFactory(); // initialize
        } catch (Exception e) {
            System.err.println("Hibernate init error: " + e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Register User (Profile + Addresses + Roles)");
            System.out.println("2. Fetch User by ID");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Add New Role");
            System.out.println("6. Assign Role to User");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            try {
                switch (ch) {
                    case 1 -> registerUser(sc);
                    case 2 -> fetchUser(sc);
                    case 3 -> updateUser(sc);
                    case 4 -> deleteUser(sc);
                    case 5 -> addRole(sc);
                    case 6 -> assignRole(sc);
                    case 0 -> {
                        System.out.println("Exiting...");
                        HibernateUtil.getFactory().close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid option");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerUser(Scanner sc) {
        System.out.println("Enter name:");
        String name = sc.nextLine().trim();
        System.out.println("Enter email:");
        String email = sc.nextLine().trim();

        // profile
        System.out.println("Enter gender:");
        String gender = sc.nextLine().trim();
        System.out.println("Enter age:");
        int age = Integer.parseInt(sc.nextLine().trim());
        System.out.println("Enter phone:");
        String phone = sc.nextLine().trim();
        Profile profile = new Profile(gender, age, phone);

        // addresses
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setProfile(profile);
        boolean addMore = true;
        while (addMore) {
            System.out.println("Enter address city:");
            String city = sc.nextLine().trim();
            System.out.println("Enter state:");
            String state = sc.nextLine().trim();
            System.out.println("Enter pincode:");
            String pincode = sc.nextLine().trim();
            Address addr = new Address(city, state, pincode);
            user.addAddress(addr);
            System.out.println("Add more address? (y/n)");
            String ans = sc.nextLine().trim();
            addMore = ans.equalsIgnoreCase("y");
        }

        // roles
        System.out.println("Enter roles to assign (comma separated role names), or leave blank:");
        String rolesLine = sc.nextLine().trim();
        if (!rolesLine.isBlank()) {
            String[] roleNames = rolesLine.split(",");
            for (String rn : roleNames) {
                String r = rn.trim();
                if (r.isEmpty()) continue;
                Role role = roleDao.findByName(r);
                if (role == null) {
                    role = new Role(r);
                    roleDao.save(role);
                }
                user.addRole(role);
            }
        }

        // password generation
        String generated = PasswordGenerator.generate(10);
        user.setPassword(generated); // will be hashed by @PrePersist
        User saved = userDao.save(user);
        System.out.println("User created id=" + saved.getId());
        System.out.println("Generated password (show ONCE) -> " + generated);
        System.out.println("Please deliver this password to the user via secure channel.");
    }

    private static void fetchUser(Scanner sc) {
        System.out.println("Enter user id:");
        int id = Integer.parseInt(sc.nextLine().trim());
        User u = userDao.findById(id);
        if (u == null) {
            System.out.println("User not found");
            return;
        }
        System.out.println("ID: " + u.getId());
        System.out.println("Name: " + u.getName());
        System.out.println("Email (decrypted): " + u.getEmail());
        Profile p = u.getProfile();
        if (p != null) System.out.println("Profile -> gender: " + p.getGender() + ", age: " + p.getAge() + ", phone: " + p.getPhone());
        System.out.println("Addresses:");
        u.getAddresses().forEach(a -> System.out.println(" - " + a.getCity() + ", " + a.getState() + " - " + a.getPincode()));
        System.out.println("Roles:");
        for (Role r : u.getRoles()) System.out.println(" - " + r.getRoleName());
    }

    private static void updateUser(Scanner sc) {
        System.out.println("Enter user id to update:");
        int id = Integer.parseInt(sc.nextLine().trim());
        User u = userDao.findById(id);
        if (u == null) { System.out.println("User not found"); return; }

        System.out.println("Current name: " + u.getName() + " -> Enter new name (or press enter to keep):");
        String name = sc.nextLine().trim();
        if (!name.isBlank()) u.setName(name);

        System.out.println("Current email: " + u.getEmail() + " -> Enter new email (or press enter to keep):");
        String email = sc.nextLine().trim();
        if (!email.isBlank()) u.setEmail(email);

        // profile update
        Profile pf = u.getProfile();
        if (pf == null) pf = new Profile();
        System.out.println("Current gender: " + (pf.getGender()==null?"":pf.getGender()) + " -> new (or enter):");
        String gender = sc.nextLine().trim();
        if (!gender.isBlank()) pf.setGender(gender);
        System.out.println("Current age: " + pf.getAge() + " -> new (or enter):");
        String ageLine = sc.nextLine().trim();
        if (!ageLine.isBlank()) pf.setAge(Integer.parseInt(ageLine));
        System.out.println("Current phone: " + (pf.getPhone()==null?"":pf.getPhone()) + " -> new (or enter):");
        String phone = sc.nextLine().trim();
        if (!phone.isBlank()) pf.setPhone(phone);
        u.setProfile(pf);

        // addresses: simple approach - remove all and add fresh (or you can implement per-address update)
        System.out.println("Do you want to replace addresses? (y/n)");
        String rep = sc.nextLine().trim();
        if (rep.equalsIgnoreCase("y")) {
            // remove existing addresses
            for (Address a : new java.util.ArrayList<>(u.getAddresses())) {
                u.removeAddress(a);
            }
            boolean addMore = true;
            while (addMore) {
                System.out.println("Enter address city:");
                String city = sc.nextLine().trim();
                System.out.println("Enter state:");
                String state = sc.nextLine().trim();
                System.out.println("Enter pincode:");
                String pincode = sc.nextLine().trim();
                Address addr = new Address(city, state, pincode);
                u.addAddress(addr);
                System.out.println("Add more address? (y/n)");
                String ans = sc.nextLine().trim();
                addMore = ans.equalsIgnoreCase("y");
            }
        }

        // roles update: simple replace
        System.out.println("Replace roles? (y/n)");
        String rr = sc.nextLine().trim();
        if (rr.equalsIgnoreCase("y")) {
            for (Role r : new java.util.ArrayList<>(u.getRoles())) {
                u.removeRole(r);
            }
            System.out.println("Enter roles (comma separated):");
            String rolesLine = sc.nextLine().trim();
            if (!rolesLine.isBlank()) {
                String[] roleNames = rolesLine.split(",");
                for (String rn : roleNames) {
                    String rname = rn.trim();
                    if (rname.isEmpty()) continue;
                    Role role = roleDao.findByName(rname);
                    if (role == null) role = roleDao.save(new Role(rname));
                    u.addRole(role);
                }
            }
        }

        // update password?
        System.out.println("Do you want to set a new password? (y/n)");
        String pw = sc.nextLine().trim();
        if (pw.equalsIgnoreCase("y")) {
            System.out.println("Enter new password:");
            String newpw = sc.nextLine().trim();
            u.setPassword(newpw); // will be hashed at @PreUpdate
        }

        userDao.update(u);
        System.out.println("User updated.");
    }

    private static void deleteUser(Scanner sc) {
        System.out.println("Enter user id to delete:");
        int id = Integer.parseInt(sc.nextLine().trim());
        User u = userDao.findById(id);
        if (u == null) { System.out.println("User not found"); return; }
        userDao.delete(u);
        System.out.println("User deleted.");
    }

    private static void addRole(Scanner sc) {
        System.out.println("Enter role name:");
        String rn = sc.nextLine().trim();
        Role existing = roleDao.findByName(rn);
        if (existing != null) {
            System.out.println("Role already exists id=" + existing.getId());
            return;
        }
        Role r = new Role(rn);
        roleDao.save(r);
        System.out.println("Role saved id=" + r.getId());
    }

    private static void assignRole(Scanner sc) {
        System.out.println("Enter user id:");
        int uid = Integer.parseInt(sc.nextLine().trim());
        User u = userDao.findById(uid);
        if (u == null) { System.out.println("User not found"); return; }
        System.out.println("Enter role name to assign:");
        String rn = sc.nextLine().trim();
        Role r = roleDao.findByName(rn);
        if (r == null) {
            r = roleDao.save(new Role(rn));
        }
        u.addRole(r);
        userDao.update(u);
        System.out.println("Role assigned.");
    }
}
```

---

# Run & Test — step-by-step

1. **Set DATA_KEY environment variable** (required by `DataConvertor`):

   * Linux / macOS (bash):

     ```bash
     export DATA_KEY=$(openssl rand -base64 32)
     ```

     This creates a 256-bit key encoded in base64. If you don't have `openssl`, generate any base64 16/24/32-byte key.

   * Windows (PowerShell):

     ```powershell
     $k = [Convert]::ToBase64String((1..32 | % {Get-Random -Maximum 256}))
     setx DATA_KEY $k
     ```

   Make sure `DATA_KEY` is available in your environment before running the app.

2. **Configure DB** in `hibernate.cfg.xml` — set URL, username, password. Create database `crudapp` manually or let Hibernate create it (MySQL user must have permission to create DB).

3. **Build**

   ```bash
   mvn clean package
   ```

4. **Run**

   * From IDE: run `App.main`.
   * From terminal:

     ```bash
     java -cp target/crudapp-1.0-SNAPSHOT.jar com.info.crudapp.App
     ```

     (You may need to include dependency jars on classpath or use the exec-maven-plugin to create an uber-jar. For quick testing run from IDE or `mvn exec:java`.)

5. **Menu actions**

   * `1` Register user → follow prompts. The generated password will be printed **one time**.
   * `2` Fetch user by id → shows decrypted email, profile, addresses, roles.
   * `3` Update user → you can update name/email/profile/addresses/roles/password.
   * `4` Delete user → cascades to profile, addresses and removes user-role associations.
   * `5` Add role
   * `6` Assign role to user

---

# Important notes & class-aligned choices

* I kept your simple DAO/service style: operations directly use `Session` & `Transaction`.
* `DataConvertor` uses an environment variable for the key — **never hard-code encryption keys**.
* Passwords are hashed with BCrypt in `@PrePersist/@PreUpdate` of `User`.
* Email is encrypted in DB and automatically decrypted when entity loads.
* Searching by email is not implemented (encrypted column prevents direct DB equals). If you want to search by email, we can add a `emailHash` column (SHA-256) to index/search safely. Tell me and I’ll add it.
* Many-to-many `Role` uses `user_roles` join table. `RoleDao.findByName` helps prevent duplicates.
* For simplicity, some update flows replace all addresses/roles; you can refine per-address or per-role editing if needed.

---

If you want I will now:

* Provide **alternative `hibernate.cfg.xml`** for PostgreSQL, or
* Add **email searchability** (emailHash column + converter) so you can `findByEmail`, or
* Create an **executable fat-jar** config (`maven-shade-plugin`) to run easily.

Which one next?
