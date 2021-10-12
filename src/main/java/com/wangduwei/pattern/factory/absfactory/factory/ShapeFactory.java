package com.wangduwei.pattern.factory.absfactory.factory;

import com.wangduwei.pattern.factory.absfactory.color.Color;
import com.wangduwei.pattern.factory.absfactory.shap.Circle;
import com.wangduwei.pattern.factory.absfactory.shap.Rectangle;
import com.wangduwei.pattern.factory.absfactory.shap.Shape;
import com.wangduwei.pattern.factory.absfactory.shap.Square;

public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}