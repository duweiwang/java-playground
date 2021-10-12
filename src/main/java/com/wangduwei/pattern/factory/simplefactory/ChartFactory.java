package com.wangduwei.pattern.factory.simplefactory;

import com.wangduwei.pattern.factory.simplefactory.chart.Chart;
import com.wangduwei.pattern.factory.simplefactory.chart.HistogramChart;
import com.wangduwei.pattern.factory.simplefactory.chart.LineChart;
import com.wangduwei.pattern.factory.simplefactory.chart.PieChart;

//图表工厂类：工厂类
class ChartFactory {
    //静态工厂方法
    public static Chart getChart(String type) {
        Chart chart = null;
        if (type.equalsIgnoreCase("histogram")) {
            chart = new HistogramChart();
            System.out.println("初始化设置柱状图！");
        } else if (type.equalsIgnoreCase("pie")) {
            chart = new PieChart();
            System.out.println("初始化设置饼状图！");
        } else if (type.equalsIgnoreCase("line")) {
            chart = new LineChart();
            System.out.println("初始化设置折线图！");
        }
        return chart;
    }
}  