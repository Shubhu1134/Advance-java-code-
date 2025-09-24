## **_ Collection _**

# 🔹 Level 1: Basics (Warm-Up)

### 1. Remove duplicates from a list

```java
import java.util.*;

public class RemoveDuplicates {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);

        Set<Integer> unique = new HashSet<>(numbers);
        System.out.println("Without duplicates: " + unique);
    }
}
```

👉 Try with `LinkedHashSet` and `TreeSet` to see order differences.

---

### 2. Reverse a list

```java
import java.util.*;

public class ReverseList {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collections.reverse(names);
        System.out.println("Reversed: " + names);
    }
}
```

---

### 3. Count frequency of words

```java
import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String text = "apple orange apple banana apple orange";
        String[] words = text.split(" ");

        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        System.out.println("Word frequency: " + frequency);
    }
}
```

---

# 🔹 Level 2: Intermediate

### 4. Sort map by values

```java
import java.util.*;

public class SortMapByValue {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 3);
        map.put("C", 1);
        map.put("B", 2);

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        System.out.println("Sorted by value: " + list);
    }
}
```

---

### 5. Find first non-repeated character in a string

```java
import java.util.*;

public class FirstNonRepeated {
    public static void main(String[] args) {
        String text = "aabbccdeff";
        Map<Character, Integer> count = new LinkedHashMap<>();

        for (char c : text.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println("First non-repeated: " + entry.getKey());
                break;
            }
        }
    }
}
```

---

# 🔹 Level 3: Advanced

### 6. Implement LRU Cache using LinkedHashMap

```java
import java.util.*;

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // accessOrder = true
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

public class TestLRU {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache);

        cache.get(1); // access 1
        cache.put(4, "D"); // removes 2
        System.out.println(cache);
    }
}
```

---

### 7. ConcurrentHashMap word counter

```java
import java.util.concurrent.*;

public class ConcurrentWordCount {
    public static void main(String[] args) {
        String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        for (String word : words) {
            map.merge(word, 1, Integer::sum);
        }

        System.out.println("Word count: " + map);
    }
}
```

---

✅ These exercises cover:

- **Lists, Sets, Maps**
- **Sorting**
- **Iterators**
- **LinkedHashMap for LRU**
- **Concurrent collections**

---
