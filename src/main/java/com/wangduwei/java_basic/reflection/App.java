package com.wangduwei.java_basic.reflection;

/**
 * 反射Demo测试类
 *
 * @author : duwei
 * @since : 2019/4/23
 */
public class App {

    public static void main(String[] args) throws ClassNotFoundException {
        boolean isE = GetClazzObj.isEqual();
        System.out.print(isE);
        System.out.println("------");

        new AccessClazz().outputClazzInfo();
    }
}
