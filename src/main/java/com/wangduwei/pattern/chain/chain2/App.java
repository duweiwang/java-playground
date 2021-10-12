package com.wangduwei.pattern.chain.chain2;

/**
 * 责任链---简单的串起来
 */
public class App {

    public static void main(String[] args) {
        AbstractHandler A = new ConcreteHandlerA();
        AbstractHandler B = new ConcreteHandlerB();
        AbstractHandler Z = new ConcreteHandlerZ();

        A.setHandler(B);
        B.setHandler(Z);
        A.handleRequest("Z");
    }
}
