package com.wangduwei.java_basic.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * <p>引用队列和弱引用的使用
 *
 * 弱引用关联了引用队列，触发GC后，队列不为null，说明弱引用被加入引用队列
 *
 * @author : wangduwei
 * @since : 2020/7/21  11:23
 **/
class ReferenceTest {
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println("-----"+ object);
        ReferenceQueue<Object> queue = new ReferenceQueue();
        WeakReference<Object> weakReference = new WeakReference<>(object, queue);
        object = null;
        System.out.println("-----before gc = " + queue.poll());
        System.gc();
        System.runFinalization();
        System.out.println("-----after gc = " + queue.poll());
//output:
//-----java.lang.Object@15db9742
//-----before gc = null
//-----after gc = java.lang.ref.WeakReference@6d06d69c

    }
}
