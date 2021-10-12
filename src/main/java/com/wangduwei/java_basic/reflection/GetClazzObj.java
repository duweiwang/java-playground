package com.wangduwei.java_basic.reflection;

/**
 * @desc: 获取Class对象的几种方法
 * <p>1.obj.getClass</>
 * <p>2.class.forName("")</p>
 * <p></p>
 * @auther:duwei
 * @date:2019/4/23
 */
public class GetClazzObj {

    public static boolean isEqual() {
        String str = "abc";
        Class clazz = str.getClass();

        Class clazz1 = null;
        try {
            clazz1 = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class clazz2 = String.class;

        Class clazz3 = Boolean.TYPE;

        if (clazz == clazz1 && clazz1 == clazz2) {
            return true;
        }
        return false;
    }


}
