package com.java.eight.examples;

/**
 * Created by 37311 on 6/8/17.
 */
public interface DefaultMethodExampleOldInterface {

    String getFirstName();

    String getLastName();

    int getEmployeeNumber();

    int getSalary();

    // If uncommented, Hero class will require implementation but as default implementation is optional
//    Integer getSalaryNewDefaultMethod();

    default Integer getSalaryNewDefaultMethod() {

        return Integer.valueOf(getSalary());
    }
}
