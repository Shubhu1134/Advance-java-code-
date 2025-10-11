Q.27 What do you mean mutability & immutability String in Java

```
Ans : In java **mutability** means

 ability of an object to be changed or modified  after it is created .

 Immutability is wise versa or we need to create another object if we changed it after object created .
```

Q.28 What is difference between equal method and == operator

```
equal , this equal method is used to compare conten of          object .

== operator , is used to compare the refrences . it works for both primitives and objects .

example ->   String s1 = "Java";
String s2 = "Java";
String s3 = new String"Java";

System.out.printlns1 == s2; // true
System.out.printlns1 == s3; //false

```

Q.29 What is String How many way to initialize the string in java

```
String is sequence of charecters which is enclosed by " " .

there is two ways to create string object -> 1. by using literals

                                             2. by using new key word .
```

Q30.What is difference between String , StringBuffer and StringBuilder in Java

```
1.String --> thread safe due to immutability , but not synchronized  when we perform write operation , immutable .

2.String Builder - mutable , not synchronized , not thread safe .

3.String Buffer - mutable , Synchronized , thread safe .
```

Q.31 -> What do you mean by method overloading in Java Tell me the key point related with method overloading.

```
method overloading means having multiple methods with the same name in the same class but with different parameters type , number , or order .

it is a form of compile-time polymorphism decided at compile time .

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

    int addint a, int b {
        return a + b;
    }

    int addint a, int b, int c {
        return a + b + c;
    }

    double adddouble a, double b {
        return a + b;
    }


    double addint a, double b {
        return a + b;
    }
}

public class TestOverload {
    public static void mainString[] args {
        Calculator calc = new Calculator;

        System.out.printlncalc.add2, 3;         // Calls int, int
        System.out.printlncalc.add2, 3, 4;      // Calls int, int, int
        System.out.printlncalc.add2.5, 3.5;     // Calls double, double
        System.out.printlncalc.add2, 3.5;       // Calls int, double
    }
}

```

Q.32 -> Can we overload static method in Java can we overload main method in Java

```
yes , we can overload static method as well as main method .

```

Q.33 -> What do you mean by array In which scenario of application you would like to use array concept.

```
An array is collection of homogenous type data , fixed in size , and follow indexed based storeing .

when we use --> when we a large and fixed no of data .

key point :
Stores multiple elements of the same type.
Fixed size — cannot grow or shrink dynamically.
Indexed — first element at index 0, last element at length - 1.
Can be single-dimensional or multi-dimensional 2D, 3D….
Stored in contiguous memory locations.

```

Q34->What is difference between array & var-arg … notation in Java

```
when we use use array the no of arguments must be as definined the size of array neither less nor more ,
or large ammount of data with known the  quantity .

Var-arg variable-length argument allows a method to accept zero or more arguments of a specific type.


```

Q35.-> can we use method overloading like behavior without implementing the method multiple times

```
NO
```

Q36- > Tell me the valid signature of defining of array in Java

```
data-type [] name of array = new data-type [size];

ex-> int [] arr = new int [5];

```

Q37-> is array fixed in size

```
Yes , array is fixed in size .
```

Q38-> What do mean by Inheritance in Java How many types of Inheritance supported by Java

- Inheritance → mechanism to acquire properties and methods of another class.

Promotes code reusability and reduces duplication.

Achieved using extends for classes and implements for interfaces.

Supported types:

Single

Multilevel

Hierarchical

Not supported: Multiple & Hybrid through classes.
Multiple inheritance is allowed only through interfaces.
Base class → Parent / Super class, Derived class → Child / Subclass.

```
Q39 -> Why Java doesn’t support multiple inheritance.
```

Java doesn’t support multiple inheritance with classes to avoid ambiguity

```
Q40-> What are advantage of inheritance in real time java applicaction
```

Promotes code reusability and reduces duplication.

```
Q41-> Inhritance IS-A or HAS-A relationship  Explain HAS-A relationship with suitable example.
```

When one class inherits another, it forms an IS-A relationship.
it promots loose coupling .

A HAS-A relationship means one class contains a reference to another class.
It represents a “part-of” or “uses-a” relationship.
it means a class is independent of another class chnage in a class does not affect another .

```

Q42 What are execution order of constructor in case of Inheritance

```

Ans : parent to child

```

Q43 Explain “super” and “super” in Java

```

Ans : super is keyword it refer parent class .
super used to call parent class constructor .

```

Q44 Expalin “this” and “this” in Java

```

Ans : this refer to current object .
this refer to current class constructor .

```

Q45  What is method overriding in Java  Tell me the key point related with method overloading.

```

Ans : If child class provide new implementation of parent class method then it is overriding .

key points ->
1.same name .
2.same parameter .
3.same return type .
4.runtime polymorphism .

```

Q46 As a Java programmer when you like to override the method in Java

```

Ans : when i need to change logic of parent class method .

```

Q47 can we can change the access modifier while overriding the method in Java

```

Ans : yes but only from protected to public not public to protected .

```

Q48 can we change the return type of the method while overriding it

```

Ans : no .

```

Q49 What are the rule to be followed while overriding the method

```

Ans : same name , same parameter , same return type , modifier can be wider .

```

Q50 can we override the static Method

```

Ans : no .

```

Q51 can we override protected method of super class as a public method in sub class

```

Ans : yes .

```

Q52 can we override super class method without throws clause as a method with throws clause in sub class.

```

Ans : yes .

```

Q53 can we change an exception of a method with throws clause from SQLException to NumberFormatException while overriding it

```

Ans : yes .

```

Q54 can we change a exception of a method with throws clause from unchecked exception to checked exception while overriding it

```

Ans : no .

```

Q55 How do you refer super class  overridden method in the sub class

```

Ans : by using super.methodName .

```

Q56 can we override private method in java

```

Ans : no .

```

Q57 can we override an exception of a method with throws clause from checked to unchecked while overriding it

```

Ans : yes .

```

Q58 is it possible to override non-static method as a static method

```

Ans : no .

```

Q59 What is difference between following two statement
a Parent p = new Parent;
b Child c = new Child;

```

Ans : a only parent method access .
b parent + child method access .

```

Q60 In which scenario of application you would like to use Parent class reference variable holding child class object concept

```

Ans : when i don’t know which class object will come at runtime .

```

Q61What is difference between Method Overloading and Method Overriding In Java
Or
What is difference compile time polymorphism or runtime polymorphism

```

Ans : overloading -> compile time , same name diff parameter .
overriding -> runtime , same name same parameter .

```

Q62What is abstract class and abstract method in java  list down the key point related with abstract class and abstract method.

```

Ans : abstract class can’t make object , it need to be inherit .
abstract method have no body only declaration .

key points ->
1.abstract class may have normal + abstract method .
2.abstract method only inside abstract class .
3.object can’t be created .

```

Q63  We can’t create the object of abstract class so can we define the instance variable  and constructor in abstract class

```

Ans : yes .

```

Q64  is blank abstract class is possible in Java

```

Ans : yes .

```

Q65 What is interface in Java  Explain the real time utility of an interface

```

Ans : interface is fully abstract class .
used to achieve abstraction and multiple inheritance .

```

Q66 What is difference between jdk 1.7 interface and jdk 1.8 interface

```

Ans : jdk 1.7 -> only abstract method .
jdk 1.8 -> abstract + default + static method .

```

Q67 What is the difference between Abstract class and interface jdk 1.8

```

Ans : abstract class -> variable , constructor , normal + abstract method .
interface -> constant variable , abstract + default + static method .

```

Q68 can we achieve the behviour of multiple inheritance with the help of interface in java

```

Ans : yes .

```

Q69 How to declare interface, write a syntax

```

Ans : interface A {}

Q70 Can we declare the interface as final in Java

```

Ans : no .

```

Q71 Which keywords java compiler add before the interface fields

```
Ans : public static final
```

Q72 Does interface extends Object class by default

```
Ans : no .
```

Q73 can we declare interface with final keyoword

```
Ans : no .
```

Q74 After compilation of interface program, .class file will be generated for every interface in java. True or false

```
Ans : true .
```

Q75 What is marker or tagged interface in java

```
Ans : marker or tagged interface is known as empty interface .
example -> Serializable, Cloneable .
```

Q76 Can we change the value of field in interface after initialization

```
Ans : no .
```

Q77 What is final keyword in java

```
Ans : final is keyword used to make constant which can’t be changed till the end of complete execution .
```

Q78 Where you can use final keyword in java

```
Ans : where we don’t want modification -> variable , method , class .
```

Q79 can we change the value of final variable

```
Ans : no .
```

Q80 can we use final keyword with the main method in java

```
Ans : yes , it can be final but not common practice .
```

Q81 can we override final method in java

```
Ans : no .
```

Q82 What is static blank final variable . How to initialize static blank final variable in java

```
Ans : static blank final variable -> declared but not initialized .
it can be initialized only in static block .
```

Q83 Can we inherit final method in sub-class

```
Ans : yes , but can’t override .
```

Q84 What is difference between abstract method and final method in Java

```
Ans : abstract method -> must be override .
final method -> can’t be override .
```

Q85 static & abstract can be used together True or False

```
Ans : false .
```

Q86 static & final can be used together True or False

```
Ans : yes .
```

Q87 abstract and final keyword can be used together True or False

```
Ans : false .
```

Q88 What is difference between final , finally and finalize

```
Ans :
final -> keyword used with variable, class, method.
finally -> block used in try-catch for important code.
finalize -> method called before object destroy by GC.
```

Q89 What is Exception Explain the exception hierarchy.

```
Ans : Abnormal termination of program is called exception .

Hierarchy ->
Object
  Throwable
   ── Exception
   ── Error
```

Q90 What is difference between error and exception

```
Ans : error -> serious issue, can’t handle ex : OutOfMemoryError
       exception -> can handle ex : NullPointerException
```

Q91 What is difference between checked exception and unchecked exception

```
Ans : all the child class of Exception except RuntimeException are checked exception .
checked exception code is mandatory to handle .
all child class of RuntimeException are unchecked exception .
```

Q92 What is difference between throw and throws

```
Ans : throw -> used to throw exception manually .
throws -> used to declare exception in method signature .
```

Q93 can we write try block without catch

```
Ans : no .
```

Q94 Can we write try block without catch or finally

```
Ans : no .
```

Q95 Can we write multiple catch block corresponding to single try block If yes then what are the conditions for it

```
Ans : yes , order must be child to parent exception class .
```

Q96 Can we handle more then one exception using single catch block Explain with suitable example.

```
Ans : yes , using multi-catch | operator .
example ->
try {
   int a = 10/0;
} catch ArithmeticException | NullPointerException e {
   System.out.println"Exception handled";
}
```

Q97 Can we throw the error and throwable object

```
Ans : yes , both can be thrown using throw keyword .
```

Q98 Write a java program to create own custom exception class.

```
example ->
class MyException extends Exception {
   MyExceptionString msg {
      supermsg;
   }
}
public class Test {
   public static void mainString[] args throws MyException {
      throw new MyException"Custom Exception Occurred";
   }
}
```

Q99 What is difference between final , finally , and finalize

```
Ans : final -> keyword used with variable, method, class non-changeable .
finally -> block used in try-catch for important code .
finalize -> method called before object destroy by GC .
```

Q100 What is finally block what is the use of finally block in Java application

```
Ans : finally block used to close connection or release resource .
it always execute whether exception handled or not .
```

Q101 Explain try with resource with suitable example.

```
Ans : used to auto close resource like file or connection .
example ->
try FileReader fr = new FileReader"a.txt" {
   // read file
} catch IOException e {
   e.printStackTrace;
}
```

Q102 Write all possible valid combination of try, catch and finally.

```
Ans :
1 try must have catch or finally .
2 catch must have try .
3 finally must have try .
4 order -> try -> catch -> finally .
5 multiple catch allowed child to parent order .
6 same exception type in two catch -> compile error .
7 nested try-catch-finally allowed .
8 curly braces mandatory for all block .
```

Q103 What is Byte-Stream, Character Stream, FileOutputStream and FileWriter

```
Ans : Byte stream -> read/write 8-bit data .
Character stream -> read/write 16-bit unicode .
FileOutputStream -> write byte data to file .
FileWriter -> write character data to file .
```

Q104 What is Marker interface in java How to serialize the object in java. Explain Serialization with example.

```
Ans : marker interface -> empty interface no method/field .
example -> Serializable, Cloneable .
to serialize object -> implement Serializable interface and use writeObject method .

example ->
class Employee implements java.io.Serializable {
   String name;
   String address;
   transient int SSN;
   int number;
}
```

Q105 What is transient keyword can we serialize static data member in class

```
Ans : transient -> used to skip variable from serialization .
static data member not serialized because it belongs to class not object .
```

120. What is List in Java
     Ans :collection of object as a single unit . List is an ordered collection that allows duplicate elements. It maintains insertion order.
     Examples of classes implementing List are ArrayList, LinkedList, Vector, Stack.

121. Difference between ArrayList and LinkedList
     Ans :
     ArrayList uses dynamic array to store elements, fast in access but slow in insertion/deletion.
     LinkedList uses doubly linked list, faster in insertion/deletion but slower in access.
     ArrayList better for storing and accessing data, LinkedList better for manipulating data.

122. Difference between Vector and ArrayList
     Ans :
     Vector is synchronized while ArrayList is not.
     Vector doubles its size when capacity is reached, ArrayList increases by 50%.
     Vector is slower compared to ArrayList.

123. Difference between Iterator and ListIterator 
     Ans :
     Iterator can traverse elements only in forward direction.
     ListIterator can traverse both forward and backward.
     ListIterator works only on List, Iterator works on any Collection.

124.what is difference between hashset and linked hashset?
```

