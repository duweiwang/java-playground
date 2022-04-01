package com.wangduwei.java_basic.multythread.concurrency_pattern.active_object;

import java.util.ArrayList;
import java.util.List;


/**
 * https://www.infoq.cn/article/java-multithreaded-programming-mode-active-object-part1
 *
 * 分离方法的调用和执行，调用是同步的，执行是异步的
 *
 */
public class Client implements Runnable {

    private static final int NUM_CREATURES = 3;

    public static void main(String[] args) {
        Client app = new Client();
        app.run();
    }

    @Override
    public void run() {
        List<ActiveCreature> creatures = new ArrayList<>();
        try {
            for (int i = 0; i < NUM_CREATURES; i++) {
                creatures.add(new Orc(Orc.class.getSimpleName() + i));
                creatures.get(i).eat();
                creatures.get(i).roam();
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(String.format((e.getMessage())));
            Thread.currentThread().interrupt();
        } finally {
            for (int i = 0; i < NUM_CREATURES; i++) {
                creatures.get(i).kill(0);
            }
        }
    }
}
