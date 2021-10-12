package com.wangduwei.pattern.singleton;

/**
 * 双重校验锁
 */
public class DoubleLock {
	
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
