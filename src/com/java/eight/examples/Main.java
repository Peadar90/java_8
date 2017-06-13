package com.java.eight.examples;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    private static List<Integer> numbers = Arrays.asList(new Integer(5), new Integer(3), new Integer(1), new Integer(4), new Integer(2));

    public static void main(String[] args) {

//        forEachExample();

//        methodReferenceExample();

//        java7ComparatorExample();
//        java8ComparatorExample();
//        lambdaComparatorExample();

//        performOperationOnCollectionJava7Example();
//        streamMapExample();
//        streamFilterExample();
//        streamDistinctExample();

//        functionalInterfacePredicateExample(number -> number % 2 == 0);
//        functionalInterfaceBiPredicateExample(">", (number, incrementNumber) -> number > incrementNumber);
//        System.out.println();
//        functionalInterfaceBiPredicateExample("<", (number, incrementNumber) -> number < incrementNumber);

//        Hero hero1 = new Hero("Bat", "Man", 123456, 2);
//        Hero hero2 = new Hero("Bat", "Girl", 654321, 3);
//        Hero hero3 = new Hero("Night", "Wing", 321456, 4);
//        List heroes = Arrays.asList(hero1, hero2, hero3);
//
//        functionalInterfaceImplementationExample("First Name Night?", (Hero hero) -> hero.getFirstName().equals("Night"), heroes);
//        functionalInterfaceImplementationExample("First Name Bat?", (Hero hero) -> hero.getFirstName().equals("Bat"), heroes);
//        functionalInterfaceImplementationExample("Employee Number is 123456?", (Hero hero) -> hero.getEmployeeNumber() == 123456, heroes);
//        functionalInterfaceImplementationExample("Salary > 2?", (Hero hero) -> hero.getSalary() > 2, heroes);

//        defaultInterfaceExample(hero1);
    }

    private static void forEachExample() {

        numbers.forEach(number -> System.out.println(number));
    }


    private static void methodReferenceExample() {

        numbers.forEach(System.out::println);
    }

    private static void java7ComparatorExample() {

        List<Integer> java7Numbers = new ArrayList<>();
        java7Numbers.addAll(numbers);

        printNumbers("Before Java 7 Comparator", java7Numbers);

        Collections.sort(java7Numbers, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        printNumbers("After Java 7 Comparator", java7Numbers);
    }

    private static void java8ComparatorExample() {

        List<Integer> java8Numbers = new ArrayList<>();
        java8Numbers.addAll(numbers);

        printNumbers("Before Java 8 Comparator", java8Numbers);

        //List.sort() since Java 8

        java8Numbers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return o1 - o2;
            }
        });

        printNumbers("After Java 8 Comparator", java8Numbers);
    }

    private static void lambdaComparatorExample() {

        List<Integer> lambdaNumbers = new ArrayList<>();
        lambdaNumbers.addAll(numbers);

        printNumbers("Before Lambda Comparator", lambdaNumbers);

        lambdaNumbers.sort((Integer i1, Integer i2) -> i1 - i2);

        printNumbers("After Lambda Comparator", lambdaNumbers);

        // New comparator method comparingInt
//        lambdaNumbers.sort(Comparator.comparingInt(Integer::intValue));
    }

    private static void performOperationOnCollectionJava7Example() {

        for (Integer number : numbers) {

            if (number == 5) {

                System.out.println(number);
            }
        }
    }

    private static void streamMapExample() {

//        List doubleNumbers = numbers.stream().map(number -> number * 2).collect(Collectors.toList());
//        doubleNumbers.forEach(System.out::println);

        //or

//        numbers.stream().map(number -> number * 2).collect(Collectors.toList()).forEach(System.out::println);
    }

    private static void streamFilterExample() {

        numbers.stream().filter(number -> number == 1).forEach(System.out::println);
    }

    private static void streamDistinctExample() {

        ArrayList<Integer> numbersDuplicates = new ArrayList<>();

        numbersDuplicates.addAll(numbers);
        numbersDuplicates.add(new Integer(5));
        numbersDuplicates.add(new Integer(3));
        numbersDuplicates.add(new Integer(2));
        numbersDuplicates.add(new Integer(4));
        numbersDuplicates.add(new Integer(4));
        numbersDuplicates.add(new Integer(1));

        numbersDuplicates.forEach(System.out::println);
        System.out.println();

        numbersDuplicates.stream().distinct().collect(Collectors.toList()).forEach(System.out::println);
    }

    private static void functionalInterfacePredicateExample(final Predicate<Integer> predicate) {

        numbers.forEach(number -> {

            if (predicate.test(number)) {

                System.out.println(number);
            }
        });
    }

    private static void functionalInterfaceBiPredicateExample(final String condition, final BiPredicate<Integer, Integer> predicate) {

        numbers.forEach(number -> {

            System.out.println(number + " " + condition + " " + ++number + " " + predicate.test(number, ++number));
        });
    }

    private static void functionalInterfaceImplementationExample(final String condition, FunctionalInterfaceExample functionalInterfaceExample,
                                                                       final List heroes) {

        System.out.println(condition);

        List<Hero> checked = new ArrayList<>();

        heroes.forEach(heroToCheck -> {

            if (functionalInterfaceExample.check((Hero) heroToCheck)) {

                checked.add((Hero) heroToCheck);
            }
        });

        checked.forEach(checkedHero -> {

            System.out.println("- " + checkedHero.getFirstName() + " " + checkedHero.getLastName());
        });

        System.out.println("===================");
    }

    private static void defaultInterfaceExample(final Hero hero) {

        System.out.println(hero.getFirstName().getClass().getName() + " :: " + hero.getFirstName());
        System.out.println(hero.getLastName().getClass().getName() + " :: " + hero.getLastName());
        System.out.println("int :: " + hero.getEmployeeNumber());
        System.out.println("int :: " + hero.getSalary());
        System.out.println(hero.getSalaryNewDefaultMethod().getClass().getName() + " :: " + hero.getSalaryNewDefaultMethod());
    }

    private static void printNumbers(final String description, final List<Integer> numbers) {

        System.out.println(description);

        numbers.forEach(System.out::println);
    }
}
