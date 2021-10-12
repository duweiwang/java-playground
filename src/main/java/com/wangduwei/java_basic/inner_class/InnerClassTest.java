package com.wangduwei.java_basic.inner_class;

/**
 * 内部类的几种形式
 * 通过javap反编译class文件发现，方法内部类持有外部类引用！！
 */
public class InnerClassTest {

    public void function() {

        class ClassInFuntion {
            public void say() {
                System.out.println("我是局部内部咧");
            }
        }
        new ClassInFuntion().say();
    }

    class Inner {
        public void say() {
            System.out.println("我是成员内部类");
        }
    }

    static class StaticInner {
        public void say() {
            System.out.println("我是静态内部类");
        }
    }

    public static void main(String[] args) {
        StaticInner staticInner = new StaticInner();
        staticInner.say();

        InnerClassTest innerClassTest = new InnerClassTest();
        innerClassTest.function();

        InnerClassTest.Inner inner = innerClassTest.new Inner();
        inner.say();
    }
}
