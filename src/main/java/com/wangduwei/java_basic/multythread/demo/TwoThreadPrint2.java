package com.wangduwei.java_basic.multythread.demo;

import java.util.concurrent.locks.LockSupport;

/**
 * 交替打印：a1b2c3d4e5f6g7
 *
 * @author : wangduwei
 * @date : 2020/5/5
 * @description :
 */
public class TwoThreadPrint2 {

    static Thread thread1, thread2;

    public static void main(String[] args) {

        char[] nums = "1234567".toCharArray();
        char[] cars = "abcdefg".toCharArray();

        thread1 = new Thread() {
            @Override
            public void run() {
                super.run();
                for (char num : nums) {
                    System.out.print(num);
                    LockSupport.unpark(thread2);//叫醒T2
                    LockSupport.park();//堵塞t1
                }
            }
        };


        thread2 = new Thread() {
            @Override
            public void run() {
                super.run();
                for (char car : cars) {
                    System.out.print(car);
                    LockSupport.park();//堵塞t2
                    LockSupport.unpark(thread1);//叫醒t1
                }
            }
        };
        thread2.start();
        thread1.start();
    }


}
