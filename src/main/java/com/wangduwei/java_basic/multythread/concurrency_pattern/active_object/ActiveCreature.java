package com.wangduwei.java_basic.multythread.concurrency_pattern.active_object;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 方法的调用是同步的，执行是异步的
 *
 * 继承此类的子类都有自己的线程控制去执行方法
 *
 */
public abstract class ActiveCreature {

    private BlockingQueue<Runnable> requests;

    private String name;

    private Thread thread; // Thread of execution.

    private int status; // status of the thread of execution.

    /**
     * Constructor and initialization.
     */
    protected ActiveCreature(String name) {
        this.name = name;
        this.status = 0;
        this.requests = new LinkedBlockingQueue<>();
        thread = new Thread(() -> {
            boolean infinite = true;
            while (infinite) {
                try {
                    requests.take().run();
                } catch (InterruptedException e) {
                    if (this.status != 0) {
                        System.out.println(String.format("Thread was interrupted. --> {}", e.getMessage()));
                    }
                    infinite = false;
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
    }

    /**
     * Eats the porridge.
     * @throws InterruptedException due to firing a new Runnable.
     */
    public void eat() throws InterruptedException {
        requests.put(() -> {
            System.out.println(String.format("{} is eating!",name()));
            System.out.println(String.format("{} has finished eating!",name()));
        });
    }

    /**
     * Roam the wastelands.
     * @throws InterruptedException due to firing a new Runnable.
     */
    public void roam() throws InterruptedException {
        requests.put(() ->
                System.out.println(String.format("{} has started to roam in the wastelands.",name()))
        );
    }

    /**
     * Returns the name of the creature.
     * @return the name of the creature.
     */
    public String name() {
        return this.name;
    }

    /**
     * Kills the thread of execution.
     * @param status of the thread of execution. 0 == OK, the rest is logging an error.
     */
    public void kill(int status) {
        this.status = status;
        this.thread.interrupt();
    }

    /**
     * Returns the status of the thread of execution.
     * @return the status of the thread of execution.
     */
    public int getStatus() {
        return this.status;
    }
}
