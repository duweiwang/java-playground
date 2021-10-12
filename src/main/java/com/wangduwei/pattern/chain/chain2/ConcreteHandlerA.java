package com.wangduwei.pattern.chain.chain2;

public class ConcreteHandlerA extends AbstractHandler {
    @Override
    public void handleRequest(String condition) {
        if (condition.equals("A")){
            System.out.println("A处理");
        }else{
            super.getHandler().handleRequest(condition);
        }
    }
}
