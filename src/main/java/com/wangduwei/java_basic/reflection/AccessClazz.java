package com.wangduwei.java_basic.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @desc: 利用反射格式化输出TargetClass
 * @auther:duwei
 * @date:2019/4/23
 */
public class AccessClazz {

    public void outputClazzInfo() throws ClassNotFoundException {
        Class clazz = Class.forName("com.wangduwei.java_basic.reflection.TargetClazz");

        print("class Name = " + clazz.getName() + "\n");
        print("class " + clazz.getSimpleName() + " {\n");
        Field[] fields = clazz.getDeclaredFields();

        Constructor[] con = clazz.getDeclaredConstructors();
        for (int i = 0; i < con.length; i++) {
            int mod = con[i].getModifiers();
            print("\n   " + Modifier.toString(mod) + " " + con[i].getName() + "(");

            Class[] parameters = con[i].getParameterTypes();

            for (int j = 0; j < parameters.length; j++) {
                print(parameters[j].getName());
            }
            print(") {\n");
            print("\n   }");
        }
        print("\n}");
    }


    public void print(String info) {
        System.out.print(info);
    }


}
