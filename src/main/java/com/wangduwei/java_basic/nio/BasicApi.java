package com.wangduwei.java_basic.nio;

import java.nio.ByteBuffer;

/**
 * <p>NIO基础API
 *
 * @auther : wangduwei
 * @since : 2019/10/29  10:30
 **/
public class BasicApi {

    public static void main(String[] args) {

    }

    /**
     * 如何获取一个Buffer
     */
    public ByteBuffer getBuffer(int type) {
        switch (type) {
            case 0:
                byte[] byteArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
                ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
                return byteBuffer;
            case 1:
                ByteBuffer byteBuffer1 = ByteBuffer.allocate(100);//Java内存
                return byteBuffer1;
            case 2:
                ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(100);//物理内存
                return byteBuffer2;
        }
        return null;
    }

    public void apiDemo() {
        ByteBuffer byteBuffer = getBuffer(0);
        printString("capacity = " + byteBuffer.capacity());//容量
        printString("limit = " + byteBuffer.limit());//默认的限制
        byteBuffer.limit(7);
        printString("limit = " + byteBuffer.limit());//修改后的限制

        printString("position = " + byteBuffer.position());
        byteBuffer.position(5);
//        byteBuffer.

    }


    private void printString(String string) {
        System.out.println(string);
    }

}
