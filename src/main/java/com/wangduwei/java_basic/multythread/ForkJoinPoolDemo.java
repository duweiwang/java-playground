package com.wangduwei.java_basic.multythread;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author : wangduwei
 * @date : 2020/5/24
 * @description :
 */
public class ForkJoinPoolDemo {
    static int[] nums = new int[1_000_000];

    static final int MAX_NUM = 50_000;

    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
    }

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        AddTaskRet task = new AddTaskRet(0, nums.length);
        fjp.execute(task);

        long result = task.join();
        System.out.print(result);
    }


    static class AddTask extends RecursiveAction {
        int start, end;

        public AddTask(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0l;
                for (int i = start; i < end; i++)
                    sum += nums[i];
            } else {
                int middle = start + (end - start) / 2;
                AddTask sub1 = new AddTask(start, middle);
                AddTask sub2 = new AddTask(middle, end);
                sub1.fork();
                sub2.fork();
            }
        }
    }

    static class AddTaskRet extends RecursiveTask<Long> {
        private static final long serialVersionUID = 1L;
        int start, end;

        public AddTaskRet(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                    return sum;
                }
            }

            int middle = start + (end - start) / 2;

            AddTaskRet sub1 = new AddTaskRet(start, middle);
            AddTaskRet sub2 = new AddTaskRet(middle, end);
            sub1.fork();
            sub2.fork();

            return sub1.join() + sub2.join();
        }
    }

}
