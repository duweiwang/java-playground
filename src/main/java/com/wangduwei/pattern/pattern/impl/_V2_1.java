package com.wangduwei.pattern.pattern.impl;


import com.wangduwei.pattern.pattern.StrategyHandler;

/**
 * 叶子节点只实现 Handler 接口而无需继承 Router 抽象类（无需再向下委托）。
 *
 * @author vergilyn
 * @since 2021-01-27
 */
public class _V2_1 implements StrategyHandler<String, Integer> {

	public static _V2_1 newInstance(){
		return new _V2_1();
	}

	@Override
	public Integer apply(String param) {
		System.out.println(this.getClass().getSimpleName() + "#apply()");

		return param.length() * 10;
	}
}
