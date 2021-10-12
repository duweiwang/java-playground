package com.wangduwei.encode;

import java.io.UnsupportedEncodingException;

public class CharsetDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "我有一只小毛驴";
//        String str = "iamapple";

        byte[] utf = str.getBytes("UTF-8");
        System.out.println(utf.length);//33
        byte[] gbk = str.getBytes("GBK");
        System.out.println(gbk.length);//21
        byte[] other = str.getBytes();
        System.out.println(other.length);//33
        byte[] ask = str.getBytes("ASCII");
        System.out.println(ask.length);//11
        byte a = (new Integer(1)).byteValue();
        System.out.println(a);
        charLoop();
    }

    static void charLoop() {
        for (int a = 'A'; a < 'Z'; a++) {
            System.out.print(a + "->" + Integer.toBinaryString(a));
            System.out.print("/");
        }
        for (int a = 'a'; a < 'z'; a++) {
            System.out.print(a + "->" + Integer.toBinaryString(a));
            System.out.print("/");
        }

    }


}
