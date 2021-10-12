package com.wangduwei.java_basic.collection.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>队列的相关操作
 *
 * @auther: wangduwei
 * @since: 2019/7/25
 **/
public class QueueApi {
    public static void main(String[] args) {
        QueueApi.testQueue();
        QueueApi.testDequeue();

        QueueApi.containsTest();

        QueueApi.testQueue2();
    }

    public static void testQueue2(){
        Queue<String> queue = new LinkedList<>();

        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.remove("1");
        queue.peek();
        queue.remove();
    }


    public static void testQueue() {
        Deque<String> deque = new LinkedList<>();
        //队尾追加
        deque.add("add-1");
        deque.add("add-2");
        deque.add("add-3");
        //
        deque.addFirst("addFirst-1");
        deque.addLast("addLast-1");

        deque.offer("offer-1");
        deque.offer("offer-2");
        deque.offer("offer-3");

        deque.offerFirst("offerFirst-1");
        deque.offerLast("offerLast-1");

        while (!deque.isEmpty()) {
            deque.poll();
        }
    }

    public static void testDequeue() {
        Deque<String> deque = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            if (deque.size() < 10) {
                deque.add(i + "");
            } else {
                deque.poll();
                deque.add(i + "");
            }
        }
    }

    public static void containsTest() {
        Queue<TestObject> queue = new LinkedList<>();
        queue.offer(new TestObject("sss"));

        TestObject two = new TestObject("sss");
        if (!queue.contains(two)) {
            queue.add(two);
        }

    }

    public static class TestObject {
        public String name;
        public TestObject(String name){
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this){
                return true;
            }
            if (o instanceof TestObject){
                TestObject t = ((TestObject) o);
                if (t.name.equals(name)){
                    return true;
                }
            }
            return false;
        }
    }
}
