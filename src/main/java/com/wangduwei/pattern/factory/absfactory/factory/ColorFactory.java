package com.wangduwei.pattern.factory.absfactory.factory;

import com.wangduwei.pattern.factory.absfactory.color.Blue;
import com.wangduwei.pattern.factory.absfactory.color.Color;
import com.wangduwei.pattern.factory.absfactory.color.Green;
import com.wangduwei.pattern.factory.absfactory.color.Red;
import com.wangduwei.pattern.factory.absfactory.shap.Shape;

public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}
