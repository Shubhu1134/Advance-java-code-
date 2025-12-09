# ✅ **50 More Advanced Java + J2EE Interview Questions (With Cross & Short Answers)**

---

# **🔹 Core Java + OOP**

---

## **1️⃣ Why main() is static in Java?**

**Answer:**

- JVM can call it **without object**
- Saves memory
- Entry point must be global

**Cross:** Can main() be overloaded?
✔ Yes, but JVM calls only standard signature.

---

## **2️⃣ Can a constructor be final?**

**Answer:**

- No
- Because constructors cannot be overridden

**Cross:** Can constructor be synchronized?
✔ No, object not created yet.

---

## **3️⃣ What is static binding vs dynamic binding?**

**Answer:**

- Static → compile-time (overloading, static methods)
- Dynamic → runtime (overriding)

**Cross:** Why overriding uses dynamic binding?
✔ Depends on object, not reference.

---

## **4️⃣ What is covariant return type?**

**Answer:**

- Overridden method can return **subtype** of parent method return

**Cross:** Can we use primitive types?
❌ No, only objects.

---

## **5️⃣ Why Java does not support operator overloading?**

**Answer:**

- To avoid complexity
- Maintain simplicity & readability

**Cross:** Which operator is overloaded secretly?
✔ “+” for Strings.

---

## **6️⃣ What is default value of local variables?**

**Answer:**

- No default
- Must be initialized

**Cross:** Why no default?
✔ Prevent accidental usage of garbage data.

---

## **7️⃣ What is immutable class design pattern?**

**Answer:**

- Make class final
- Private fields
- No setters
- Return new object on update

**Cross:** Can an immutable class contain mutable fields?
✔ Yes, but must deep copy.

---

## **8️⃣ Why char[] is preferred for passwords?**

**Answer:**

- Can be cleared after use
- String stays in string pool

**Cross:** Can SecureString solve this?
✔ Not in Java standard.

---

## **9️⃣ What is record class in Java 16?**

**Answer:**

- Immutable data-carrier class
- Auto-generates constructor, equals(), hashCode()

**Cross:** Can records be extended?
❌ No.

---

## **1️⃣0️⃣ Why serialization is dangerous sometimes?**

**Answer:**

- Can break encapsulation
- Security attacks possible
- Fragile versioning

**Cross:** How to disable?
✔ private Object readObject() throws Exception.

---

# **🔹 JVM + Memory Management**

---

## **1️⃣1️⃣ What is Metaspace?**

**Answer:**

- Stores class metadata
- Replaced PermGen (Java 8)

**Cross:** Why PermGen was removed?
✔ To avoid fixed size out-of-memory issues.

---

## **1️⃣2️⃣ What is GC Root?**

**Answer:**

- Starting points for GC to trace live objects
- Examples: local variables, static variables

**Cross:** Can weak references be GC roots?
❌ No.

---

## **1️⃣3️⃣ What is escape analysis?**

**Answer:**

- JIT optimization
- Converts object allocation from heap → stack

**Cross:** Benefit?
✔ Zero GC pressure.

---

## **1️⃣4️⃣ What is stop-the-world event?**

**Answer:**

- All threads paused during GC

**Cross:** Which GC reduces it?
✔ G1, ZGC, Shenandoah.

---

## **1️⃣5️⃣ Types of Garbage Collectors?**

**Answer:**

- Serial
- Parallel
- CMS
- G1
- ZGC
- Shenandoah

**Cross:** Which is default now?
✔ G1 (Java 9+).

---

# **🔹 Threads + Concurrency**

---

## **1️⃣6️⃣ What is thread starvation?**

**Answer:**

- Thread never gets CPU/resources
- Due to unfair locking

**Cross:** Solution?
✔ Fair locking.

---

## **1️⃣7️⃣ What is CAS operation?**

**Answer:**

- Compare-And-Swap
- Atomic hardware-level operation

**Cross:** Which classes use CAS?
✔ Atomic classes.

---

## **1️⃣8️⃣ Why thread pool is used?**

**Answer:**

- Reuse threads
- Faster
- Controlled parallelism

**Cross:** Which largest pool?
✔ ForkJoinPool.

---

## **1️⃣9️⃣ What is ReentrantLock?**

**Answer:**

- Lock that same thread can acquire multiple times

**Cross:** Why it's better than synchronized?
✔ TryLock(), interruptible lock, fairness.

---

## **2️⃣0️⃣ What is Phaser in Java?**

**Answer:**

- Advanced synchronization barrier
- Replaces CyclicBarrier + CountDownLatch

**Cross:** Use-case?
✔ Multi-phase tasks.

---

# **🔹 Collections + Generics**

---

## **2️⃣1️⃣ Why HashSet allows only one null?**

**Answer:**

- HashSet is backed by HashMap
- Null stored at bucket 0

**Cross:** Can TreeSet store null?
❌ No, needs sorting.

---

## **2️⃣2️⃣ What is ConcurrentSkipListMap?**

**Answer:**

- Thread-safe sorted map
- Based on skip-list
- Non-blocking

**Cross:** Alternative?
✔ TreeMap (not thread-safe).

---

## **2️⃣3️⃣ Why PriorityQueue not thread-safe?**

**Answer:**

- Designed for performance
- Not for concurrency

**Cross:** Thread-safe alternative?
✔ PriorityBlockingQueue.

---

## **2️⃣4️⃣ What is type erasure?**

**Answer:**

- Generics removed at runtime
- Backward compatibility

**Cross:** Why generic array creation forbidden?
❌ Type erasure breaks runtime type safety.

---

## **2️⃣5️⃣ Why Hashtable is obsolete?**

**Answer:**

- Fully synchronized (slow)
- Allows no nulls
- Replaced by ConcurrentHashMap

**Cross:** Any use-case left?
✔ Legacy code only.

---

# **🔹 Exception Handling**

---

## **2️⃣6️⃣ What is suppressed exception?**

**Answer:**

- Exception hidden by try-with-resources

**Cross:** Where stored?
✔ e.getSuppressed()

---

## **2️⃣7️⃣ Why catch block must be ordered?**

**Answer:**

- More specific → before general
- Otherwise unreachable

**Cross:** What happens if reversed?
✔ Compile-time error.

---

## **2️⃣8️⃣ What is custom exception?**

**Answer:**

- User-defined
- Extends Exception/RuntimeException

**Cross:** When to extend Exception?
✔ When it's a recoverable case.

---

## **2️⃣9️⃣ Can we throw checked exception without throws?**

**Answer:**

- Yes using **sneaky throw** (Lombok / reflection trick)

**Cross:** Should we do this?
❌ No, bad design.

---

## **3️⃣0️⃣ What is NoClassDefFoundError?**

**Answer:**

- Class present during compile but missing during runtime

**Cross:** Difference with ClassNotFoundException?
✔ That one occurs during dynamic loading.

---

# **🔹 J2EE + Web + Spring**

---

## **3️⃣1️⃣ What is Filter in Servlet?**

**Answer:**

- Pre/post request processing
- Logging, auth, encoding

**Cross:** Runs before or after servlet?
✔ Both.

---

## **3️⃣2️⃣ What is Session vs Cookie?**

**Answer:**

- Session → server-side
- Cookie → client-side

**Cross:** Which is safer?
✔ Session.

---

## **3️⃣3️⃣ What is RequestDispatcher?**

**Answer:**

- Forward or include other resources

**Cross:** Difference between redirect vs forward?
✔ Redirect = new request
✔ Forward = same request

---

## **3️⃣4️⃣ Explain JSP lifecycle.**

**Answer:**

- Translation → Compilation → Loading → Instantiation → Initialization → Execution → Destroy

**Cross:** JSP converted into what?
✔ Servlet.

---

## **3️⃣5️⃣ What is EntityManager in JPA?**

**Answer:**

- Interface to perform DB operations
- Manages persistence context

**Cross:** Similar to Session in Hibernate?
✔ Yes.

---

## **3️⃣6️⃣ Why Spring uses proxies?**

**Answer:**

- To apply AOP
- Wrap beans with extra behavior

**Cross:** Which two proxies used?
✔ JDK dynamic proxy
✔ CGLIB

---

## **3️⃣7️⃣ What is @Autowired?**

**Answer:**

- Injects dependency
- Based on type

**Cross:** Why constructor injection preferred?
✔ Ensures immutability.

---

## **3️⃣8️⃣ What is Bean Scope?**

**Answer:**

- Singleton, prototype
- Request, session, application

**Cross:** Default scope?
✔ Singleton.

---

## **3️⃣9️⃣ What is REST idempotency?**

**Answer:**

- Same request → same effect
- GET, PUT, DELETE are idempotent

**Cross:** Why POST not idempotent?
✔ Creates new resources.

---

## **4️⃣0️⃣ What is caching in Spring Boot?**

**Answer:**

- @Cacheable, @CachePut, @CacheEvict
- Speeds repeated calls

**Cross:** Default cache?
✔ ConcurrentMapCache.

---

# **🔹 Hibernate + JPA**

---

## **4️⃣1️⃣ What is session.flush()?**

**Answer:**

- Synchronizes persistence context to DB

**Cross:** How is it different from commit?
✔ Commit finalizes transaction.

---

## **4️⃣2️⃣ What is optimistic locking?**

**Answer:**

- Uses version numbers
- Prevents concurrent updates

**Cross:** Pessimistic locking?
✔ Uses DB locks.

---

## **4️⃣3️⃣ What is N+1 select problem?**

**Answer:**

- One select for parent + N selects for children

**Cross:** Solution?
✔ JOIN FETCH.

---

## **4️⃣4️⃣ Why proxies used in lazy loading?**

**Answer:**

- To load data only when needed
- Saves performance

**Cross:** When does lazy loading fail?
✔ Outside transaction.

---

## **4️⃣5️⃣ What is Cascade?**

**Answer:**

- Auto-save/update/delete child entities

**Cross:** Which cascade is dangerous?
✔ CascadeType.ALL on ManyToMany.

---

# **🔹 Microservices + Design + Architecture**

---

## **4️⃣6️⃣ What is API Gateway?**

**Answer:**

- Entry point for microservices
- Handles routing, security, throttling

**Cross:** Example?
✔ Zuul, Spring Cloud Gateway.

---

## **4️⃣7️⃣ What is Circuit Breaker?**

**Answer:**

- Stops calling failing service
- Protects system from cascading failure

**Cross:** Example?
✔ Resilience4j.

---

## **4️⃣8️⃣ What is Load Balancing?**

**Answer:**

- Distributes traffic among servers
- Improves availability

**Cross:** Types?
✔ Round-robin, least connections.

---

## **4️⃣9️⃣ What is idempotent API design?**

**Answer:**

- Repeatable operations
- Prevent duplicate processing

**Cross:** Example?
✔ Payment verification API.

---

## **5️⃣0️⃣ What is containerization (Docker)?**

**Answer:**

- Pack app + dependencies
- Portable environment

**Cross:** Difference between VM & container?
✔ Containers share OS kernel.

---
