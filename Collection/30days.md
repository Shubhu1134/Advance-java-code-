## **full 30-day collection of starter templates**

# 📅 30-Day Java Collections Starter Templates

---

## 🔹 Week 1 – Foundations (Lists, Sets, Maps)

### Day 1

**Task 1: ArrayList basics**

```java
import java.util.*;

public class Day1_ArrayListDemo {
    public static void main(String[] args) {
        // TODO: Create an ArrayList of Strings
        // Add, remove, iterate using for, for-each, and Iterator
    }
}
```

**Task 2: Reverse a list**

```java
import java.util.*;

public class Day1_ReverseList {
    public static void main(String[] args) {
        // TODO: Create a List<Integer>, add elements
        // Reverse using Collections.reverse()
    }
}
```

---

### Day 2

**Task 1: Remove duplicates with Set**

```java
import java.util.*;

public class Day2_RemoveDuplicates {
    public static void main(String[] args) {
        // TODO: Create a List<Integer> with duplicates
        // Remove duplicates using HashSet, LinkedHashSet, TreeSet
    }
}
```

---

### Day 3

**Task: Compare ArrayList vs LinkedList**

```java
import java.util.*;

public class Day3_ListComparison {
    public static void main(String[] args) {
        // TODO: Add 1M elements in ArrayList & LinkedList
        // Measure insertion time in middle
    }
}
```

---

### Day 4

**Task 1: Unique characters**

```java
import java.util.*;

public class Day4_UniqueChars {
    public static void main(String[] args) {
        // TODO: Check if a string has all unique chars using HashSet
    }
}
```

**Task 2: Sorted Set**

```java
import java.util.*;

public class Day4_SortedSet {
    public static void main(String[] args) {
        // TODO: Insert random integers into TreeSet
        // Print in sorted order
    }
}
```

---

### Day 5

**Task 1: Stack**

```java
import java.util.*;

public class Day5_StackDemo {
    public static void main(String[] args) {
        // TODO: Push, pop, peek using Stack
    }
}
```

**Task 2: Queue**

```java
import java.util.*;

public class Day5_QueueDemo {
    public static void main(String[] args) {
        // TODO: Enqueue and dequeue using LinkedList
    }
}
```

---

### Day 6

**Task: Word frequency counter**

```java
import java.util.*;

public class Day6_WordFrequency {
    public static void main(String[] args) {
        // TODO: Count frequency of each word in a sentence using HashMap
    }
}
```

---

### Day 7 (Mini Project: Student Management System)

```java
import java.util.*;

class Student {
    int id;
    String name;
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class Day7_StudentManagement {
    public static void main(String[] args) {
        // TODO: Use Map<Integer, Student>
        // Add, remove, update, search students
    }
}
```

---

## 🔹 Week 2 – Utilities, Sorting, Iterators

### Day 8

**Task: Sort with Comparable**

```java
import java.util.*;

class Student implements Comparable<Student> {
    int marks;
    String name;
    public Student(String name, int marks) { this.name = name; this.marks = marks; }
    public int compareTo(Student s) {
        return this.marks - s.marks; // TODO: Modify if needed
    }
}

public class Day8_SortStudents {
    public static void main(String[] args) {
        // TODO: Create list of students and sort using Collections.sort()
    }
}
```

---

### Day 9

**Task: Sort with Comparator**

```java
import java.util.*;

class Employee {
    String name;
    int salary;
    public Employee(String n, int s) { name = n; salary = s; }
}

public class Day9_ComparatorSort {
    public static void main(String[] args) {
        // TODO: Sort employees by name and by salary using Comparator
    }
}
```

---

### Day 10

**Task: Sort HashMap by keys/values**

```java
import java.util.*;

public class Day10_SortMap {
    public static void main(String[] args) {
        // TODO: Sort map by keys and values
    }
}
```

---

### Day 11

**Task: Iterator & ListIterator**

```java
import java.util.*;

public class Day11_IteratorDemo {
    public static void main(String[] args) {
        // TODO: Use Iterator and ListIterator to traverse and modify a list
    }
}
```

---

### Day 12

**Task: PriorityQueue + Deque**

```java
import java.util.*;

public class Day12_PriorityQueueDeque {
    public static void main(String[] args) {
        // TODO: Implement task priority manager using PriorityQueue
        // TODO: Demonstrate stack + queue behavior with ArrayDeque
    }
}
```

---

### Day 13

**Task: First non-repeating character**

```java
import java.util.*;

public class Day13_FirstUniqueChar {
    public static void main(String[] args) {
        // TODO: Use LinkedHashMap to find first non-repeated character
    }
}
```

---

### Day 14 (Mini Project: To-Do List Manager)

```java
import java.util.*;

class Task {
    String name;
    int priority;
    public Task(String n, int p) { name = n; priority = p; }
}

public class Day14_ToDoManager {
    public static void main(String[] args) {
        // TODO: Use PriorityQueue<Task> for to-do list
    }
}
```

---

## 🔹 Week 3 – Advanced Collections

### Day 15

**Task: LRU Cache**

```java
import java.util.*;

class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private int capacity;
    public LRUCache(int cap) {
        super(cap, 0.75f, true);
        this.capacity = cap;
    }
    protected boolean removeEldestEntry(Map.Entry<K,V> e) {
        return size() > capacity;
    }
}

public class Day15_LRUCache {
    public static void main(String[] args) {
        // TODO: Test LRU cache
    }
}
```

---

### Day 16

**Task: TreeMap Dictionary**

```java
import java.util.*;

public class Day16_Dictionary {
    public static void main(String[] args) {
        // TODO: Use TreeMap<String,String> as dictionary
    }
}
```

---

### Day 17

**Task: WeakHashMap demo**

```java
import java.util.*;

public class Day17_WeakHashMapDemo {
    public static void main(String[] args) {
        // TODO: Compare HashMap vs WeakHashMap behavior with GC
    }
}
```

---

### Day 18

**Task: IdentityHashMap demo**

```java
import java.util.*;

public class Day18_IdentityHashMapDemo {
    public static void main(String[] args) {
        // TODO: Store same keys in IdentityHashMap and compare with HashMap
    }
}
```

---

### Day 19

**Task: EnumSet + EnumMap**

```java
import java.util.*;

enum Permission { READ, WRITE, EXECUTE }

public class Day19_EnumDemo {
    public static void main(String[] args) {
        // TODO: Use EnumSet and EnumMap for permissions
    }
}
```

---

### Day 20

**Task: ConcurrentHashMap word counter**

```java
import java.util.concurrent.*;

public class Day20_ConcurrentMapDemo {
    public static void main(String[] args) {
        // TODO: Count word frequency using ConcurrentHashMap
    }
}
```

---

### Day 21 (Mini Project: Leaderboard)

```java
import java.util.*;

public class Day21_Leaderboard {
    public static void main(String[] args) {
        // TODO: Use TreeMap<Integer,String> to maintain scores
    }
}
```

---

## 🔹 Week 4 – Real-World + Interview Prep

### Day 22

**Task: Group words by length**

```java
import java.util.*;

public class Day22_GroupWords {
    public static void main(String[] args) {
        // TODO: Use Map<Integer, List<String>> to group words by length
    }
}
```

---

### Day 23

**Task: Check anagrams**

```java
import java.util.*;

public class Day23_AnagramCheck {
    public static void main(String[] args) {
        // TODO: Use HashMap<Character,Integer> to check if two strings are anagrams
    }
}
```

---

### Day 24

**Task: Graph representation**

```java
import java.util.*;

public class Day24_GraphDemo {
    public static void main(String[] args) {
        // TODO: Represent graph as Map<String, List<String>>
        // Implement BFS & DFS
    }
}
```

---

### Day 25

**Task: Phonebook**

```java
import java.util.*;

public class Day25_Phonebook {
    public static void main(String[] args) {
        // TODO: Map<String, List<String>> for person -> phone numbers
    }
}
```

---

### Day 26

**Task: Custom hash function**

```java
import java.util.*;

class Person {
    String name;
    int age;
    // TODO: Override equals() and hashCode()
}

public class Day26_CustomHash {
    public static void main(String[] args) {
        // TODO: Store Person objects in HashSet
    }
}
```

---

### Day 27

**Task: Cycle detection in graph**

```java
import java.util.*;

public class Day27_CycleDetection {
    public static void main(String[] args) {
        // TODO: Detect cycle in directed graph using DFS
    }
}
```

---

### Day 28

**Task: Stream operations**

```java
import java.util.*;
import java.util.stream.*;

public class Day28_StreamDemo {
    public static void main(String[] args) {
        // TODO: Use map, filter, reduce on a List<Integer>
    }
}
```

---

### Day 29

**Task: Interview Qs**

```java
public class Day29_InterviewPrep {
    public static void main(String[] args) {
        // TODO: Write notes + small code demos:
        // - HashMap vs Hashtable
        // - Immutable keys in HashMap
        // - Collision handling
    }
}
```

---

### Day 30 (Final Project: E-commerce Cart)

```java
import java.util.*;

class Product {
    int id;
    String name;
    double price;
    public Product(int id, String n, double p) { this.id=id; this.name=n; this.price=p; }
}

class Order {
    int orderId;
    List<Product> products;
    public Order(int id) { this.orderId=id; this.products=new ArrayList<>(); }
}

public class Day30_EcommerceCart {
    public static void main(String[] args) {
        // TODO: Implement cart with Map<Integer,Product>
        // Maintain order history in List<Order>
        // Apply discounts using sort/streams
    }
}
```

---
