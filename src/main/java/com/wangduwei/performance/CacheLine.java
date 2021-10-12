package com.wangduwei.performance;

/**
 * 顺序读写一片内存，由于cpu缓存的存在，将会快于非顺序读写。
 */
public class CacheLine {
    static long[][] arr;

    public static void main(String[] args) {
        arr = new long[1024 * 1024][8];
        long sum = 0;
        // 横向遍历
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked)+ "ms");

        marked = System.currentTimeMillis();
        // 纵向遍历
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum += arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked)+ "ms");
    }
}
