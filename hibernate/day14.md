
---

## 🗓️ **Day 14 – Transactions, Validation & Exception Handling**

### 🎯 Goal

By the end of today, you’ll be able to:

* Control database operations safely with **transactions**
* Add **input validation** for clean data
* Implement **centralized exception handling**
* Write code that never corrupts the database even on failure

---

## 🧱 **1️⃣ Transactions – The Safety Net**

A **transaction** is a unit of work that must either **fully succeed** or **fully fail**.

### Example

```java
@Service
public class BankService {

    @Autowired
    private AccountRepository repo;

    @Transactional
    public void transferMoney(Long fromId, Long toId, double amount) {
        Account from = repo.findById(fromId).orElseThrow();
        Account to = repo.findById(toId).orElseThrow();

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        repo.save(from);
        repo.save(to);
    }
}
```

💡 If an exception occurs anywhere inside the method, **Spring rolls back** all changes automatically — no partial updates.

---

### ⚙️ Transactional Options

```java
@Transactional(
    propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED,
    rollbackFor = Exception.class
)
```

| Option                          | Meaning                                      |
| ------------------------------- | -------------------------------------------- |
| `Propagation.REQUIRED`          | Join existing transaction or start a new one |
| `Isolation.READ_COMMITTED`      | Prevents dirty reads                         |
| `rollbackFor = Exception.class` | Rollback even for checked exceptions         |

---

## 🧠 **2️⃣ Common Mistakes to Avoid**

❌ Calling `@Transactional` methods **inside the same class** (proxy won’t work)
✅ Keep `@Transactional` in **service layer**

❌ Catching exceptions without rethrowing (it cancels rollback)
✅ Let Spring handle rollback automatically

---

## 🧩 **3️⃣ Validation – Keeping Data Clean**

Add JSR-380 annotations (Jakarta Validation) to your entity or DTO.

### Example: Employee Validation

```java
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @Min(value = 10000, message = "Salary must be >= 10000")
    private double salary;
}
```

### Controller

```java
@PostMapping
public Employee addEmployee(@Valid @RequestBody Employee emp) {
    return repo.save(emp);
}
```

💡 Spring automatically validates the request before it hits your business logic.
If invalid → `MethodArgumentNotValidException` is thrown.

---

## ⚡ **4️⃣ Global Exception Handling**

Use `@ControllerAdvice` to handle exceptions in one place.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
            errors.put(err.getField(), err.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Something went wrong: " + ex.getMessage());
    }
}
```

Now, *all errors return clean JSON responses* instead of ugly stack traces.

---

## 🧾 **5️⃣ Custom Exceptions**

```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

Throw it anywhere:

```java
Employee emp = repo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
```

---

## 🧠 **6️⃣ Validation in Nested Entities**

If your entity contains another object (e.g., `Address` inside `Employee`),
annotate it with `@Valid` to validate inner fields automatically.

```java
public class Employee {
    @Valid
    private Address address;
}
```

---

## 🧩 **7️⃣ Logging Errors**

Instead of `System.out.println()`, always use:

```java
private static final Logger logger = LoggerFactory.getLogger(YourClass.class);
```

Example:

```java
logger.error("Error transferring money: {}", ex.getMessage());
```

---

## ✅ **8️⃣ Summary**

| Feature                  | You Learned                       |
| ------------------------ | --------------------------------- |
| **Transactions**         | Atomic operations with rollback   |
| **@Transactional**       | Automatic transaction management  |
| **Validation**           | Entity & request-level validation |
| **Global Error Handler** | Centralized exception control     |
| **Custom Exceptions**    | Clean business error design       |
| **Logging**              | Professional debugging            |

---

## 💡 **9️⃣ Mini-Challenge**

Create a **Product-Order** system:

1. `OrderService.placeOrder()` deducts stock using `@Transactional`.
2. Add validation: product price > 0, quantity > 0.
3. Handle `OutOfStockException` globally.

---
