package com.wangduwei.java_basic.reflection.method;

/*
 * Program Demonstrate isDefault() method
 * of Method Class.
 */

import java.lang.reflect.Method;

public class GFG {

    // create main method
    public static void main(String args[]) {
        try {
            // create class object for interface Shape
            Class c = Shape.class;
            // get list of Method object
            Method[] methods = c.getMethods();
            // print Default Methods
            for (Method m : methods) {
                // check whether the method is Default Method or not
                if (m.isDefault())
                    // Print
                    System.out.println("Method: " + m.getName()
                            + " is Default Method");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private interface Shape {
        default int draw() {
            return 0;
        }

        void paint();
    }
}
