**Day 3 Java Interview Prep Plan – Multithreading Basics (Theory + Hands-on)**.

---

# 📌 **Day 3 – Multithreading & Concurrency**

---

## **🔹 Theory (Interview-Focused)**

### 1. **What is Multithreading?**

- Multithreading = Executing multiple tasks (threads) simultaneously in a single process.
- Each thread is a **lightweight unit of a process**.

👉 **Use cases**: Server apps, gaming, banking transactions, background tasks.

---

### 2. **Thread Lifecycle**

1. New
2. Runnable
3. Running
4. Waiting/Blocked/Sleep
5. Terminated

---

### 3. **Ways to Create Thread**

- Extend `Thread` class
- Implement `Runnable` interface
- Implement `Callable` + use `Future`

---

### 4. **Thread Methods**

- `start()` → begins execution (calls `run()` internally).
- `sleep(ms)` → pause thread.
- `join()` → wait until thread finishes.
- `yield()` → hint to scheduler.
- `setDaemon(true)` → background thread.

---

### 5. **Synchronization**

👉 Needed when **multiple threads share resources**.

- `synchronized` block or method prevents race conditions.
- `ReentrantLock` (advanced alternative).

---

### 6. **Volatile vs Synchronization**

- `volatile` → ensures **visibility** of variable across threads.
- `synchronized` → ensures **mutual exclusion** + visibility.

---

### 7. **Deadlock**

When two threads wait forever for each other’s lock.

---

## **🔹 Hands-on Coding Practice**

---

### **1. Create a Thread (Runnable vs Thread)**

```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        t1.start();

        Thread t2 = new Thread(() -> System.out.println("Lambda Thread!"));
        t2.start();
    }
}
```

---

### **2. Sleep & Join Example**

```java
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for(int i=1;i<=3;i++){
                System.out.println("Thread 1: " + i);
                try { Thread.sleep(1000); } catch(Exception e){}
            }
        });

        Thread t2 = new Thread(() -> System.out.println("Thread 2 running"));

        t1.start();
        t1.join(); // waits for t1
        t2.start();
    }
}
```

---

### **3. Synchronized Example**

```java
class Counter {
    private int count = 0;
    public synchronized void increment() { count++; }
    public int getCount() { return count; }
}

public class SyncDemo {
    public static void main(String[] args) throws Exception {
        Counter c = new Counter();

        Thread t1 = new Thread(() -> { for(int i=0;i<1000;i++) c.increment(); });
        Thread t2 = new Thread(() -> { for(int i=0;i<1000;i++) c.increment(); });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Final Count = " + c.getCount()); // 2000
    }
}
```

---

### **4. Deadlock Example**

```java
class A {}
class B {}

public class DeadlockDemo {
    public static void main(String[] args) {
        A a = new A(); B b = new B();

        Thread t1 = new Thread(() -> {
            synchronized(a){
                System.out.println("Thread 1 locked A");
                try { Thread.sleep(100); } catch(Exception e){}
                synchronized(b){ System.out.println("Thread 1 locked B"); }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized(b){
                System.out.println("Thread 2 locked B");
                try { Thread.sleep(100); } catch(Exception e){}
                synchronized(a){ System.out.println("Thread 2 locked A"); }
            }
        });

        t1.start(); t2.start();
    }
}
```

👉 This will cause **deadlock** sometimes.

---

### **5. Volatile Keyword Example**

```java
class SharedData {
    volatile boolean flag = true;
}

public class VolatileDemo {
    public static void main(String[] args) {
        SharedData data = new SharedData();

        new Thread(() -> {
            while(data.flag){
                // busy wait
            }
            System.out.println("Stopped!");
        }).start();

        try { Thread.sleep(1000); } catch(Exception e){}
        data.flag = false; // without volatile, thread may not stop
    }
}
```

---

**advanced multithreading concepts** —

---

# 📌 **Day 3 – Advanced Multithreading (ExecutorService, Callable, ThreadPool)**

---

### **1. ExecutorService**

- Instead of manually creating threads, we use **thread pools**.
- Advantages:

  - Reuse threads → better performance.
  - Manage number of concurrent threads easily.
  - Avoid thread creation overhead.

**Example:**

```java
import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i=1; i<=5; i++){
            int task = i;
            executor.submit(() -> System.out.println("Task " + task + " executed by " + Thread.currentThread().getName()));
        }

        executor.shutdown(); // stops accepting new tasks
    }
}
```

💡 **Tip:** `shutdown()` vs `shutdownNow()` — first lets running tasks finish, second attempts to stop immediately.

---

### **2. Callable & Future**

- `Callable` → Like Runnable but **can return a value** and throw exceptions.
- `Future` → Holds the result of a callable task.

**Example:**

```java
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            Thread.sleep(1000);
            return 123;
        };

        Future<Integer> future = executor.submit(task);
        System.out.println("Result from Callable: " + future.get()); // waits if not done

        executor.shutdown();
    }
}
```

💡 **Interview Twist:**

- Difference between `Runnable` and `Callable`?

  - `Runnable` → no return, no checked exception.
  - `Callable` → returns value, can throw checked exception.

---

### **3. ThreadPool Types**

1. **FixedThreadPool** → Fixed number of threads.
2. **CachedThreadPool** → Reuses idle threads, creates new if none idle.
3. **ScheduledThreadPool** → Executes tasks after delay or periodically.

**Example – ScheduledThreadPool**

```java
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

scheduler.scheduleAtFixedRate(() -> System.out.println("Scheduled Task"), 0, 2, TimeUnit.SECONDS);

// stop after 10 seconds
scheduler.schedule(() -> scheduler.shutdown(), 10, TimeUnit.SECONDS);
```

💡 **Tip:** Always shutdown scheduler to avoid JVM hanging.

---

### **4. Thread-safe Collections**

- `Collections.synchronizedList()`
- `CopyOnWriteArrayList` → Ideal for more reads than writes.
- `ConcurrentHashMap` → High-performance map in concurrent scenarios.

**Example:**

```java
import java.util.concurrent.*;
import java.util.*;

public class ConcurrentDemo {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1); list.add(2);

        Runnable task = () -> list.add(3);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(task);
        executor.submit(task);

        executor.shutdown();
        System.out.println(list); // safe from ConcurrentModificationException
    }
}
```

---

### **5. Atomic Variables**

- `AtomicInteger`, `AtomicBoolean` → For lock-free thread-safe operations.

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);

        Runnable task = () -> {
            for(int i=0;i<1000;i++) count.incrementAndGet();
        };

        Thread t1 = new Thread(task), t2 = new Thread(task);
        t1.start(); t2.start();

        try { t1.join(); t2.join(); } catch(Exception e){}

        System.out.println("Atomic count: " + count); // always 2000
    }
}
```

---

### **6. Deadlock Avoidance (Tips)**

- Lock ordering → always acquire locks in same order.
- Use `tryLock(timeout)` → avoid waiting forever.
- Prefer **higher-level concurrency utilities** (`ExecutorService`, `ConcurrentHashMap`) over manual `synchronized` when possible.

---

### ✅ **Day 3 Interview Tips**

1. Always clarify thread-safety in answers.
2. Show knowledge of **modern concurrency utilities** (`ExecutorService`, `Atomic`, `Concurrent` collections).
3. Mention **pros/cons** of `synchronized` vs `Lock` vs `Atomic`.
4. Real-life examples: Web server handles requests concurrently, banking transactions, background processing.

---

## **Day 3 – Practice Set**

# 📌 **Day 3 – Practice Questions (Advanced Multithreading)**

---

### **ExecutorService / Thread Pools**

1. What is the difference between `Executor`, `ExecutorService`, and `ScheduledExecutorService`?
2. How do you properly shut down a thread pool? Explain difference between `shutdown()` and `shutdownNow()`.
3. When would you use `newCachedThreadPool()` vs `newFixedThreadPool(int n)`?
4. Can a thread pool grow beyond its maximum size? What happens when all threads are busy?

---

### **Callable & Future**

5. Difference between `Runnable` and `Callable`?
6. What happens if you call `future.get()` and the task has not completed?
7. How do you cancel a running `Future` task? What does `future.cancel(true)` do?
8. Can `Callable` throw checked exceptions? How is it different from Runnable in this context?

---

### **ScheduledThreadPool**

9. Difference between `schedule()`, `scheduleAtFixedRate()`, and `scheduleWithFixedDelay()`?
10. What happens if a task in `scheduleAtFixedRate()` takes longer than its period?
11. How can you prevent a scheduled task from keeping JVM alive after main method finishes?

---

### **Thread-Safe Collections**

12. Difference between `Collections.synchronizedList()` and `CopyOnWriteArrayList`?
13. When should you prefer `ConcurrentHashMap` over `HashMap` with synchronized block?
14. What is a `ConcurrentModificationException`? How can you avoid it?

---

### **Atomic Variables**

15. Difference between `AtomicInteger` and `volatile int`?
16. Can atomic operations guarantee thread-safety for complex operations like `if-check-then-act`?
17. Show how to use `getAndIncrement()` in a multithreaded scenario.

---

### **Locks & Deadlock**

18. Difference between `synchronized` and `ReentrantLock`?
19. How can `tryLock(timeout)` help in avoiding deadlock?
20. What are some strategies to prevent deadlocks in multi-threaded applications?

---

### **Miscellaneous / Interview Twists**

21. Can you submit a `Runnable` to `ExecutorService` and still get a result? How?
22. Explain thread starvation and thread leakage with examples.
23. Explain the difference between `Thread.join()` and `Future.get()`.

---

# 📌 **Day 3 – Model Answers (Short)**

---

### **ExecutorService / Thread Pools**

- `Executor` → executes tasks, no lifecycle control.

- `ExecutorService` → can shutdown, await termination, submit tasks.

- `ScheduledExecutorService` → schedule tasks with delay or periodic execution.

- `shutdown()` → no new tasks, waits running tasks.

- `shutdownNow()` → tries to stop all tasks immediately (may interrupt running threads).

---

### **Callable & Future**

- `Runnable` → no return, no checked exception.
- `Callable` → returns value, can throw checked exception.
- `future.get()` → blocks until task completes.
- `future.cancel(true)` → attempts to interrupt task thread.

---

### **ScheduledThreadPool**

- `schedule()` → run once after delay.

- `scheduleAtFixedRate()` → run at fixed period, regardless of task duration.

- `scheduleWithFixedDelay()` → run after fixed delay from previous task completion.

- Long-running task in `scheduleAtFixedRate()` → next run may be delayed or tasks may overlap (depends on implementation).

---

### **Thread-Safe Collections**

- `Collections.synchronizedList()` → locks entire list on every operation.
- `CopyOnWriteArrayList` → creates copy on write, better for many reads, few writes.
- `ConcurrentHashMap` → high concurrency, no full lock like `synchronizedMap`.

---

### **Atomic Variables**

- `volatile` → guarantees visibility but not atomicity.
- `AtomicInteger` → atomic read-modify-write operations.
- Complex operation (`check-then-act`) still requires `synchronized` or `compareAndSet()`.

---

### **Locks & Deadlock**

- `synchronized` → intrinsic monitor lock.
- `ReentrantLock` → explicit lock, can tryLock(), interruptible.
- `tryLock(timeout)` → prevents indefinite waiting → reduces deadlock.
- Prevent deadlocks → lock ordering, timeout, avoid nested locks, use higher-level constructs.

---

### **Miscellaneous**

- Submit `Runnable` → `executor.submit(runnable, resultObject)` → returns `Future`.
- `Thread.join()` → wait for thread termination.
- `Future.get()` → wait for task result, may throw exception.

---

## **Day 3 – Advanced Multithreading Cheat Sheet** for you, designed for **fast revision before interviews**

# 📌 **Day 3 – Advanced Multithreading Cheat Sheet**

---

## **1. Thread Pools – ExecutorService**

**Types of Executors:**

| Type                        | Description             | Use Case                  |
| --------------------------- | ----------------------- | ------------------------- |
| `newFixedThreadPool(n)`     | Fixed number of threads | Predictable load          |
| `newCachedThreadPool()`     | Dynamically grows       | Short-lived tasks         |
| `newSingleThreadExecutor()` | Single thread           | Sequential execution      |
| `newScheduledThreadPool(n)` | Scheduled tasks         | Periodic or delayed tasks |

**Lifecycle methods:**

- `shutdown()` → stops new tasks, waits for running tasks.
- `shutdownNow()` → tries to stop all tasks immediately (interrupts threads).

💡 **Tip:** Always shutdown thread pools to avoid thread leakage.

**Diagram:**

```
Task Queue → ExecutorService → Thread Pool → Threads execute tasks
```

---

## **2. Runnable vs Callable**

| Feature      | Runnable                         | Callable            |
| ------------ | -------------------------------- | ------------------- |
| Return Value | None                             | Yes (generics)      |
| Exception    | Cannot throw checked             | Can throw checked   |
| submit()     | Returns `Future<?>` if submitted | Returns `Future<V>` |

```java
Callable<Integer> task = () -> 10;
Future<Integer> future = executor.submit(task);
Integer result = future.get(); // blocks until task completes
```

💡 **Tip:** Use `Callable` when you need a result or exception handling.

---

## **3. Scheduled Executors**

| Method                     | Behavior                                        |
| -------------------------- | ----------------------------------------------- |
| `schedule()`               | Run once after delay                            |
| `scheduleAtFixedRate()`    | Run periodically (fixed period, may overlap)    |
| `scheduleWithFixedDelay()` | Run periodically after previous task completion |

**Example:**

```java
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
scheduler.scheduleAtFixedRate(() -> System.out.println("Task!"), 0, 2, TimeUnit.SECONDS);
```

💡 **Twist:** If task runs longer than period → next run may overlap.

---

## **4. Thread-Safe Collections**

| Collection                       | Behavior          | Use Case               |
| -------------------------------- | ----------------- | ---------------------- |
| `Collections.synchronizedList()` | Locks entire list | Few reads/writes       |
| `CopyOnWriteArrayList`           | Copy-on-write     | Many reads, few writes |
| `ConcurrentHashMap`              | Segment locks     | High concurrency maps  |

💡 **Tip:** Use concurrent collections instead of `synchronizedMap` for high performance.

---

## **5. Atomic Variables**

- `volatile` → ensures visibility only, not atomicity.
- `AtomicInteger` → atomic read-modify-write, thread-safe.

```java
AtomicInteger counter = new AtomicInteger(0);
counter.getAndIncrement(); // atomic increment
```

💡 **Trick:** For complex operations, use `compareAndSet()` or `synchronized`.

---

## **6. Locks & Deadlocks**

**Synchronized vs ReentrantLock**

| Feature       | Synchronized | ReentrantLock |
| ------------- | ------------ | ------------- |
| Explicit Lock | ❌           | ✅            |
| tryLock()     | ❌           | ✅            |
| Interruptible | ❌           | ✅            |
| Fairness      | ❌           | ✅            |

**Deadlock prevention:**

1. Lock ordering
2. Use `tryLock(timeout)`
3. Avoid nested locks
4. Use higher-level constructs (`Semaphore`, `ExecutorService`)

**Deadlock Diagram:**

```
Thread A → Lock 1 → waiting Lock 2
Thread B → Lock 2 → waiting Lock 1
```

---

## **7. Miscellaneous / Interview Tricks**

- Submit `Runnable` with result:

```java
Future<String> f = executor.submit(() -> System.out.println("Run"), "Done");
String result = f.get(); // returns "Done"
```

- `Thread.join()` → wait for thread completion
- `Future.get()` → wait for result, can throw exceptions

**Thread Starvation:** High-priority threads dominate CPU → low-priority threads wait indefinitely.
**Thread Leakage:** Threads not shutdown → JVM resource leak.

---

### ✅ **Fast Recall Tips**

- ExecutorService → lifecycle + thread pool types
- Callable → returns result, can throw exceptions
- ScheduledExecutor → `scheduleAtFixedRate()` vs `scheduleWithFixedDelay()`
- Atomic → `compareAndSet()`, volatile ≠ atomic
- Deadlock → lock order + tryLock + avoid nested locks
- Always shutdown pools → no thread leaks

---
