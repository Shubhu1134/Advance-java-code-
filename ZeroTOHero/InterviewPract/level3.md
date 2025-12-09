# ✅ **Additional 50 Java/J2EE Questions (Short & Sharp Answers)**

---

## **51. What is a ClassLoader in Java?**

- Loads classes into JVM.
- Types: Bootstrap, Extension, Application, Custom.

## **52. What is the difference between ClassNotFoundException & NoClassDefFoundError?**

- ClassNotFoundException → class missing at runtime (dynamic loading).
- NoClassDefFoundError → class was available at compile time but missing at runtime.

## **53. What is the use of `transient` keyword?**

- Prevents variable from being serialized.

## **54. What is `volatile`?**

- Ensures visibility of variable across threads.
- Prevents caching in thread-local memory.

## **55. Difference between `==` and `.equals()`?**

- `==` compares references.
- `.equals()` compares values (if overridden in class).

## **56. Can constructor be private?**

- Yes, used in Singleton, Factory patterns.

## **57. What is Enum in Java?**

- Special class representing fixed constants.

## **58. Why multiple inheritance not allowed?**

- To avoid diamond problem.
- Java uses interfaces instead.

## **59. What is marker interface?**

- Interface with no methods (Serializable, Cloneable).

## **60. Can we override static methods?**

- No (method hiding instead).

---

## **61. What is immutable class? How to create?**

- State cannot change after creation.
- Use:

  - private final fields
  - no setters
  - private constructor
  - return copies in getters.

## **62. Difference between fail-fast and fail-safe?**

- Fail-fast throws ConcurrentModificationException.
- Fail-safe uses copy; no exception.

## **63. What is CopyOnWriteArrayList?**

- Thread-safe immutable snapshot list.
- Good for read-heavy, write-light operations.

## **64. What is Callable vs Runnable?**

- Runnable → no return, no checked exception.
- Callable → returns value, can throw checked exception.

## **65. What is Future?**

- Represents result of asynchronous computation.

## **66. What is CompletableFuture?**

- Asynchronous + chaining + pipeline support.

## **67. What is Reflection API?**

- Inspect/modify classes/methods/fields at runtime.

## **68. What is Annotation?**

- Metadata that provides instructions to compiler/VM.

## **69. What is method reference?**

- Shortcut for lambda: `Class::method`.

## **70. Functional interface?**

- Exactly one abstract method (e.g., Runnable).

---

## **71. What is JPA?**

- Java API for ORM.
- Provides persistence using annotations.

## **72. Difference: JPA vs Hibernate?**

- JPA = specification.
- Hibernate = implementation.

## **73. What is Entity in JPA?**

- Java class mapped to DB table.

## **74. What is Lazy vs Eager loading?**

- Lazy: loads data on-demand.
- Eager: loads data immediately.

## **75. What is Cascade in JPA?**

- Propagates entity state changes (PERSIST, MERGE, REMOVE, etc.)

## **76. What is JPQL?**

- Object-oriented query language for JPA.

## **77. Difference between Session & EntityManager?**

- Session = Hibernate API.
- EntityManager = JPA API.

## **78. What is @Transactional?**

- Manages transactions automatically.

## **79. What is dirty checking?**

- Hibernate auto-detects changed fields & updates DB.

## **80. N+1 problem in Hibernate?**

- Many queries issued due to lazy load.
- Solved via JOIN FETCH.

---

## **81. What is Servlet Filter?**

- Pre/post processing for requests (logging, auth).

## **82. What is Servlet Listener?**

- Tracks lifecycle events (session created, destroyed).

## **83. RequestDispatcher forward vs redirect?**

- Forward: server-side, URL same.
- Redirect: client-side, URL changes.

## **84. What is cookie vs session?**

- Cookie: stored client-side.
- Session: server-side user data.

## **85. What is HttpSession?**

- Maintains user-specific data.

## **86. What is JSP EL?**

- Expression Language for accessing data (`${}`).

## **87. What is JSTL?**

- Tag library for loops, conditions, formatting.

## **88. MVC in web apps?**

- Model: data
- View: UI
- Controller: logic

## **89. What is web.xml?**

- Deployment descriptor.

## **90. What is WAR file?**

- Web application archive packaged for deployment.

---

## **91. What is SOAP vs REST?**

- SOAP: XML-heavy protocol.
- REST: lightweight, JSON-friendly.

## **92. What is idempotency in REST?**

- Same request → same result (GET, PUT, DELETE).

## **93. What is statelessness?**

- Server stores no session/state.

## **94. What is DTO?**

- Data Transfer Object: structure for API data.

## **95. What is API rate limiting?**

- Restrict number of requests per time window.

## **96. What is JWT?**

- Token for authentication (header + payload + signature).

## **97. What is OAuth2?**

- Token-based authorization framework.

## **98. What is CORS?**

- Controls cross-domain requests.

## **99. What is CSRF?**

- Cross-site request forgery attack.

## **100. What is XSS?**

- Injection of malicious JavaScript into web pages.

---
