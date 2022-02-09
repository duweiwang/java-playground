package com.wangduwei.java_basic.multythread.master_worker;

/**
 * https://www.cnblogs.com/binarylei/p/8999712.html
 *
 *           client
 *             |
 *          master
 *         /      \
 *        /        \
 *    worker      worker
 */
public class Client {

    public static void main(String[] args) {
        Master master = new Master(new Worker(), 1);

        for (int i = 1; i <= 100; i++) {
            master.submit(new Task(i, "task-" + i ,i));
        }

        master.execute();

        long t1 = System.currentTimeMillis();
        while (true) {
            if (master.isComplete()) {
                long t = System.currentTimeMillis() - t1;
                System.out.printf("执行结果：%s；执行时间：%s", master.getResult(), t);
                break;
            }
        }
    }

}
