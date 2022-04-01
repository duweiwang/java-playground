package com.wangduwei.java_basic.multythread.concurrency_pattern.master_worker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Master {
    //1. 应该有一个容器存放任务列表，这个容器需要支持高并发操作
    private ConcurrentLinkedDeque<Task> taskQueue = new ConcurrentLinkedDeque<>();

    //2. 应该有一个容器存放worker
    private HashMap<String, Thread> workers = new HashMap<>();

    //3. 应该有一个容器存放结果集，这个容器需要支持高并发操作
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    //4. 构造函数
    public Master (Worker worker, int threadCount) {
        //将任务列表和结果集传递给worker
        worker.setTaskQueue(taskQueue);
        worker.setResultMap(resultMap);
        //初始化worder列表
        for (int i = 0; i < threadCount; i++) {
            workers.put("worker-" + i, new Thread(worker));
        }
    }

    public Master (Worker worker) {
        this(worker, Runtime.getRuntime().availableProcessors());
    }

    //5. 提交任务
    public void submit (Task task) {
        taskQueue.add(task);
    }

    //6. 执行方法 开启所有的线程
    public void execute () {
        for(Map.Entry<String, Thread> me : workers.entrySet()) {
            me.getValue().start();
        }
    }

    //7. 判断是否执行完毕
    public boolean isComplete () {
        for(Map.Entry<String, Thread> me : workers.entrySet()) {
            if (me.getValue().getState() != Thread.State.TERMINATED)
                return false;
        }
        return true;
    }

    //8. 处理结果集
    public int getResult () {
        int ret = 0;
        for(Map.Entry<String, Object> me : resultMap.entrySet()) {
            ret += (int)me.getValue();
        }
        return ret;
    }
}
