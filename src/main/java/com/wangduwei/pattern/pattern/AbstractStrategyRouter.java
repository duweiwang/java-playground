package com.wangduwei.pattern.pattern;

import java.util.Objects;


/**
 * 通用的“策略树“框架，通过树形结构实现分发与委托，每层通过指定的参数进行向下分发委托，直到达到最终的执行者。
 * 该框架包含两个类：{@code StrategyHandler} 和 {@code AbstractStrategyRouter}。 <br/>
 *
 * 其中：通过实现 {@code AbstractStrategyRouter} 抽象类完成对策略的分发，
 * 实现 {@code StrategyHandler} 接口来对策略进行实现。
 * 像是第二层 A、B 这样的节点，既是 Root 节点的策略实现者也是策略A1、A2、B1、B2 的分发者，这样的节点只需要
 * 同时继承 {@code StrategyHandler} 和实现 {@code AbstractStrategyRouter} 接口就可以了。
 *
 * <pre>
 *           +---------+
 *           |  Root   |   ----------- 第 1 层策略入口
 *           +---------+
 *            /       \  ------------- 根据入参 P1 进行策略分发
 *           /         \
 *     +------+      +------+
 *     |  A   |      |  B   |  ------- 第 2 层不同策略的实现
 *     +------+      +------+
 *       /  \          /  \  --------- 根据入参 P2 进行策略分发
 *      /    \        /    \
 *   +---+  +---+  +---+  +---+
 *   |A1 |  |A2 |  |B1 |  |B2 |  ----- 第 3 层不同策略的实现
 *   +---+  +---+  +---+  +---+
 * </pre>
 *
 * @author vergilyn
 * @since 2021-01-27
 */
public abstract class AbstractStrategyRouter<T, R> {

	protected StrategyMapper<T, R> strategyMapper;

	/**
	 * 类初始化时注册分发策略 Mapper
	 */
	public AbstractStrategyRouter() {
		strategyMapper = registerStrategyMapper();
		Objects.requireNonNull(strategyMapper, "strategyMapper cannot be null");
	}

	/**
	 * 执行策略，框架会自动根据策略分发至下游的 Handler 进行处理
	 *
	 * @param param 入参
	 * @return 下游执行者给出的返回值
	 */
	public R applyStrategy(T param) {
		final StrategyHandler<T, R> strategyHandler = strategyMapper.get(param);
		if (strategyHandler != null) {
			return strategyHandler.apply(param);
		}

		// 根据场景，看是用默认的strategy-handler处理，还是抛出异常
		// return defaultStrategyHandler.apply(param);
		throw new IllegalArgumentException("not found strategy-handler!");
	}

	/**
	 * 抽象方法，需要子类实现策略的分发逻辑。
	 *
	 * <p>如果子节点路由逻辑比较简单，可以直接通过 if-else 进行分发。
	 * 当然如果为了更好地性能、适应更复杂的分发逻辑也可以使用 Map 等保存映射。</p>
	 *
	 * @return 分发逻辑 Mapper 对象
	 */
	protected abstract StrategyMapper<T, R> registerStrategyMapper();
}
