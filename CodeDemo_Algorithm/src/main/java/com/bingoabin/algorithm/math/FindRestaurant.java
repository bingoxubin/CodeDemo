package com.bingoabin.algorithm.math;

import java.util.*;

/**
 * @Author: xubin34
 * @Date: 2022/3/14 9:51 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindRestaurant {
    //Leetcode 599. 两个列表的最小索引总和
    //示例：输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
    //     输出: ["Shogun"]
    //     解释: 他们唯一共同喜爱的餐厅是“Shogun”。
    //分析：假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
    //     你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
    //思路：
    public static void main(String[] args) {
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        FindRestaurant findRestaurant = new FindRestaurant();
        System.out.println(Arrays.toString(findRestaurant.findRestaurant(list1, list2)));
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        //先将list1中的放入到map中，然后进行遍历list2
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                sum = map.get(list2[i]) + i;
                //如果更新最小值，要清掉缓存的记录，如果相等最小值，需要再添加多的记录
                if (sum < min) {
                    res.clear();
                    min = sum;
                    res.add(list2[i]);
                } else if (sum == min) {
                    res.add(list2[i]);
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
