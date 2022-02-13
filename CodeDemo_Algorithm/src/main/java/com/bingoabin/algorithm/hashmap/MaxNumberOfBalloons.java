package com.bingoabin.algorithm.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xubin34
 * @Date: 2022/2/13 2:23 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MaxNumberOfBalloons {
    //Leetcode 1189. “气球” 的最大数量
    //示例：给你一个字符串text，你需要使用 text 中的字母来拼凑尽可能多的单词"balloon"（气球）。
    //    字符串text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词"balloon"。
    //分析：输入：text = "loonbalxballpoon"
    //     输出：2
    //思路：
    public static void main(String[] args) {
        String text = "loonbalxballpoon";
        MaxNumberOfBalloons maxNumberOfBalloons = new MaxNumberOfBalloons();
        System.out.println(maxNumberOfBalloons.maxNumberOfBalloons(text));
    }

    //hashmap
    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> map = new HashMap<>();
        //如果不加，存在问题，可能出现只有部分字母的情况，比如"lloo"
        map.put('b', 0);
        map.put('a', 0);
        map.put('l', 0);
        map.put('o', 0);
        map.put('n', 0);
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == 'b' || ch == 'a' || ch == 'l' || ch == 'o' || ch == 'n') {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
        }
        int min = Integer.MAX_VALUE;
        for (Character key : map.keySet()) {
            int value = map.get(key);
            if (key == 'l' || key == 'o') {
                value /= 2;
            }
            min = Math.min(min, value);
        }
        return min;
    }

    //数组代替hashmap
    public int maxNumberOfBalloons1(String text) {
        int[] cnt = new int[5];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == 'b') {
                cnt[0]++;
            } else if (ch == 'a') {
                cnt[1]++;
            } else if (ch == 'l') {
                cnt[2]++;
            } else if (ch == 'o') {
                cnt[3]++;
            } else if (ch == 'n') {
                cnt[4]++;
            }
        }
        cnt[2] /= 2;
        cnt[3] /= 2;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cnt.length; i++) {
            ans = Math.min(ans, cnt[i]);
        }
        return ans;
    }
}
