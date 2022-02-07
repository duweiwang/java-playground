package com.wangduwei.pattern.singleton;

/**
 * 双重校验锁
 */
public class DoubleLock {

	//禁止指令重排，new关键字不满足原子性
	private static volatile DoubleLock instance ;
	
	private DoubleLock(){
	}
	
	public static DoubleLock getInstance(){
		if( instance == null){
			synchronized (DoubleLock.class) {
				if (instance == null ) {
					instance = new DoubleLock();
				}
			}
		}
		return instance;
	}
}
