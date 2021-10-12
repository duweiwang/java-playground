package com.wangduwei.pattern.pattern.impl;


import com.wangduwei.pattern.pattern.AbstractStrategyRouter;
import com.wangduwei.pattern.pattern.StrategyHandler;
import com.wangduwei.pattern.pattern.StrategyMapper;

/**
 * 根节点只继承 Router 抽象类。
 *
 * @author vergilyn
 * @since 2021-01-27
 */
public class Root extends AbstractStrategyRouter<String, Integer> {

	protected static final StrategyHandler<String, Integer> DEFAULT_STRATEGY_HANDLER = t -> {
		System.out.println("default-handler#apply()");
		// 不再向下传递，结束责任链
		return 0;
	};

	public Root() {
		super();
	}

	@Override
	protected StrategyMapper<String, Integer> registerStrategyMapper() {
		return param -> {
			if (param.startsWith("V1_")){
				return _V1_0.newInstance();
			}

			if (param.startsWith("V2_")){
				return _V2_0.newInstance();
			}

			return DEFAULT_STRATEGY_HANDLER;
		};
	}
}
