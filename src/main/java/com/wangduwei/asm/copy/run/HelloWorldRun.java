package com.wangduwei.asm.copy.run;

public class HelloWorldRun {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("sample.HelloWorld");
        Object instance = clazz.newInstance();
        System.out.println(instance);
    }
}
