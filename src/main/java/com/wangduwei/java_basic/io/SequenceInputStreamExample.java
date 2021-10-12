package com.wangduwei.java_basic.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Vector;

/**
 * @author : wangduwei
 * @date : 2020/8/29
 * @description :
 */
class SequenceInputStreamExample {
    public static void merge2() throws IOException {
        InputStream input1 = new FileInputStream("D:\\test\\1.txt");
        InputStream input2 = new FileInputStream("D:\\test\\2.txt");

        SequenceInputStream sequenceInputStream = new SequenceInputStream(input1, input2);
        int data = sequenceInputStream.read();
        while (data != -1) {
            System.out.print((char) data);
            data = sequenceInputStream.read();
        }
    }

    public static void merge3() throws IOException {
        InputStream input1 = new FileInputStream("D:\\test\\1.txt");
        InputStream input2 = new FileInputStream("D:\\test\\2.txt");
        InputStream input3 = new FileInputStream("D:\\test\\3.txt");

        Vector<InputStream> streams = new Vector<>();
        streams.add(input1);
        streams.add(input2);
        streams.add(input3);
        // 利用 Vector 对象的 elements() 方法返回 enumeration 对象
        SequenceInputStream sequenceInputStream = new SequenceInputStream(streams.elements());
        int data = sequenceInputStream.read();
        while (data != -1) {
            System.out.print((char) data);
            data = sequenceInputStream.read();
        }
    }

    public static void merge2Style2() throws IOException {
        InputStream input1 = new FileInputStream("D:\\test\\1.txt");
        InputStream input2 = new FileInputStream("D:\\test\\2.txt");
        InputStream input3 = new FileInputStream("D:\\test\\3.txt");

        SequenceInputStream sequenceInputStream1 = new SequenceInputStream(input1, input2);
        SequenceInputStream sequenceInputStream = new SequenceInputStream(sequenceInputStream1, input3);
        int data = sequenceInputStream.read();
        while (data != -1) {
            System.out.print((char)data);
            data = sequenceInputStream.read();
        }
        sequenceInputStream.close();
    }
}
