package com.wangduwei.java_basic.generic;

/**
 * 泛型方法
 */
public class GenericFunction {

    public static void main(String[] args) {
        try {
            Innerclass innerclass = GenericFunction.gf(Innerclass.class);
            innerclass.innerMethod();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static <T> T gf(Class<T> t) throws IllegalAccessException, InstantiationException {
        return t.newInstance();
    }

    public static class Innerclass {
        public void innerMethod() {
            System.out.println("1");
        }
    }

}
