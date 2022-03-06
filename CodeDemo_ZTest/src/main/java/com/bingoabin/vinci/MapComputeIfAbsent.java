package com.bingoabin.vinci;

import java.util.HashMap;

/**
 * @Author: xubin34
 * @Date: 2022/3/4 11:12 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MapComputeIfAbsent {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        Integer num1 = map.computeIfAbsent(3, key -> new Integer(5));
        System.out.println(num1);
        System.out.println(map);
        Integer num2 = map.computeIfAbsent(4, key -> new Integer(4));
        System.out.println(num2);
        System.out.println(map);
    }
}
