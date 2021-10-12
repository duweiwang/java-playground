package com.wangduwei.pattern.factory.absfactory.shap;

public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}