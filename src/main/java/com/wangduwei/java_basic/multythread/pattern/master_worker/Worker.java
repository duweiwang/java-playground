package com.wangduwei.java_basic.multythread.pattern.master_worker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Worker implements Runnable {

    private ConcurrentLinkedDeque<Task> taskQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    @Override
    public void run() {
        while (true) {
            Task task = taskQueue.poll();
            if (task == null) break;

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果集
            resultMap.put(Integer.toString(task.getId()), handle(task));
        }
    }

    private Object handle(Task task) {
        return task.getCount();
    }

    public void setTaskQueue(ConcurrentLinkedDeque<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
