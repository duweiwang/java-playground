package com.wangduwei.pattern.pattern.impl;


import com.wangduwei.pattern.pattern.AbstractStrategyRouter;
import com.wangduwei.pattern.pattern.StrategyHandler;
import com.wangduwei.pattern.pattern.StrategyMapper;

/**
 * 上一层的 Handler（{@code implements StrategyHandler}），
 * 同时是下一层的 Router（{@code extends AbstractStrategyRouter}）。
 *
 * @author vergilyn
 * @since 2021-01-27
 */
public class _V1_0 extends AbstractStrategyRouter<String, Integer> implements StrategyHandler<String, Integer> {

	public _V1_0() {
		super();
	}

	public static _V1_0 newInstance(){
		return new _V1_0();
	}

	@Override
	protected StrategyMapper<String, Integer> registerStrategyMapper() {
		// 通过 if-else 实现获取到对应的策略处理者
		// VFIXME 2021-01-27 如果增加`A3`，那么需要修改此处的代码，违背了 Open-Close Principle 原则。
		return new StrategyMapper<String, Integer>() {
			@Override
			public StrategyHandler<String, Integer> get(String param) {
				if (param.contains("_1")){
					return _V1_1.newInstance();
				}

				if (param.contains("_2")){
					return _V1_2.newInstance();
				}

				return Root.DEFAULT_STRATEGY_HANDLER;
			}
		};
	}

	@Override
	public Integer apply(String param) {
		System.out.println(this.getClass().getSimpleName() + "#apply()");

		// 向下传递
		Integer rs = applyStrategy(param);

		return rs + param.length();
	}
}
