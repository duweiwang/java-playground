package com.wangduwei.pattern.singleton;

/**
 * 饿汉模式：提前初始化了
 */
public class HungryMan {
	
	private static final HungryMan instance = new HungryMan();
		
	public static HungryMan getInstance(){
		return instance;
	} 

	private HungryMan(){}

}
