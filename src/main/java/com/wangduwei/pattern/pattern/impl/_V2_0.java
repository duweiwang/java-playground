package com.wangduwei.pattern.pattern.impl;


import com.wangduwei.pattern.pattern.AbstractStrategyRouter;
import com.wangduwei.pattern.pattern.StrategyHandler;
import com.wangduwei.pattern.pattern.StrategyMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 上一层的 Handler（{@code implements StrategyHandler}），
 * 同时是下一层的 Router（{@code extends AbstractStrategyRouter}）。
 *
 * @author vergilyn
 * @since 2021-01-27
 *
 * @see _V1_0
 */
public class _V2_0 extends AbstractStrategyRouter<String, Integer> implements StrategyHandler<String, Integer> {

	private final Map<String, StrategyHandler<String, Integer>> handlerMap;

	public _V2_0() {
		super();

		handlerMap = new HashMap<>();

		// VFIXME 2021-01-27 同样的问题，如果增加`B3`，也需要修改此处的代码
		handlerMap.put("_1", _V2_1.newInstance());
		handlerMap.put("_2", _V2_2.newInstance());
	}

	public static _V2_0 newInstance(){
		return new _V2_0();
	}

	@Override
	protected StrategyMapper<String, Integer> registerStrategyMapper() {
		// 通过 Map 实现获取到对应的策略处理者
		// 相比if-else，Map 性能更好（可以保存映射）、适应更复杂的分发逻辑
		return new StrategyMapper<String, Integer>() {
			@Override
			public StrategyHandler<String, Integer> get(String param) {
				if (param.length() < 2){
					return Root.DEFAULT_STRATEGY_HANDLER;
				}

				StrategyHandler<String, Integer> handler = handlerMap.get(param.substring(1, 2));
				if (handler != null){
					return handler;
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
