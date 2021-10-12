package com.wangduwei.java_basic.collection.set;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>集合相关
 *
 * @auther : wangduwei
 * @since : 2019/10/22  11:47
 **/
public class SetTest {
    public static void main(String[] args) {
        treeSetTest();
    }


    /**
     *
     */
    private void hashSetTest() {
        Set<String> stringSet = new HashSet<>();
        stringSet.add("第一个元素");
        stringSet.add("第二个元素");
        stringSet.add("第三个元素");
    }

    /**
     * 会默认进行排序
     */
    private static void treeSetTest() {
        Set<Integer> stringSet = new TreeSet<>();
        stringSet.add(12);
        stringSet.add(1);
        stringSet.add(56);
        stringSet.add(5);
        stringSet.add(33);

        for (Integer i : stringSet) {
            System.out.print(i + "\n");
        }
    }

    private void linkedHashSetTest() {
        Set<String> stringSet = new LinkedHashSet<>();
        stringSet.add("第一个元素");
        stringSet.add("第二个元素");
        stringSet.add("第三个元素");
    }


}
