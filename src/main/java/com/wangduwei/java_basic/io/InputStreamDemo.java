package com.wangduwei.java_basic.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * https://www.cnblogs.com/czwbig/p/10007289.html
 *
 * @author : wangduwei
 * @date : 2020/8/28
 * @description :
 */
class InputStreamDemo {
    /**
     * 为什么输出：123456789678
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        byte[] b = "123456789".getBytes();
        InputStream inputStream = new ByteArrayInputStream(b);
        byte[] bytes = new byte[4]; // 每次只读取 4 个字节
        int data = inputStream.read(bytes);
        while (data != -1) {
            System.out.print(new String(bytes));
            data = inputStream.read(bytes);
        }
    }

    /**
     * mark和reset方法，打标记
     *
     * 第一次打印：
     * 123456789
     * 在经过 mark 和 reset 之后从 mark 位置开始打印：
     * 789
     * @throws IOException
     */
    public static void testMarkAndReset() throws IOException {
        InputStream input = new ByteArrayInputStream("123456789".getBytes());
        System.out.println("第一次打印：");

        int count = 0;// 计算是第几次读取，将在第二次读取时做标记；
        byte[] bytes = new byte[3]; // 每次只读取 3 个字节
        int data = input.read(bytes);
        while (data != -1) {
            System.out.print(new String(bytes));
            if (++count == 2) { // 在第二轮读取，即读到数字 4 的时候，做标记
                input.mark(16); // 从 mark 点开始再过 readlimit 个字节，mark 将失效
            }
            data = input.read(bytes);
        }

        input.reset();
        System.out.println("\n在经过 mark 和 reset 之后从 mark 位置开始打印：");
        data = input.read(bytes);
        while (data != -1) {
            System.out.print(new String(bytes));
            data = input.read(bytes);
        }
    }

}
