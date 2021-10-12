package com.wangduwei.java_basic.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <p> ArrayList 的remove坑：
 * <p>1.使用倒序遍历</p>
 * <p>2.使用迭代器的remove</p>
 * <p>3.改变index</p>
 *
 * @auther : wangduwei
 * @since : 2019/10/22  10:13
 **/
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayListTest.testRemove();
        testAdd();
    }

    public static void testAdd(){
        List<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.add(list.size()-1,5);
    }


    public static void testRemove() {
        ArrayList<Integer> list = init();

        useFor(list);//no

        changeIndex(list);//yes

        revers(list);//yes

        useIterator(list);
    }

    /**
     * 调用迭代器的remove方法-可用
     *
     * @param list
     */
    private static void useIterator(ArrayList<Integer> list) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() == 3) {
                it.remove();
            }
        }
    }

    /**
     * 倒序遍历-可用
     */
    private static void revers(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) == 3) {
                list.remove(i);
            }
        }
    }

    /**
     * 边删边改变角标-可用
     *
     * @param list
     */
    private static void changeIndex(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 3)
                list.remove(i--);
        }
    }


    /**
     * 使用for无法删除相邻元素
     *
     * @param list
     */
    private static void useFor(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 3)
                list.remove(i);
        }
    }

    private static ArrayList init() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        return list;
    }

}
