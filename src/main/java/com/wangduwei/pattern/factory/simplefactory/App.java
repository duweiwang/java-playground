package com.wangduwei.pattern.factory.simplefactory;

import com.wangduwei.pattern.factory.simplefactory.chart.Chart;

/**
 * 编程入口
 */
class App {
    public static void main(String args[]) {
        Chart chart;
        chart = ChartFactory.getChart("histogram"); //通过静态工厂方法创建产品  
        chart.display();
    }
}  
