package com.wangduwei.java_basic.multythread.demo;

import java.util.concurrent.Semaphore;

/**
 * 使用信号量交替打印ABC
 */
class PrintABCSemaphore {

    private static Semaphore sa = new Semaphore(1);
    private static Semaphore sb = new Semaphore(0);
    private static Semaphore sc = new Semaphore(0);

    private static class PrintAThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    sa.acquire();
                    System.out.println("A");
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sb.release();
                }
            }

        }
    }

    private static class PrintBThread extends Thread {

        @Override
        public void run() {

            while (true) {
                try {

                    sb.acquire();
                    System.out.println("B");
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sc.release();
                }
            }

        }
    }

    private static class PrintCThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    sc.acquire();
                    System.out.println("C");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sa.release();
                }
            }
        }
    }

    public static void main(String... args) {
        new PrintAThread().start();
        new PrintBThread().start();
        new PrintCThread().start();
    }
}
