package com.wangduwei.java_basic.reflection.method;

/*
 * Program Demonstrate isDefault() method
 * of Method Class.
 * This program checks all default method in Comparator interface
 */

import java.lang.reflect.Method;
import java.util.Comparator;

public class Main6 {

    // create main method
    public static void main(String args[]) {

        try {
            // create class object for Interface Comparator
            Class c = Comparator.class;

            // get list of Method object
            Method[] methods = c.getMethods();

            System.out.println("Default Methods of Comparator Interface");
            for (Method method : methods) {
                // check whether method is Default Method or not
                if (method.isDefault())
                    // print Method name
                    System.out.println(method.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
