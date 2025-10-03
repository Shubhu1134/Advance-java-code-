**Day 2: Strings + Collections (Core + Advanced)**.

---

# 📌 **Day 2 – Strings + Collections**

---

## **1. Strings in Java**

- **Immutable**: Once created, cannot be changed.

  ```java
  String s = "Java";
  s.concat("World");
  System.out.println(s); // "Java" (unchanged)
  ```

- New object created in memory, old one stays unchanged.

👉 Why immutable?

- Security (passwords, URLs).
- Thread-safety.
- String pool optimization.

---

### **String Pool**

- Strings are stored in a **special heap area** = _String Constant Pool_.
- Example:

  ```java
  String s1 = "Java";
  String s2 = "Java"; // points to same object in pool
  System.out.println(s1 == s2); // true
  ```

- But:

  ```java
  String s1 = new String("Java");
  String s2 = "Java";
  System.out.println(s1 == s2); // false (different objects)
  ```

💡 **Interview Tip:** Always use `equals()` for string content comparison, not `==`.

---

### **Important String methods**

- `length()`, `charAt()`, `substring()`, `toLowerCase()`, `toUpperCase()`, `trim()`, `split()`, `replace()`.
- `StringBuilder` vs `StringBuffer`:

  - Both mutable.
  - StringBuffer = synchronized (thread-safe, slower).
  - StringBuilder = not synchronized (faster).

---

## **2. equals() vs ==**

- `==` → compares references (memory address).
- `equals()` → compares values (content) if overridden.

👉 Example:

```java
String s1 = new String("Hello");
String s2 = new String("Hello");
System.out.println(s1 == s2);     // false
System.out.println(s1.equals(s2));// true
```

---

## **3. hashCode() & equals()**

- Used in **HashMap, HashSet, Hashtable**.
- **Contract:**

  - If `a.equals(b) == true`, then `a.hashCode() == b.hashCode()`.
  - If hashCode differs, objects must be different.

👉 Example:

```java
class Student {
   int id;
   String name;

   public int hashCode() { return id; }
   public boolean equals(Object o) {
      Student s = (Student)o;
      return this.id == s.id;
   }
}
```

---

## **4. Wrapper Classes & Autoboxing**

- Wrapper classes (Integer, Double, etc.) wrap primitives in objects.
- **Autoboxing:** primitive → wrapper automatically.
- **Unboxing:** wrapper → primitive automatically.

```java
Integer x = 10; // autoboxing
int y = x;      // unboxing
```

---

## **5. Collections Framework**

- Interfaces: **Collection, List, Set, Queue, Map**.
- **List** → ordered, allows duplicates (ArrayList, LinkedList, Vector).
- **Set** → no duplicates (HashSet, LinkedHashSet, TreeSet).
- **Map** → key-value pairs (HashMap, LinkedHashMap, TreeMap).

---

### **ArrayList vs LinkedList**

- ArrayList → dynamic array, fast random access, slow insertion/deletion in middle.
- LinkedList → doubly linked list, fast insert/delete, slow access.

---

### **HashMap vs Hashtable**

- HashMap → non-synchronized (faster), allows null key + null values.
- Hashtable → synchronized (slower), no null key, no null values.

---

### **HashSet vs TreeSet**

- HashSet → unordered, based on hashCode.
- TreeSet → sorted order, uses `compareTo()` or Comparator.

---

### **Comparable vs Comparator**

- **Comparable (natural order):**

  ```java
  class Student implements Comparable<Student> {
     int id;
     public int compareTo(Student s) { return this.id - s.id; }
  }
  ```

- **Comparator (custom order):**

  ```java
  Collections.sort(list, (s1, s2) -> s1.name.compareTo(s2.name));
  ```

---

## **6. Fail-Fast vs Fail-Safe**

- **Fail-Fast** → throws `ConcurrentModificationException` if modified while iterating (e.g., ArrayList, HashMap).
- **Fail-Safe** → uses copy, so no exception (e.g., CopyOnWriteArrayList, ConcurrentHashMap).

---

## **7. Immutability in Custom Class**

To make class immutable:

- Declare class `final`.
- Make fields `private final`.
- No setters.
- Return copies of mutable objects.

---

# 📌 **Day 2 Practice Questions**

1. Why are Strings immutable in Java?
2. Difference between String, StringBuilder, StringBuffer?
3. What is String Pool? How is it different from heap?
4. What happens when you compare Strings with `==` vs `equals()`?
5. Why override `hashCode()` if you override `equals()`?
6. Difference between ArrayList and LinkedList?
7. Difference between HashMap and Hashtable?
8. Can HashMap have duplicate keys or values?
9. Difference between HashSet and TreeSet?
10. Difference between Comparable and Comparator?
11. What is a fail-fast iterator? Give example.
12. How do you make your own class immutable?

---

**model answers for Day 2 Practice Qs (Strings + Collections)**.

# 📌 **Day 2 – Model Answers**

---

### **1. Why are Strings immutable in Java?**

✅ Because:

- Security (e.g., passwords, URLs shouldn’t change).
- Thread-safety (safe sharing between threads).
- String Pool optimization (same literals can be reused).
  👉 Example:

```java
String s1 = "Java";
String s2 = "Java"; // points to same object in pool
```

---

### **2. Difference between String, StringBuilder, StringBuffer?**

- **String** → Immutable. Every modification creates new object.
- **StringBuilder** → Mutable, faster, not thread-safe.
- **StringBuffer** → Mutable, synchronized, slower but thread-safe.

---

### **3. What is String Pool? How is it different from heap?**

- **String Pool**: special area in heap where literal Strings are stored & reused.
- If `"Java"` already exists, JVM reuses it.
- Heap: general memory for objects (including String objects created via `new`).

---

### **4. `==` vs `equals()` in Strings**

- `==` → reference comparison (same memory address?).
- `equals()` → value/content comparison.
  👉 Example:

```java
String a = new String("Hi");
String b = new String("Hi");
System.out.println(a == b);     // false
System.out.println(a.equals(b));// true
```

---

### **5. Why override `hashCode()` if you override `equals()`?**

✅ Because collections like **HashMap/HashSet** use **hashCode() first** to locate object.
👉 Contract: If two objects are equal (equals → true), they must have same hashCode.

---

### **6. Difference between ArrayList and LinkedList?**

- ArrayList → dynamic array, fast random access, slow insertion/deletion in middle.
- LinkedList → doubly linked, fast insertion/deletion, slow random access.

---

### **7. Difference between HashMap and Hashtable?**

- HashMap → non-synchronized (faster), allows **1 null key + many null values**.
- Hashtable → synchronized (slower), **no null key, no null values**.

---

### **8. Can HashMap have duplicate keys or values?**

- Keys → ❌ No duplicates (new value overrides old one).
- Values → ✅ Duplicates allowed.
  👉 Example:

```java
map.put(1, "A");
map.put(1, "B"); // replaces A
map.put(2, "B"); // duplicate value allowed
```

---

### **9. Difference between HashSet and TreeSet?**

- HashSet → unordered, based on hashCode().
- TreeSet → sorted order, based on compareTo()/Comparator.

---

### **10. Comparable vs Comparator?**

- **Comparable**: defines natural order, implemented inside class (`compareTo`).
- **Comparator**: defines custom order, implemented outside class (`compare`).

👉 Example:

```java
class Student implements Comparable<Student> {
   int id; String name;
   public int compareTo(Student s){ return this.id - s.id; } // natural order
}
Collections.sort(list, (a,b) -> a.name.compareTo(b.name)); // custom order
```

---

### **11. What is a fail-fast iterator? Give example.**

- Fail-fast iterators throw `ConcurrentModificationException` if collection is modified while iterating.
  👉 Example:

```java
List<Integer> list = new ArrayList<>();
list.add(1); list.add(2);
for(Integer i: list){
   list.add(3); // throws ConcurrentModificationException
}
```

---

### **12. How do you make your own class immutable?**

Steps:

1. Declare class as `final`.
2. Make fields `private final`.
3. No setters.
4. Return copies of mutable objects.

👉 Example:

```java
final class Student {
   private final int id;
   private final String name;
   Student(int id, String name){ this.id=id; this.name=name; }
   public int getId(){ return id; }
   public String getName(){ return name; }
}
```

---

**Day 2 Hands-on Coding Tasks**

**interview-ready with practical demos**.

---

# 📌 **Day 2 – Coding Practice (Strings + Collections)**

---

### **1. String Immutability Demo**

👉 Prove that Strings are immutable.

```java
public class StringImmutability {
    public static void main(String[] args) {
        String s1 = "Java";
        String s2 = s1;
        s1 = s1 + " Rocks";  // creates new object
        System.out.println(s1); // Java Rocks
        System.out.println(s2); // Java
    }
}
```

🔑 Show interviewer: `s2` remains unchanged → proof of immutability.

---

### **2. StringBuilder vs StringBuffer**

👉 Measure performance difference.

```java
public class StringBuilderVsBuffer {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("A");
        for(int i=0; i<100000; i++) sb.append("B");
        long end = System.currentTimeMillis();
        System.out.println("StringBuilder Time: " + (end-start));

        start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer("A");
        for(int i=0; i<100000; i++) sbf.append("B");
        end = System.currentTimeMillis();
        System.out.println("StringBuffer Time: " + (end-start));
    }
}
```

---

### **3. ArrayList vs LinkedList**

👉 Compare insertion performance.

```java
import java.util.*;

public class ListComparison {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long start = System.nanoTime();
        for(int i=0; i<100000; i++) arrayList.add(i);
        long end = System.nanoTime();
        System.out.println("ArrayList Add: " + (end-start));

        start = System.nanoTime();
        for(int i=0; i<100000; i++) linkedList.add(i);
        end = System.nanoTime();
        System.out.println("LinkedList Add: " + (end-start));
    }
}
```

---

### **4. HashMap Behavior (Duplicate Keys/Nulls)**

👉 Test interview trick question.

```java
import java.util.*;

public class HashMapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(1, "B"); // replaces A
        map.put(null, "C"); // one null key allowed
        map.put(2, null);   // multiple null values
        map.put(3, null);

        System.out.println(map);
    }
}
```

---

### **5. Comparable vs Comparator**

👉 Sort list in two ways.

```java
import java.util.*;

class Student implements Comparable<Student> {
    int id; String name;
    Student(int id, String name){ this.id=id; this.name=name; }
    public int compareTo(Student s){ return this.id - s.id; } // natural order by id
    public String toString(){ return id + " - " + name; }
}

public class SortDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(3, "Raj"));
        list.add(new Student(1, "Aman"));
        list.add(new Student(2, "Neha"));

        Collections.sort(list); // natural order
        System.out.println("By ID: " + list);

        Collections.sort(list, (a,b) -> a.name.compareTo(b.name)); // custom order
        System.out.println("By Name: " + list);
    }
}
```

---

### **6. Fail-fast Iterator Demo**

```java
import java.util.*;

public class FailFastDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A"); list.add("B");

        for(String s : list){
            // list.add("C"); // Uncomment → ConcurrentModificationException
            System.out.println(s);
        }
    }
}
```

---

### **7. Immutable Class**

👉 Write your own immutable class.

```java
final class Employee {
    private final int id;
    private final String name;
    Employee(int id, String name){
        this.id = id; this.name = name;
    }
    public int getId(){ return id; }
    public String getName(){ return name; }
}

public class ImmutableDemo {
    public static void main(String[] args) {
        Employee e = new Employee(1, "Shubh");
        System.out.println(e.getId() + " - " + e.getName());
        // No setters, can’t modify object → Immutable
    }
}
```

---
