package com.wangduwei.pattern.factory.factorymethod;

import com.wangduwei.pattern.factory.factorymethod.factory.FileLoggerFactory;
import com.wangduwei.pattern.factory.factorymethod.factory.LoggerFactory;
import com.wangduwei.pattern.factory.factorymethod.logger.Logger;

class App {
    public static void main(String args[]) {  
        LoggerFactory factory;
        Logger logger;
        factory = new FileLoggerFactory(); //可引入配置文件实现
        logger = factory.createLogger();  
        logger.writeLog();  
    }  
}  
