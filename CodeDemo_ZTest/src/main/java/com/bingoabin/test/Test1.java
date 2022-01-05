package com.bingoabin.test;

import java.util.*;

/**
 * @Author: xubin34
 * @Date: 2021/12/27 3:33 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Test1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String[] strings = list.toArray(new String[list.size()]);
        System.out.println(Arrays.toString(strings));

        Set<String> set = new HashSet<>(list);
        System.out.println(set);

        List<Integer> list1 = new ArrayList<>(Arrays.asList(3, 4, 5));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2));

        list1.addAll(0, list2);
        System.out.println(list1);
    }
}
