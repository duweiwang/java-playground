package com.wangduwei.java_basic.multythread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 哲学家进餐问题，通过设置超时时间避免死锁
 */
class Philosopher extends Thread {
    private ReentrantLock leftChopstick, rightChopstick;

    private Random random;

    public Philosopher(ReentrantLock leftChopstick, ReentrantLock rightChopstick) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        random = new Random();
    }

    public void run() {

        try {
            while (true) {
                Thread.sleep(random.nextInt(1000)); // 思考一段时间
                leftChopstick.lock();
                try {
                    if (rightChopstick.tryLock(1000, TimeUnit.MILLISECONDS)) {//获取右手边的筷子
                        try {
                            Thread.sleep(random.nextInt(1000)); //进餐一段时间
                        } finally {
                            rightChopstick.unlock();
                        }
                    } else {
                        //没有获取到右手边的筷子，放弃并继续思考
                    }
                } finally {
                    leftChopstick.unlock();
                }
            }
        } catch (
                InterruptedException e) {
        }
    }
}