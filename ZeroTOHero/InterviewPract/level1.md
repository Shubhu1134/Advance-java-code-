# ✅ **20 Advanced Java + J2EE Questions + Cross-Questions + Short Answers**

---

## **1️⃣ What is the difference between final, finally & finalize?**

**Short answer**

- **final** → keyword (variable/method/class locking)
- **finally** → block (executes after try/catch)
- **finalize()** → called by GC before object removal

**Cross question**
➡ Why is finalize() deprecated in Java 9?
**Ans:** Unpredictable, slow, no guaranteed execution.

---

## **2️⃣ Why does Java not support multiple inheritance?**

**Short answer**

- To avoid **diamond problem**
- To maintain **simplicity & unambiguous method resolution**

**Cross question**
➡ Then how interfaces allow it?
**Ans:** Because they don’t carry state (before Java 8), so no ambiguity.

---

## **3️⃣ What is the difference between checked & unchecked exceptions?**

**Short answer**

- Checked → must handle at compile time
- Unchecked → runtime, JVM handles

**Cross question**
➡ Why RuntimeException is unchecked?
**Ans:** Because handling them at compile time slows development.

---

## **4️⃣ What is immutability in Java?**

**Short answer**

- Object state cannot change after creation
- Example: String, wrapper classes

**Cross question**
➡ Why wrapper classes are immutable?
**Ans:** Thread-safe, caching, easy to use in collections.

---

## **5️⃣ What is functional interface?**

**Short answer**

- Interface with only **one abstract method**
- Used in lambda expressions

**Cross question**
➡ Can functional interface have default methods?
**Ans:** Yes, only _one abstract_ required.

---

## **6️⃣ What is Reflection API?**

**Short answer**

- Ability to inspect or modify classes/fields/methods at runtime

**Cross question**
➡ Why reflection is slow?
**Ans:** Breaks optimizations + bypasses access checks.

---

## **7️⃣ What is volatile keyword?**

**Short answer**

- Ensures visibility of shared variable changes across threads
- Prevents caching inconsistencies

**Cross question**
➡ Is volatile thread-safe?
**Ans:** No, it prevents visibility issues but not atomicity.

---

## **8️⃣ What is the difference between synchronized & concurrent collections?**

**Short answer**

- synchronized → locks whole collection
- concurrent → granular locking, high performance

**Cross question**
➡ Why ConcurrentHashMap disallows null?
**Ans:** To avoid confusion in concurrent operations.

---

## **9️⃣ What is the difference between List, Set, and Map?**

**Short answer**

- List → ordered, duplicates allowed
- Set → no duplicates
- Map → key-value pairs

**Cross question**
➡ Which one uses hashing?
**Ans:** HashSet, HashMap.

---

## **1️⃣0️⃣ What is the difference between deep copy & shallow copy?**

**Short answer**

- Shallow → copies references only
- Deep → copies full object graph

**Cross question**
➡ Why clone() is considered broken?
**Ans:** Complicated, inconsistent across classes.

---

## **1️⃣1️⃣ What is fail-fast iterator?**

**Short answer**

- Throws **ConcurrentModificationException** when structure modified

**Cross question**
➡ Why fail-safe doesn’t throw error?
**Ans:** Works on a cloned copy.

---

## **1️⃣2️⃣ What is JVM Just-In-Time (JIT) compiler?**

**Short answer**

- Converts hot bytecode to machine code for speed
- Runs at runtime

**Cross question**
➡ What is hotspot detection?
**Ans:** JVM identifies frequently used code paths.

---

## **1️⃣3️⃣ Why StringBuilder is faster than StringBuffer?**

**Short answer**

- No synchronization
- Reduced overhead

**Cross question**
➡ In which case StringBuffer can outperform?
**Ans:** Multi-threaded append operations.

---

## **1️⃣4️⃣ Difference between abstract class & interface?**

**Short answer**

- Abstract class → can have state
- Interface → pure abstraction

**Cross question**
➡ Why abstract class can have constructor?
**Ans:** To initialize common fields.

---

## **1️⃣5️⃣ What is JDBC Batch Processing?**

**Short answer**

- Send multiple SQL statements to DB at once
- Faster insert/update operations

**Cross question**
➡ What is addBatch()?
**Ans:** Adds SQL to batch queue.

---

## **1️⃣6️⃣ What is ORM? Why Hibernate?**

**Short answer**

- Converts Java objects ↔ tables
- Reduces boilerplate SQL
- Provides caching, lazy loading, transactions

**Cross question**
➡ What is n+1 problem?
**Ans:** Excess queries, solved by fetch joins.

---

## **1️⃣7️⃣ What is DispatcherServlet?**

**Short answer**

- Front controller in Spring MVC
- Routes requests to controllers

**Cross question**
➡ How it selects handler method?
**Ans:** HandlerMapping + MethodResolver.

---

## **1️⃣8️⃣ What is REST vs SOAP?**

**Short answer**

- REST → stateless, JSON, lightweight
- SOAP → XML-heavy, secure, enterprise

**Cross question**
➡ Why REST is faster?
**Ans:** No overhead, uses HTTP directly.

---

## **1️⃣9️⃣ What are filters & interceptors?**

**Short answer**

- Filter → Java EE, pre/post request
- Interceptor → Spring, controller-level processing

**Cross question**
➡ Which runs first?
**Ans:** Filter → then Interceptor → then Controller.

---

## **2️⃣0️⃣ What is @Transactional in Spring?**

**Short answer**

- Manages transactions automatically
- Commit/rollback based on success/failure

**Cross question**
➡ Why it doesn’t work on private methods?
**Ans:** Proxies cannot wrap private calls.

---
