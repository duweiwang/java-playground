package com.wangduwei.pattern.template;

/**
 * <p>
 *
 * @auther : wangduwei
 * @since : 2019/9/6  13:06
 **/
public class Cricket extends Game {

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }
}
