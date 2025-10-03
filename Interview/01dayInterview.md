**Day 1 (Core Java)**.
**theory + examples + interview tips**

---

# 📌 **Day 1 – Core Java Interview Prep**

### **1. JDK vs JRE vs JVM**

(We already covered — Kitchen analogy 🍴)
👉 Quick recap:

- JDK → Dev kit (JRE + tools like javac).
- JRE → Runtime (JVM + libraries).
- JVM → Executes bytecode.
  💡 **Interview Tip:** Say **JVM is platform-dependent, bytecode is platform-independent.**

---

### **2. `public static void main(String[] args)`**

(We covered too, but key takeaway:)

- `public` → JVM can access.
- `static` → Called without object.
- `void` → No return.
- `main` → Special entry name.
- `args` → Command-line input.
  💡 Interviewer twist: “Can main be private?”
  👉 Yes, it compiles, but JVM won’t run (`NoSuchMethodError: main`).

---

### **3. Why main is static?**

- No object before program runs.
- Saves memory (no object creation).
- JVM directly calls `ClassName.main()`.

---

### **4. System.out.println() family**

- `System` = final class.
- `out` = static PrintStream object.
- `print()` = no newline.
- `println()` = newline.
- `printf()` = formatted.
- `err.println()` = error stream.
  💡 Tip: “Why is `out` static?” → So we don’t need `new System()` object.

---

### **5. OOPs Concepts**

(VERY HIGHLY ASKED 🚨 — must explain with examples!)

**4 Pillars of OOP:**

1. **Encapsulation** → Binding data + methods in one unit.

   ```java
   class BankAccount {
       private double balance;
       public void deposit(double amt) { balance += amt; }
       public double getBalance() { return balance; }
   }
   ```

   - `private` + getters/setters = encapsulation.
   - Real life: ATM hides internal cash handling.

2. **Inheritance** → One class derives from another.

   ```java
   class Animal { void eat(){} }
   class Dog extends Animal { void bark(){} }
   ```

   - `Dog` inherits `eat()`.
   - Promotes **reusability**.

3. **Polymorphism** → Many forms.

   - Compile-time (Overloading):

     ```java
     void add(int a, int b) {}
     void add(double a, double b) {}
     ```

   - Runtime (Overriding):

     ```java
     class Animal { void sound() { System.out.println("Generic"); } }
     class Dog extends Animal { void sound() { System.out.println("Bark"); } }
     ```

     - Called based on object at runtime.

4. **Abstraction** → Hiding implementation, showing only essentials.

   - Abstract class:

     ```java
     abstract class Shape { abstract void draw(); }
     class Circle extends Shape { void draw() { System.out.println("Circle"); } }
     ```

   - Interface: 100% abstraction before Java 8.

💡 **Tricky Question:** “Which is better: abstract class vs interface?”
👉 Use abstract class when: some **common implementation** exists.
👉 Use interface when: only **contract/behaviour**.

---

### **6. Access Modifiers in Java**

- **public** → accessible everywhere.
- **protected** → accessible in same package + subclasses.
- **default (no modifier)** → package-private.
- **private** → only within class.

💡 **Tricky Q:** Can a top-level class be private?
👉 No. Only `public` or default allowed.

---

### **7. Constructors**

- Used to initialize object.
- Same name as class, no return type.
- Types:

  - Default (if none given).
  - Parameterized.
  - Copy constructor (not automatic, must define).

**Example:**

```java
class Car {
    String model;
    Car(String m) { this.model = m; }
}
Car c = new Car("BMW");
```

💡 **Tricky Q:**

- “Can constructor be abstract?” ❌ No.
- “Can constructor return value?” ❌ No (not even void).
- “Can constructor call another constructor?” ✅ Yes, using `this()`.

---

### **8. `this` and `super`**

- **this:** Refers to current object.

  ```java
  this.name = name;
  this.show();
  this(); // calls another constructor
  ```

- **super:** Refers to parent class.

  ```java
  super(); // parent constructor
  super.method(); // parent method
  ```

💡 **Tricky Q:** What if both parent and child have constructor with params?
👉 Child must explicitly call `super(args)` if no default constructor exists.

---

### **9. final keyword**

- **final variable** → constant.
- **final method** → cannot override.
- **final class** → cannot extend.
  💡 **Trick:** “Can final variable be initialized later?”
  👉 Yes, if it’s a **blank final** (must be initialized in constructor).

---

### **10. static keyword**

- **static variable** → belongs to class, not object. Shared among all objects.
- **static method** → called without object. Cannot access non-static members directly.
- **static block** → runs once when class loads.

  ```java
  static { System.out.println("Class loaded!"); }
  ```

💡 **Trick:** Order of execution?

1. Static block → 2. main method → 3. constructor.

---

here’s a **Day 1 practice set** with tricky variations.

# 📌 **Day 1 – Practice Questions (Core Java)**

### **JDK/JRE/JVM**

1. What is the difference between JDK and JRE? Can we run Java code with only JDK installed (without JRE)?
2. If JVM is platform-dependent, how can Java be “write once, run anywhere”?

---

### **main method**

3. What happens if:

   - You make `main` **private**?
   - You remove `static`?
   - You overload `main` method? (with different parameters)

---

### **System.out.println**

4. Explain how `System.out.println("Hello");` works internally (break down `System`, `out`, `println`).
5. Can you redirect `System.out` and `System.err` to a file instead of console? How?

---

### **OOP Concepts**

6. Give a **real-life example** of each OOP pillar (Encapsulation, Inheritance, Polymorphism, Abstraction).
7. Difference between **method overloading** and **overriding**? Which one is compile-time and which is runtime?

---

### **Access Modifiers**

8. If a method is `protected`, can it be accessed from another package? How?
9. Why can’t a **top-level class** be `private` or `protected`?

---

### **Constructors**

10. What happens if you declare a constructor with `return type`? (e.g., `public void MyClass(){}`)
11. Can a constructor call another constructor? Show example with `this()`.
12. Can a constructor be `final` or `static`? Why not?

---

### **this & super**

13. If parent and child both have a variable with the same name, how do you access it in child?
14. Difference between `this()` and `super()` in constructors? Which must be the first statement?

---

### **final keyword**

15. What is a **blank final variable**? Can it be initialized later?
16. Can we declare a constructor as `final`? Why/why not?

---

### **static keyword**

17. Can a static method access non-static variables? Why/why not?
18. What is the difference between a **static block** and a **constructor**?
19. Execution order: Static block, main method, instance block, constructor → Which runs first and why?

---

---

# 📌 **Day 1 – Model Answers**

### **JDK/JRE/JVM**

**Q1:** Difference between JDK and JRE? Can we run Java code with only JDK installed?
✅ **Answer:**

- **JDK** = JRE + development tools (javac, debugger).
- **JRE** = JVM + libraries (only for running).
  👉 If you install JDK, it already contains JRE. So yes, you can run Java code with only JDK installed.

**Q2:** JVM is platform-dependent, but Java is “write once, run anywhere”. How?
✅ **Answer:**

- JVM is platform-specific (different implementations for Windows/Linux/Mac).
- But Java compiles source into **bytecode (.class)**, which is **platform-independent**.
  👉 Same bytecode runs on any JVM → portability.

---

### **main method**

**Q3:** What if:

- `private static void main(String[] args)` → Compiles, but JVM won’t find it → `NoSuchMethodError`.
- Remove `static` → JVM won’t create object → `NoSuchMethodError`.
- Overload `main` → Works, but JVM always calls **only** `public static void main(String[] args)`. Others can be called manually.

---

### **System.out.println**

**Q4:** Explain `System.out.println("Hello")`.
✅ **Answer:**

- `System` → final class in `java.lang`.
- `out` → static `PrintStream` object inside System.
- `println()` → method of PrintStream.

**Q5:** Can we redirect `System.out`/`System.err` to file?
✅ **Answer:**
Yes, using `System.setOut(new PrintStream(new File("output.txt")));`

---

### **OOP Concepts**

**Q6:** Real-life examples of OOP pillars:

- Encapsulation → ATM machine hides internal details.
- Inheritance → Dog extends Animal.
- Polymorphism → Same “draw()” method for Circle/Rectangle.
- Abstraction → Interface like “List” hides internal ArrayList/LinkedList.

**Q7:** Overloading vs Overriding?

- Overloading → Same method name, different parameters. Compile-time.
- Overriding → Child class changes parent method. Runtime.

---

### **Access Modifiers**

**Q8:** If a method is protected, can it be accessed from another package?
✅ **Answer:**
Yes, but **only through inheritance** in subclass.

**Q9:** Why can’t a top-level class be private/protected?
✅ **Answer:**
Because JVM/classloader must access it → restricting scope would make it invisible. Only `public` or default allowed.

---

### **Constructors**

**Q10:** What if constructor has return type?
✅ **Answer:**
Then it’s no longer a constructor → treated as normal method. Compiler doesn’t consider it a constructor.

**Q11:** Can constructor call another constructor?
✅ **Answer:**
Yes, using `this()`. Must be the first line.

```java
class A {
   A() { this(10); }
   A(int x) { System.out.println(x); }
}
```

**Q12:** Can constructor be final/static?
✅ **Answer:**
No.

- `final` → Constructor is never inherited.
- `static` → Constructors are tied to objects, not class.

---

### **this & super**

**Q13:** If parent & child both have same variable, how to access parent’s?
✅ **Answer:** `super.varName`.

**Q14:** Difference between `this()` and `super()`?
✅ **Answer:**

- `this()` → calls another constructor in same class.
- `super()` → calls parent constructor.
  👉 Both must be first statement in constructor.

---

### **final keyword**

**Q15:** What is blank final variable?
✅ **Answer:**
A final variable not initialized during declaration but must be initialized in constructor.

**Q16:** Can we declare constructor as final?
✅ **Answer:**
No. Constructors are not inherited → so `final` makes no sense.

---

### **static keyword**

**Q17:** Can static method access non-static variables?
✅ **Answer:**
No. Because non-static vars belong to an object, and static methods run before object exists.

**Q18:** Difference: static block vs constructor?

- Static block → Runs once when class loads.
- Constructor → Runs each time object is created.

**Q19:** Execution order?
✅ **Answer:**

1. Static block (once when class loads)
2. main() method
3. Instance block (before constructor, on object creation)
4. Constructor

---
