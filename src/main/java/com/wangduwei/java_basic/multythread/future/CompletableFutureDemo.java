package com.wangduwei.java_basic.multythread.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author : wangduwei
 * @date : 2020/5/23
 * @description :
 */
public class CompletableFutureDemo {


    public static void main(String[] args) {
        CompletableFuture<Double> futureTm = CompletableFuture.supplyAsync(new Supplier<Double>() {
            @Override
            public Double get() {
                return priceOfTianMao();
            }
        });
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(new Supplier<Double>() {
            @Override
            public Double get() {
                return priceOfJD();
            }
        });
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(new Supplier<Double>() {
            @Override
            public Double get() {
                return priceOfTaoBao();
            }
        });

        CompletableFuture.allOf(futureJD, futureTB, futureTm).join();
        System.out.println("主线程结束");
        //------------------------

//        CompletableFuture.supplyAsync(new Supplier<Object>() {
//            @Override
//            public Object get() {
//                return priceOfTianMao();
//            }
//        }).thenApply()
    }


    private static double priceOfJD() {
        delayRandom();
        System.out.println("京东价格1.0");
        return 1.0;
    }

    private static double priceOfTaoBao() {
        delayRandom();
        System.out.println("淘宝价格3.0");
        return 3.0;
    }

    private static double priceOfTianMao() {
        delayRandom();
        System.out.println("天猫价格2.0");
        return 2.0;
    }


    private static void delayRandom() {
        int time = new Random().nextInt(50);
        try {
            System.out.println("睡眠" + time + "秒");
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
