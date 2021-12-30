package com.bingoabin.algorithm.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: xubin34
 * @Date: 2021/12/30 11:34 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class IsNStraightHand {
    //Leetcode 846. 一手顺子
    //示例：输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
    //     输出：true
    //     解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
    //分析：Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
    //     给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
    //思路：
    public static void main(String[] args) {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        IsNStraightHand isNStraightHand = new IsNStraightHand();
        System.out.println(isNStraightHand.isNStraightHand(hand, groupSize));
    }

    public boolean isNStraightHand(int[] hand, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
        for (int i : hand) {
            //map计数
            map.put(i, map.getOrDefault(i, 0) + 1);
            //queue中放到小顶堆
            q.add(i);
        }
        while (!q.isEmpty()) {
            int t = q.poll();
            //不断从小顶堆弹出，如果弹出的值在map中没有，继续
            if (map.get(t) == 0) continue;
            for (int i = 0; i < m; i++) {
                //从当前值开始，消耗掉自己一个，后续数m-1个，如果没得消耗，返回false
                int cnt = map.getOrDefault(t + i, 0);
                if (cnt == 0) return false;
                map.put(t + i, cnt - 1);
            }
        }
        return true;
    }
}
