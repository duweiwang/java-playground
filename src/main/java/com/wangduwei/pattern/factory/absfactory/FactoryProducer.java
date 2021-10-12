package com.wangduwei.pattern.factory.absfactory;

import com.wangduwei.pattern.factory.absfactory.factory.AbstractFactory;
import com.wangduwei.pattern.factory.absfactory.factory.ColorFactory;
import com.wangduwei.pattern.factory.absfactory.factory.ShapeFactory;

/**
 * 工厂的工厂
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("SHAPE")) {
            return new ShapeFactory();
        } else if (choice.equalsIgnoreCase("COLOR")) {
            return new ColorFactory();
        }
        return null;
    }
}