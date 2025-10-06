Q.27 What do you mean mutability & immutability String in Java?

```
Ans : In java **mutability** means

 ability of an object to be changed or modified  after it is created .

 Immutability is wise versa or we need to create another object if we changed it after object created .
```

Q.28 What is difference between equal() method and == operator ?

```
equal() , this equal() method is used to compare conten of          object .

== operator , is used to compare the refrences . it works for both primitives and objects .

example ->   String s1 = "Java";
String s2 = "Java";
String s3 = new String("Java");

System.out.println(s1 == s2); // true
System.out.println(s1 == s3); //false

```

Q.29 What is String ? How many way to initialize the string in java ?

```
String is sequence of charecters which is enclosed by " " .

there is two ways to create string object -> 1. by using literals

                                             2. by using new key word .
```

Q30.What is difference between String , StringBuffer and StringBuilder in Java?

```
1.String --> thread safe due to immutability , but not synchronized ( when we perform write operation ), immutable .

2.String Builder - mutable , not synchronized , not thread safe .

3.String Buffer - mutable , Synchronized , thread safe .
```

```
examples -->
```

Q.31 -> What do you mean by method overloading in Java ? Tell me the key point related with method overloading.

```
method overloading means having multiple methods with the same name in the same class but with different parameters (type , number , or order ).

it is a form of compile-time polymorphism (decided at compile time ).

key points -->
1.same method name .
2.difference parameter .
3.return type can be same or different , but can not overload only by return type .
4. compile time polymorphism .
5.can overload in same class or child class : if inherited , child class can overload parent class method .
6. access modifier : can be same or different .
7. static method : can also be overload .

```

```
example :
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(double a, double b) {
        return a + b;
    }


    double add(int a, double b) {
        return a + b;
    }
}

public class TestOverload {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        System.out.println(calc.add(2, 3));         // Calls int, int
        System.out.println(calc.add(2, 3, 4));      // Calls int, int, int
        System.out.println(calc.add(2.5, 3.5));     // Calls double, double
        System.out.println(calc.add(2, 3.5));       // Calls int, double
    }
}

```

Q.32 -> Can we overload static method in Java ? can we overload main method in Java ?

```
yes , we can overload static method as well as main method .

```

Q.33 -> What do you mean by array ? In which scenario of application you would like to use array concept.

```
An array is a container that holds multiple and homogenous type data , fixed in size , indexed based storeing .

when we use --> When you already know how many elements you need to store and it won’t change.

key point :
Stores multiple elements of the same type.
Fixed size — cannot grow or shrink dynamically.
Indexed — first element at index 0, last element at length - 1.
Can be single-dimensional or multi-dimensional (2D, 3D…).
Stored in contiguous memory locations.

```

Q34->What is difference between array & var-arg (…) notation in Java ?

```
when we use use array the no of arguments must be as definined the size of array neither less nor more ,
or large ammount of data with known the  quantity .

Var-arg (variable-length argument) allows a method to accept zero or more arguments of a specific type.


```

Q35.-> can we use method overloading like behavior without implementing the method multiple times?

```
NO
```

Q36- > Tell me the valid signature of defining of array in Java ?

```
data-type [] name of array = new data-type [size];

ex-> int [] arr = new int [5];

```

Q37-> is array fixed in size ?

```
Yes , array is fixed in size .
```

Q38-> What do mean by Inheritance in Java ? How many types of Inheritance supported by Java ?

- Inheritance → mechanism to acquire properties and methods of another class.

Promotes code reusability and reduces duplication.

Achieved using extends (for classes) and implements (for interfaces).

Supported types:

Single

Multilevel

Hierarchical

Not supported: Multiple & Hybrid (through classes).
Multiple inheritance is allowed only through interfaces.
Base class → Parent / Super class, Derived class → Child / Subclass.

```
Q39 -> Why Java doesn’t support multiple inheritance.
```

Java doesn’t support multiple inheritance with classes to avoid ambiguity

```
Q40-> What are advantage of inheritance in real time java applicaction ?
```

Promotes code reusability and reduces duplication.

```
Q41-> Inhritance IS-A or HAS-A relationship ? Explain HAS-A relationship with suitable example.
```

When one class inherits another, it forms an IS-A relationship.
it promots loose coupling .

A HAS-A relationship means one class contains a reference to another class.
It represents a “part-of” or “uses-a” relationship.
it means a class is independent of another class ( chnage in a class does not affect another ).

```

```
