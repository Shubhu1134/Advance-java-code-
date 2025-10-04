**20-day coding exercise sheet**

---

# **Java Collections – 20-Day Coding Exercise Plan**

---

## **Phase 1: Fundamentals & Core Concepts (Days 1–7)**

### **Day 1 – Introduction to Collections**

1. Create an `ArrayList` of integers and print all elements.
2. Create a `HashMap` of student names and marks. Print the map.
3. Add, remove, and update elements in the `ArrayList`.
4. Iterate through `HashMap` using `entrySet()` and print key-value pairs.
5. Convert an array of integers to an `ArrayList`.

---

### **Day 2 – List Interface (ArrayList)**

1. Add 10 names to `ArrayList` and print them in reverse.
2. Insert a new element at index 2.
3. Remove element by index and by value.
4. Find the first and last occurrence of a particular element.
5. Create a sublist from an existing `ArrayList`.

---

### **Day 3 – List Interface (LinkedList)**

1. Implement a queue using `LinkedList`.
2. Add elements at first and last positions.
3. Remove first and last elements.
4. Iterate using `ListIterator` and print in reverse.
5. Merge two `LinkedList`s into one.

---

### **Day 4 – Set Interface**

1. Create a `HashSet` and add duplicate elements. Observe behavior.
2. Convert `HashSet` to `ArrayList`.
3. Create a `TreeSet` of integers and add random numbers.
4. Create a `LinkedHashSet` and maintain insertion order.
5. Remove all elements less than a given value from a `TreeSet`.

---

### **Day 5 – Queue Interface**

1. Implement `Queue` using `PriorityQueue`.
2. Add 10 integers and remove the smallest element.
3. Implement `Deque` using `ArrayDeque`.
4. Insert elements at both ends of `Deque`.
5. Iterate `Deque` from front to back.

---

### **Day 6 – Map Interface**

1. Create a `HashMap` of 5 countries and capitals. Print keys and values.
2. Update values for a specific key.
3. Remove a key-value pair.
4. Merge two `HashMap`s.
5. Count frequency of each character in a string using `HashMap`.

---

### **Day 7 – Iterators**

1. Use `Iterator` to traverse a `HashSet`.
2. Use `ListIterator` to traverse a `LinkedList` forward and backward.
3. Remove even numbers from a `List` using `Iterator`.
4. Iterate `HashMap` using `keySet()` and `values()`.
5. Iterate using Java 8 `forEach` method with lambda.

---

## **Phase 2: Intermediate & Advanced Collections (Days 8–14)**

### **Day 8 – Comparable & Comparator**

1. Create `Employee` class with `name` and `salary`.
2. Sort a `List<Employee>` by salary (Comparable).
3. Sort a `List<Employee>` by name using Comparator.
4. Sort `TreeSet<Employee>` using Comparator.
5. Reverse sorting of `Employee` list using Comparator.

---

### **Day 9 – Generics**

1. Implement a generic `Box<T>` class.
2. Create a generic `Stack<T>` using `ArrayList`.
3. Implement `printAll` method accepting `List<?>`.
4. Create method `addNumbers(List<? super Integer>)`.
5. Create method `sumNumbers(List<? extends Number>)`.

---

### **Day 10 – Concurrent Collections**

1. Use `Collections.synchronizedList()` and test multi-threaded modification.
2. Use `ConcurrentHashMap` with multiple threads adding elements.
3. Implement producer-consumer using `BlockingQueue`.
4. Compare `CopyOnWriteArrayList` vs `ArrayList` in multi-threaded environment.
5. Iterate `ConcurrentHashMap` safely while modifying.

---

### **Day 11 – Stack & Vector**

1. Use `Stack` to reverse a string.
2. Implement expression evaluation using `Stack`.
3. Push and pop elements in `Vector`.
4. Compare performance of `Vector` vs `ArrayList`.
5. Implement undo-redo simulation using `Stack`.

---

### **Day 12 – Advanced Map**

1. Implement LRU cache using `LinkedHashMap`.
2. Use `WeakHashMap` and observe behavior with garbage collection.
3. Use `IdentityHashMap` to store object keys.
4. Merge multiple maps into one.
5. Count occurrence of words in a paragraph using `TreeMap`.

---

### **Day 13 – Sorting & Searching**

1. Sort `ArrayList` of integers using `Collections.sort()`.
2. Sort `ArrayList` of strings ignoring case.
3. Reverse a list using `Collections.reverse()`.
4. Binary search in a sorted list.
5. Sort `TreeMap` keys in descending order.

---

### **Day 14 – Utility Classes**

1. Shuffle an `ArrayList` of 52 cards.
2. Copy one `List` into another.
3. Fill an `ArrayList` with a default value.
4. Find max and min in a `List` using `Collections`.
5. Rotate a list by 3 positions.

---

## **Phase 3: Mastery & Real-World Usage (Days 15–20)**

### **Day 15 – Memory & Performance**

1. Compare memory usage of `ArrayList` vs `LinkedList` with 1M elements.
2. Measure time to add 1M elements to `ArrayList` and `LinkedList`.
3. Remove 500k elements and compare time.
4. Iterate `ArrayList` using for loop vs iterator, compare speed.
5. Benchmark `HashSet` vs `TreeSet` insertion time.

---

### **Day 16 – Custom Data Structures**

1. Implement `Stack` using `LinkedList`.
2. Implement `Queue` using two stacks.
3. Implement LRU cache using `Deque` + `HashMap`.
4. Implement `CircularQueue` using `ArrayList`.
5. Implement `PriorityQueue` manually using `ArrayList` + sorting.

---

### **Day 17 – Streams**

1. Filter employees with salary > 50k using streams.
2. Map employee names to uppercase using streams.
3. Count employees in each department using `Collectors.groupingBy`.
4. Sum all salaries using `reduce`.
5. Convert a `List<String>` to a comma-separated string using `Collectors.joining`.

---

### **Day 18 – Interview Problems**

1. Find duplicates in an array using `Set`.
2. Find intersection of two arrays.
3. Top 3 frequent elements in an array.
4. Sliding window max of size k.
5. Rearrange array so positives and negatives alternate.

---

### **Day 19 – Real-World Applications**

1. Implement leaderboard using `TreeMap`.
2. Task scheduler with `PriorityQueue`.
3. Simulate social network friends using `HashMap<String, List<String>>`.
4. Cache frequently accessed items with `LinkedHashMap`.
5. Implement URL hit counter using `ConcurrentHashMap`.

---

### **Day 20 – Revision & Mock Test**

1. Solve 5 mixed collection problems in 1 hour.
2. Implement a mini project using multiple collections.
3. Redesign LRU cache with threadsafe operations.
4. Optimize `ArrayList` vs `LinkedList` operations.
5. Take a timed mock test of 20–30 collection-based problems.

---
