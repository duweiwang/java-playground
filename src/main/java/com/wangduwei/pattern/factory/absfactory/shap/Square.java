package com.wangduwei.pattern.factory.absfactory.shap;

public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}