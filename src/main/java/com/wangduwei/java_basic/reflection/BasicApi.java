package com.wangduwei.java_basic.reflection;

/**
 * @author : wangduwei
 * @date : 2020/5/8
 * @description :
 */
public class BasicApi {

    public static void main(String[] args) {
        String name = BasicApi.class.getCanonicalName();
        String name2 = BasicApi.class.getSimpleName();
        String name3 = BasicApi.class.getName();
        String name4 = BasicApi.class.getTypeName();

        System.out.println(name);//com.wangduwei.java_basic.reflection.BasicApi
        System.out.println(name2);//com.wangduwei.java_basic.reflection.BasicApi
        System.out.println(name3);//com.wangduwei.java_basic.reflection.BasicApi
        System.out.println(name4);//com.wangduwei.java_basic.reflection.BasicApi

        //特殊类型会有不一样
        System.out.println(byte[].class.getCanonicalName());//byte[]
        System.out.println(byte[].class.getName());//[B
        System.out.println(byte[].class.getTypeName());//byte[]
    }

}
