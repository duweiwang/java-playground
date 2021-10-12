package com.wangduwei.java_basic.multythread.consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p> 生产者消费者-阻塞队列实现
 *
 * @author : wangduwei
 * @since : 2020/5/15  10:22
 **/
public class BlockingQueueImpl {

    static Thread consumer, producer;

    static BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(5);

    public static void main(String[] args) {

        consumer = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (blockingQueue.size() < 5){
                        Integer integer = new Random().nextInt(10);
                        System.out.println("放入" + integer.intValue());
                        blockingQueue.add(integer);
                    }
                }
            }
        };

        producer = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (!blockingQueue.isEmpty()){
                        int i = blockingQueue.poll();
                        System.out.println("取出" + i);
                    }
                }
            }
        };

        consumer.start();
        producer.start();
    }

}
