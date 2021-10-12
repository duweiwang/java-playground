package com.wangduwei.test;

/**
 * <p>变量初始化顺序问题
 *
 * @author : wangduwei
 * @since : 2020/1/3  13:56
 **/
public class Test {
    //1.初始化变量-走构造函数-变量被初始化
    private static Test test = new Test();
    public static int a ;
    //这里再次初始化被置为0
    public static int b = 0;

    private Test(){
        a++;
        b++;
    }
    public static Test getTest(){
        return test;
    }

    public static void main(String[] args) {
        Test test = Test.getTest();
        System.out.print("a = " + test.a);
        System.out.println();
        System.out.print("b = " + test.b);
    }

}
