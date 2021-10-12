package com.wangduwei.pattern.template;

/**
 * <p>
 *
 * @auther : wangduwei
 * @since : 2019/9/6  13:06
 **/
public class Football extends Game {

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }
}
