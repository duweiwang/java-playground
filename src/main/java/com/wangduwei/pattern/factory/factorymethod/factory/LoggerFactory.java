package com.wangduwei.pattern.factory.factorymethod.factory;

import com.wangduwei.pattern.factory.factorymethod.logger.Logger;

//日志记录器工厂接口：抽象工厂
public interface LoggerFactory {
    Logger createLogger();
}  