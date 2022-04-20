package com.wangduwei.java_basic.multythread.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorServiceDemo s = new ScheduledExecutorServiceDemo();
        //调度任务
        s.schedule();
        //周期性调度
        s.scheduleAtFixedRate();
        //任务超时
        s.scheduleAtFixedRate2();
        //周期延迟
        s.scheduleWithFixedDelay();
        //其他配置项
        s.config();
    }

    /**
     * 调度任务
     */
    private void schedule() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                new ScheduledThreadPoolExecutor(2);
        //2秒后执行runnable任务
        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("This is runable1 task");
        }, 2, TimeUnit.SECONDS);

        //提交一个2秒后才执行的runnable任务
        //既然runnable无法返回结果,为什么还要有Future呢,因为我们可以通过Future进行取消任务等操作
        ScheduledFuture<?> runnableFuture = scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("This is runable2 task");
        }, 2, TimeUnit.SECONDS);
        //取消任务
        runnableFuture.cancel(true);

        //休眠3秒,确保上面的任务都被执行完
        mySleep(3);
        System.out.println("========================");
    }

    /**
     * 周期性调度
     */
    private void scheduleAtFixedRate() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                new ScheduledThreadPoolExecutor(2);
        //提交延迟1秒执行,周期为2秒的runnable任务,虽然runnable没有返回结果,但是可以通过runnable取消任务
        ScheduledFuture<?> runnableFuture = scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            System.out.println("This is runable task running " + Thread.currentThread().getName());
        }, 1, 2, TimeUnit.SECONDS);

        //休眠8秒
        mySleep(8);
        //取消该循坏任务
        runnableFuture.cancel(true);
    }

    /**
     * 模拟超时任务，超时的任务，下一个周期将立即执行
     */
    private void scheduleAtFixedRate2() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
        AtomicLong atomicLong = new AtomicLong(0L);
        //提交初始延迟1秒执行,固定周期为2秒的runnable任务
        ScheduledFuture<?> runnableFuture = scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            //记录当前时间
            Long current = System.currentTimeMillis();
            //判断是否为第一次运行
            if (atomicLong.get() == 0) {
                atomicLong.set(current);
                System.out.printf("first running [%d]\n", atomicLong.get());
            } else {
                //记录与上次的间隔时间
                System.out.printf("running time:[%d]\n", current - atomicLong.get());
            }
            //将当前时间保存
            atomicLong.set(current);
            //模拟超过固定周期时间
            mySleep(5);
        }, 1, 2, TimeUnit.SECONDS);
    }

    /**
     * 可以看出来，无论你的任务执行多久，在任务执行完毕之后都会延迟一定时间才进入下一周期。
     */
    private void scheduleWithFixedDelay() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                new ScheduledThreadPoolExecutor(2);
        AtomicLong atomicLong = new AtomicLong(0L);
        //提交初始延迟1秒执行,延迟为2秒的runnable任务
        ScheduledFuture<?> runnableFuture = scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            //记录当前时间
            Long current = System.currentTimeMillis();
            //判断是否为第一次运行
            if (atomicLong.get() == 0) {
                atomicLong.set(current);
                System.out.printf("first running [%d]\n", atomicLong.get());
            } else {
                //记录与上次的间隔时间
                System.out.printf("running time:[%d]\n", current - atomicLong.get());
            }
            //将当前时间保存
            atomicLong.set(current);
            //模拟超过固定周期时间
            mySleep(5);
        }, 1, 2, TimeUnit.SECONDS);
    }

    /**
     * 一些配置项：setContinueExistingPeriodicTasksAfterShutdownPolicy
     */
    private void config(){
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                new ScheduledThreadPoolExecutor(2);
        //提交固定周期任务
        ScheduledFuture<?> runnableFuture = scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            System.out.println("This is runable task running "+Thread.currentThread().getName());
        }, 1,2, TimeUnit.SECONDS);

        //默认情况关闭线程池后是不允许继续执行固定周期任务的，所有输出false
        System.out.println(scheduledThreadPoolExecutor.getContinueExistingPeriodicTasksAfterShutdownPolicy());
        //设置为true
        scheduledThreadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        //休眠1200毫秒，确保任务被执行
        mySleep(1200);
        //关闭线程池
        scheduledThreadPoolExecutor.shutdown();
        //休眠2000毫秒后查看线程池状态
        mySleep(2000);
        //线程池的状态
        System.out.println("isShutdown:"+scheduledThreadPoolExecutor.isShutdown());
        System.out.println("isTerminating:"+scheduledThreadPoolExecutor.isTerminating());
        System.out.println("isTerminated:"+scheduledThreadPoolExecutor.isTerminated());
    }


    private static void mySleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
