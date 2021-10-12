package com.wangduwei.pattern.singleton;

/**
 * 懒汉延迟初始化
 */
public class LazyMan {
	private static LazyMan instance = null; // 延迟加载

	private LazyMan() {
	}

	synchronized public static LazyMan getInstance() {
		if (instance == null) {
			instance = new LazyMan();
		}
		return instance;
	}
}
