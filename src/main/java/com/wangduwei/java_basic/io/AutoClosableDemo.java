package com.wangduwei.java_basic.io;

/**
 * @author : wangduwei
 * @date : 2020/8/29
 * @description :
 */
class AutoClosableDemo {

    /**
     * MyAutoClosable-doSth
     * MyAutoClosable-close
     * 自动调用close方法
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try (MyAutoClosable a = new MyAutoClosable()) {
            a.doSth();
        }
    }


    public static class MyAutoClosable implements AutoCloseable {

        public void doSth() {
            System.out.println("MyAutoClosable-doSth");
        }

        @Override
        public void close() throws Exception {
            System.out.println("MyAutoClosable-close");
        }
    }
}
