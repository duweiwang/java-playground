package com.wangduwei.java_basic.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author : wangduwei
 * @date : 2020/8/29
 * @description :
 */
class RandomAccessFileExample {

    public static void main(String[] args) throws IOException {
        // out.txt 此时的文件内容为 "123456789"
        RandomAccessFile file = new RandomAccessFile("D:\\out.txt", "rw");

        System.out.println("pointer: " + file.getFilePointer()); // 输出 pointer: 0
        System.out.println("char: " + (char) file.read()); // 输出 char: 1
        System.out.println("pointer: " + file.getFilePointer()); // 输出 pointer: 1

        file.seek(4); // 下标从 0 开始的，让其指向第 5 个字节

        System.out.println("pointer: " + file.getFilePointer()); // 输出 pointer: 4
        System.out.println("char: " + (char) file.read()); // 输出 char: 5
        System.out.println("pointer: " + file.getFilePointer()); // 输出 pointer: 5
        file.close();
    }
}
