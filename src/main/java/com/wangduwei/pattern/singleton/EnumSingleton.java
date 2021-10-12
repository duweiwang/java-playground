package com.wangduwei.pattern.singleton;

/**
 * @auther: davewang
 * @since: 2019/7/15
 **/
public enum EnumSingleton {
    INSTANCE;

    @Override
    public String toString() {
        return getDeclaringClass().getCanonicalName() + "@" + hashCode();
    }
}
