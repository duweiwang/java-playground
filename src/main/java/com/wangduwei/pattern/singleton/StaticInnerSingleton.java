package com.wangduwei.pattern.singleton;

/**
 * final保证线程安全
 * 内部类懒加载
 */
class StaticInnerSingleton {  
    private StaticInnerSingleton() {}  
      
    private static class HolderClass {  
    	//静态实例属于类，所有HolderClass对象共享一个Singleton实例，
    	//保证线程安全
        private final static StaticInnerSingleton instance = new StaticInnerSingleton();  
    }  
      
    public static StaticInnerSingleton getInstance() {  
        return HolderClass.instance;  
        //初始化类时才初始化外部类对象：懒加载
    }  
}  
