package com.wangduwei.problems;

/**
 * <p>变量初始化顺序问题
 *
 * @author : wangduwei
 * @since : 2020/1/3  13:56
 **/
public class VariableInitOrder {
    //1.初始化变量-走构造函数-变量被初始化
    private static VariableInitOrder test = new VariableInitOrder();
    public static int a ;
    //这里再次初始化被置为0
    public static int b = 0;

    private VariableInitOrder(){
        a++;
        b++;
    }
    public static VariableInitOrder getTest(){
        return test;
    }

    public static void main(String[] args) {
        VariableInitOrder test = VariableInitOrder.getTest();
        System.out.print("a = " + test.a);
        System.out.println();
        System.out.print("b = " + test.b);
    }

}
