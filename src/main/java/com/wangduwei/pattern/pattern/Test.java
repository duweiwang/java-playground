package com.wangduwei.pattern.pattern;

import com.wangduwei.pattern.pattern.impl.Root;

/**
 * @author 杜伟
 * @date 2021/7/16 7:44 PM
 */
public class Test {

    public void basic() {
        Root root = new Root();

        root.applyStrategy("V1_");
    }


    public static void main(String[] args) {
        new Test().basic();
    }
}
