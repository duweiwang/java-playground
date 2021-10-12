package com.wangduwei.pattern.pattern;

/**
 * 策略映射器，根据指定的入参路由到对应的策略处理者。
 *
 * @param <T> 策略的入参类型
 * @param <R> 策略的返回值类型
 */
public interface StrategyMapper<T, R> {
	/**
	 * 根据入参获取到对应的策略处理者。可通过 if-else 实现，也可通过 Map 实现。
	 *
	 * @param param 入参
	 * @return 策略处理者
	 */
	StrategyHandler<T, R> get(T param);
}
