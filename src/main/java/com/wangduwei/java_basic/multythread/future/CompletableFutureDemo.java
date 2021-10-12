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
        return 1.0;
    }

    private static double priceOfTaoBao() {
        delayRandom();
        return 3.0;
    }

    private static double priceOfTianMao() {
        delayRandom();
        return 2.0;
    }


    private static void delayRandom() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
