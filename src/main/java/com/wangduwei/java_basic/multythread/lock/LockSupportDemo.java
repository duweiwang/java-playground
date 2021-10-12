package com.wangduwei.java_basic.multythread.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 *
 * @author : wangduwei
 * @since : 2020/5/14  16:53
 **/
public class LockSupportDemo {

    static Queue<Integer> queue = new LinkedList<>();
    static Thread thread1, thread2;
    static int size = 10;

    public static void main(String[] args) {
//生产者
        thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (queue.size() == size) {
                        LockSupport.park();
                        System.out.println("1休眠");
                    }
                    System.out.println("放入数据");
                    queue.add(new Random().nextInt(10));
                    LockSupport.unpark(thread2);
                }
            }
        };
//消费者
        thread2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    while (queue.isEmpty()) {
                        LockSupport.park();
                        System.out.println("2休眠");
                    }
                    int value = queue.poll();
                    System.out.print("取出 = " + value);
                    LockSupport.unpark(thread1);
                }
            }
        };


        thread1.start();
        thread2.start();
    }

}
