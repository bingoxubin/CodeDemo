package com.bingoabin.algorithm.string;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2022/4/7 10:05 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class RotateStringLeetcode {
    //Leetcode 796. 旋转字符串
    //示例：输入: s = "abcde", goal = "cdeab"
    //     输出: true
    //分析：给定两个字符串, s和goal。如果在若干次旋转操作之后，s能变成goal，那么返回true。
    //     s的 旋转操作 就是将s 最左边的字符移动到最右边。
    //     例如, 若s = 'abcde'，在旋转一次之后结果就是'bcdea'。
    //思路：
    public static void main(String[] args) {
        String s = "abcde";
        String goal = "cdeab";
        RotateStringLeetcode rotateStringLeetcode = new RotateStringLeetcode();
        System.out.println(rotateStringLeetcode.rotateString(s, goal));
        System.out.println(rotateStringLeetcode.rotateString1(s, goal));
        System.out.println(rotateStringLeetcode.rotateString2(s, goal));
        System.out.println(rotateStringLeetcode.rotateString3(s, goal));
    }

    //indexOf
    public boolean rotateString1(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals("") && B.equals("")) return true;
        return (A + A).indexOf(B, 1) > 0;
    }

    //字符串匹配
    public boolean rotateString2(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }

    //暴力
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.length() == 0) return true;
        search:
        for (int s = 0; s < A.length(); ++s) {
            for (int i = 0; i < A.length(); ++i) {
                if (A.charAt((s + i) % A.length()) != B.charAt(i)) {
                    continue search;
                }
            }
            return true;
        }
        return false;
    }

    //KMP
    public boolean rotateString3(String A, String B) {
        int N = A.length();
        if (N != B.length()) return false;
        if (N == 0) return true;

        //Compute shift table
        int[] shifts = new int[N + 1];
        Arrays.fill(shifts, 1);
        int left = -1;
        for (int right = 0; right < N; ++right) {
            while (left >= 0 && (B.charAt(left) != B.charAt(right)))
                left -= shifts[left];
            shifts[right + 1] = right - left++;
        }

        //Find match of B in A+A
        int matchLen = 0;
        for (char c : (A + A).toCharArray()) {
            while (matchLen >= 0 && B.charAt(matchLen) != c)
                matchLen -= shifts[matchLen];
            if (++matchLen == N) return true;
        }

        return false;
    }
}
