package com.wangduwei.pattern.factory.absfactory.factory;

import com.wangduwei.pattern.factory.absfactory.color.Color;
import com.wangduwei.pattern.factory.absfactory.shap.Shape;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}