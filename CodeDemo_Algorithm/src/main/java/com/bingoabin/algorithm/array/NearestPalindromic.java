package com.bingoabin.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/3/2 9:51 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class NearestPalindromic {
    //Leetcode 564. 寻找最近的回文数
    //示例：示例 1:
    //     输入: n = "123"
    //     输出: "121"
    //分析：给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
    //     “最近的”定义为两个整数差的绝对值最小。
    //思路：
    public static void main(String[] args) {
        NearestPalindromic nearestPalindromic = new NearestPalindromic();
        System.out.println(nearestPalindromic.nearestPalindromic("1234"));
    }

    public String nearestPalindromic(String n) {
        long selfNumber = Long.parseLong(n), ans = -1;
        List<Long> candidates = getCandidates(n);
        for (long candidate : candidates) {
            if (candidate != selfNumber) {
                if (ans == -1 ||
                        Math.abs(candidate - selfNumber) < Math.abs(ans - selfNumber) ||
                        Math.abs(candidate - selfNumber) == Math.abs(ans - selfNumber) && candidate < ans) {
                    ans = candidate;
                }
            }
        }
        return Long.toString(ans);
    }

    public List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> candidates = new ArrayList<Long>() {{
            add((long) Math.pow(10, len - 1) - 1);
            add((long) Math.pow(10, len) + 1);
        }};
        long selfPrefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuffer sb = new StringBuffer();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            StringBuffer suffix = new StringBuffer(prefix).reverse();
            sb.append(suffix.substring(len & 1));
            String candidate = sb.toString();
            candidates.add(Long.parseLong(candidate));
        }
        return candidates;
    }
}
