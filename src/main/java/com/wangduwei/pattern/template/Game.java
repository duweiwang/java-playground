package com.wangduwei.pattern.template;

/**
 * <p> 抽象父类定义骨架，子类实现
 *
 * @auther : wangduwei
 * @since : 2019/9/6  13:05
 **/
public abstract class Game {
    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();

    //模板
    public final void play() {

        //初始化游戏
        initialize();

        //开始游戏
        startPlay();

        //结束游戏
        endPlay();
    }
}
