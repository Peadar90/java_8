package com.java.eight.examples;

/**
 * Created by 37311 on 2017-06-20.
 */
@FunctionalInterface
public interface HeroWithArgProvider {

    Hero getHero(final String firstName, final String lastName, final int employeeNumber, final int salary);
}
