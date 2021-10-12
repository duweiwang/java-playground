package com.wangduwei.java_basic.collection.stack;


import java.util.Stack;

/**
 * @author : wangduwei
 * @date : 2020/3/9
 * @description :
 */
public class StackTest {

    public static void main(String[] args) {
        Stack<ListNode> stack = new Stack<>();
        stack.push(new ListNode());
        stack.peek();
        stack.pop();
        //~~~~~~~~~~
        stack.isEmpty();
        //~~~~~~~~~~~
        stack.empty();
    }


    public static class ListNode {

    }

}
