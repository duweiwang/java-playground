package com.wangduwei.kotlin_jvm.jvmstatic;

import com.wangduwei.kotlin.jvm_annotation.Singleton;
import com.wangduwei.kotlin.jvm_annotation.Singleton2;

public class Client {


    public static void main(String[] args) {
        Singleton.INSTANCE.play();

        Singleton2.play();

        Singleton2.play();
    }


}
