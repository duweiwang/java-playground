package com.wangduwei.pattern.singleton;

/**
 * 静态实例全局唯一
 */
public class StaticSelfInstance {
	
	public static StaticSelfInstance instance = new StaticSelfInstance();
	
	private StaticSelfInstance(){}

}
