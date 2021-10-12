package com.wangduwei.java_basic.multythread.util;

import java.util.concurrent.Phaser;

/**
 * @author : wangduwei
 * @date : 2020/5/17
 * @description :
 */
public class PhaserDemo {
    public static void main(String[] args) {
        int runnerNum = 4;

        GamePhaser gamePhaser = new GamePhaser();
        /**
         * 注册一次表示phaser维护的线程个数
         */
        gamePhaser.register();
        for (int i = 0; i < runnerNum; i++) {
            /**
             * 注册一次表示phaser维护的线程个数
             */
            gamePhaser.register();
            new Thread(new Runner(gamePhaser)).start();

        }
        /**
         * 后续阶段主线程就不参加了
         */
        gamePhaser.arriveAndDeregister();

    }

    public static class Runner implements Runnable {

        private Phaser phaser;

        public Runner(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            /**
             * 参加预赛
             */
            System.out.println("选手-" + Thread.currentThread().getName() + ":参加预赛");
            /**
             * 预赛阶段-----执行这个方法的话会等所有的选手都完成了之后再继续下面的方法
             */
            phaser.arriveAndAwaitAdvance();
            /**
             * 参加初赛
             */
            System.out.println("选手-" + Thread.currentThread().getName() + ":参加初赛");
            /**
             * 初赛阶段-----执行这个方法的话会等所有的选手都完成了之后再继续下面的方法
             */
            phaser.arriveAndAwaitAdvance();
            /**
             * 参加决赛
             */
            System.out.println("选手-" + Thread.currentThread().getName() + ":参加决赛");
            /**
             * 决赛阶段-----执行这个方法的话会等所有的选手都完成了之后再继续下面的方法
             */
            phaser.arriveAndAwaitAdvance();
        }
    }

    /**
     * 比赛阶段器
     */
    public static class GamePhaser extends Phaser {

        /**
         * 当一个阶段的所有线程都到达时 , 执行该方法, 此时 phase自动加1
         *
         * @param phase
         * @param registeredParties
         * @return
         */
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("预赛完成");
                    return false;
                case 1:
                    System.out.println("初赛完成");
                    return false;
                case 2:
                    System.out.println("决赛完成");
                    return false;
                default:
                    return true;
            }
        }
    }
}
