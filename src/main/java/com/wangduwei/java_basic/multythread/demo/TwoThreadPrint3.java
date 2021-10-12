package com.wangduwei.java_basic.multythread.demo;

/**
 * @author : wangduwei
 * @date : 2020/5/5
 * @description :
 */
public class TwoThreadPrint3 {

    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] nums = "1234567".toCharArray();
        char[] cars = "abcdefg".toCharArray();


        new Thread("t1"){
            @Override
            public void run() {
                super.run();
                for (char car : cars) {
                    while (r != ReadyToRun.T1){}
                    System.out.print(car);
                    r = ReadyToRun.T2;
                }
            }
        }.start();


        new Thread("t2"){
            @Override
            public void run() {
                super.run();
                for (char num : nums) {
                    while (r != ReadyToRun.T2){}
                    System.out.print(num);
                    r = ReadyToRun.T1;
                }
            }
        }.start();
    }

}
