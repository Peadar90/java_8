JAVA 8
======

The major new features in Java 8 center on lambda expressions, along with related features such as streams, method references, and functional interfaces.

Lambda
------

- An _anonymous function_ that allows you to pass methods as arguments.
- No access modifier(private, public or protected).
- No return type declaration and no name.
- It can take zero or more parameters.
- Parenthesis not required when using one parameter, e.g. a -> { do something }
- Curly braces not required when the body contains a single statement, e.g. ( ) -> return 45 can also be ( ) -> { return 45 };


- List interface supports the sort method directly, no need to use Collections.sort anymore.


- Comparator Example Java 7

```
List<Student> students = getStudents();

Collections.sort(students, new Comparator<Student>() {
	@Override
	public int compare(Student o1, Student o2) {
		return o1.getAge() - o2.getAge();
	}
});
```

- Comparator Example Java 8

```
List<Student> students = getStudents();

//List.sort() since Java 8
students.sort(new Comparator<Student>() {
	@Override
	public int compare(Student o1, Student o2) {
		return o2.getAge() - o1.getAge();
	}
});
```

```
//lambda
listDevs.sort((Developer o1, Developer o2)->o1.getAge()-o2.getAge());
```

```
//lambda, valid, parameter type is optional
listDevs.sort((o1, o2)->o1.getAge()-o2.getAge());
```


Default Methods
---------------

- Allows developers to add new methods to the interfaces without breaking the existing implementation of these interface.
- Provides flexibility to allow interface define implementation which will use as default in the situation where a concrete class fails to provide an implementation for that method.

```
public interface oldInterface {
    public void existingMethod();

    default public void newDefaultMethod() {
        System.out.println("New default method"
              " is added in interface");
    }
}
```

```
public class oldInterfaceImpl implements oldInterface {
    public void existingMethod() {
        // existing implementation is here…
    }
}
```

```
oldInterfaceImpl obj = new oldInterfaceImpl ();
// print “New default method add in interface”
obj.newDefaultMethod();
```


For Each
--------

- Iterable interface provides a passive iterator in the form of a default method called forEach().
  - Any collection class that implements the Iterable interface can now use forEach().

- Advanced For Loop Example Java 7

```
List<String> names = getNames();

for (String name : names)
    System.out.println(name);
```

- For Each Example Java 8

```
List<String> names = getNames();

names.forEach(name -> System.out.println(name));
```

- Above, we tell the forEach() method what to do with the objects in the list — in this case we simply print the object.
- Control of the iteration resides within the forEach() method (passive).


Streams
-------

Sequential
----------

- A mechanism to carry a sequence of values through a pipeline of operations.
- Streams are not Collections that store elements.
- A Stream pipeline consists of a Stream source, intermediate operations that transform the stream and produce a new Stream, and a terminal operation that either produces a result or calls the forEach() method.
- Collections and Arrays can be used to generate Streams.

- Using active iterators in Java 7

```
List<String> names = getNames();

long count = 0;
for (String name : names)
  {
    if (name.startsWith("A"))
        ++count;
  }
```

- Using a stream pipeline of operations in Java 8

```
List<String> names = getNames();

long count = names.stream()
                  .filter(name -> name.startsWith("A"))
                  .count();
```

- Above, filter() is the intermediate operation.
  - There are other transformations that can be performed on a Stream.
- Other examples include:
  - distinct()
  - sorted()
  - map() - lets you convert an object to something else
- count() is the terminal operation.
- Other terminal operation examples include:
  - sum()
  - average()
  - max()
  - forEach()
  - collect() - performs mutable fold operations (repackaging elements to some data structures and applying some additional logic, concatenating them, etc.) on data elements held in a Stream instance
- Depending on the context, the logic in the Java 7 example might not be thread-safe, while the Java 8 example is thread safe.


Parallel Streams
----------------

- Also part of Collections
- Allows pipeline operations to be executed concurrently in separate Java threads.
  - Note - the order in which the Collection elements are processed can change.
- Parallelizing the operations in a stream pipeline is usually as simple as replacing the call to stream() with a call to parallelStream().


Functional Interface
--------------------
- Have a single functionality to exhibit.
  - e.g. a Comparable interface with a single method ‘compareTo’ is used for comparison purpose.
- Java 8 has defined a lot of functional interfaces to be used extensively in lambda expressions.
- Some examples include:
  - BiConsumer<T,U> - Represents an operation that accepts two input arguments, and returns no result.
  - BiPredicate<T,U> - Represents a predicate (boolean-valued function) of two arguments.
  - DoubleFunction<R> - Represents a function that accepts a double-valued argument and produces a result.
  - Predicate<T> - Represents a predicate (boolean-valued function) of one argument.

- Example using the Predicate Interface
- Predicate <T> interface is a functional interface with a method test(Object) to return a Boolean value. This interface signifies that an object is tested to be true or false.

```
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

System.out.println("Print all numbers:");
//n is passed as parameter to test method of Predicate interface
eval(list, n->true);

System.out.println("Print even numbers:");
eval(list, n-> n%2 == 0 );

System.out.println("Print numbers greater than 3:");
eval(list, n-> n > 3 );

public void eval(List<Integer> list, Predicate<Integer> predicate) {

   for(Integer n: list) {
      if(predicate.test(n)) {
         System.out.println(n + " ");
      }
   }
```

- You can create your own Functional Interface.
  - Will have exactly _one_ abstract method.
  - Don’t need the annotation FunctionalInterface annotation; this annotation just documents your intention and helps detecting errors at compile time similar to Override.
  - The major benefit is that we can use lambda expressions to instantiate them and avoid using bulky anonymous class implementation.
- Existing one-method interfaces in the Java API.
  - Runnable, Callable, Comparator, ActionListener.

```
@FunctionalInterface
public Interface ITrade() {
    public boolean check(Trade trade);
}

ITrade newTradeChecker = (Trade trade) -> t.getStatus().equals("NEW");

// Or we could omit the input type setting:
ITrade newTradeChecker = (trade) -> t.getStatus().equals("NEW");
```

- NOTE - Since default methods have an implementation, they are not abstract. If an interface declares an abstract method overriding one of the public methods of java.lang.Object, that also does not count toward the interface's abstract method count since any implementation of the interface will have an implementation from java.lang.Object or elsewhere

```
interface Foo { boolean equals(Object obj); }
// Not functional because equals is already an implicit member (Object class)

interface Comparator<T> {
 boolean equals(Object obj);
 int compare(T o1, T o2);
}
// Functional because Comparator has only one abstract non-Object method
```

Method References
-----------------

- When we are using a method reference – the target reference is placed before the delimiter :: and the name of the method is provided after it

- Comparator Example with Lambda Expression
```
Comparator c = (Student s1, Student s2) -> s1.getAge().compareTo(s2.getAge());
```

- With Type Inference

```
Comparator c = (s1, s2) -> s1.getAge().compareTo(s2.getAge());
```

- The :: operator as a shorthand for lambdas calling a specific method, i.e. by name

```
Comparator c = Comparator.comparing(Student::getAge);
```

- It uses a method reference – the target reference is placed before the delimiter :: and the name of the method is provided after it.

- Further Examples

```
List<String> names = getNames();
names.forEach(System.out::print);
```

- Referencing a Constructor to Instantiate an Object

```
@FunctionalInterface
public interface Student {
    StudentImpl create();
}

Student student = Student::new;
StudentImpl studentImpl = student.create();
```