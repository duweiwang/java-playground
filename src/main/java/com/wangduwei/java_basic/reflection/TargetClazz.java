package com.wangduwei.java_basic.reflection;

/**
 * @desc:
 * @auther:duwei
 * @date:2019/4/23
 */
public class TargetClazz {

    private String name;
    private int age;

    public TargetClazz(){

    }

    public TargetClazz(String name){
        this.name = name;
    }


    private TargetClazz(String name ,int age){
        this.age = age;
        this.name = name;
    }


}
