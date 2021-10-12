package com.wangduwei.pattern.template;

/**
 * <p>模板方法模式，
 *
 * @auther : wangduwei
 * @since : 2019/9/6  13:07
 **/
public class App {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
