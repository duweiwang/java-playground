package com.wangduwei.java_basic.multythread.demo;

/**
 * <p>
 * 两个线程依次输出奇数偶数
 * </p>
 *
 * @auther: davewang
 * @since: 2019/7/16
 **/
public class TwoThreadPrint {

    public static void main(String[] args) {
        Content lock = new Content();
        JiThread thread1 = new JiThread(lock);
        OuThread ouThread = new OuThread(lock);

        thread1.start();
        ouThread.start();

        while (thread1.isAlive() || ouThread.isAlive()) ;
    }

    private static class Content {
        int value = 0;
        boolean otherGo = false;
    }

    public static class JiThread extends Thread {
        private Content lock;

        public JiThread(Content lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (lock.value < 100) {
                synchronized (lock) {
                    try {
                        if (!lock.otherGo) {
                            System.out.println("A:" + ++lock.value);
                            lock.otherGo = true;
                            lock.wait();
                        } else {
                            lock.otherGo = false;
                            lock.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class OuThread extends Thread {
        private Content lock;

        public OuThread(Content lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (lock.value < 100) {
                synchronized (lock) {
                    try {
                        if (!lock.otherGo) {
                            lock.otherGo = true;
                            lock.wait();
                        } else {
                            System.out.println("B:" + ++lock.value);
                            lock.otherGo = false;
                            lock.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
