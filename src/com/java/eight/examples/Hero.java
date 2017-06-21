package com.java.eight.examples;

/**
 * Created by 37311 on 6/8/17.
 */
public class Hero implements DefaultMethodExampleOldInterface {

    private String firstName;

    private String lastName;

    private int employeeNumber;

    private int salary;

    public Hero() {

        this.firstName = "Bat";
        this.lastName = "Man";
        this.employeeNumber = 123456;
        this.salary = 2;
    }

    public Hero(final String firstName, final String lastName, final int employeeNumber, final int salary) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeNumber = employeeNumber;
        this.salary = salary;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public int getEmployeeNumber() {

        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {

        this.employeeNumber = employeeNumber;
    }

    public int getSalary() {

        return salary;
    }

    public void setSalary(int salary) {

        this.salary = salary;
    }
}
