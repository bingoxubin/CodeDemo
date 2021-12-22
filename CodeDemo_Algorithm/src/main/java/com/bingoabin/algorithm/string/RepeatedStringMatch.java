package com.bingoabin.algorithm.string;

import java.time.Clock;

/**
 * @Author: xubin34
 * @Date: 2021/12/22 9:53 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class RepeatedStringMatch {
    //Leetcode 686. 重复叠加字符串匹配
    //示例：输入：a = "abcd", b = "cdabcdab"  输出：3
    //    解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
    //分析：给定两个字符串a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
    //    注意：字符串 "abc"重复叠加 0 次是 ""，重复叠加 1 次是"abc"，重复叠加 2 次是"abcabc"。
    //思路：
    public static void main(String[] args) {
        String a = "abcd";
        String b = "cdabcdab";
        RepeatedStringMatch repeatedStringMatch = new RepeatedStringMatch();
        System.out.println(repeatedStringMatch.repeatedStringMatch1(a, b));
        System.out.println(repeatedStringMatch.repeatedStringMatch2(a, b));
        System.out.println(repeatedStringMatch.repeatedStringMatch3(a, b));
        System.out.println(repeatedStringMatch.repeatedStringMatch4(a, b));
    }

    //卡常
    public int repeatedStringMatch1(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < b.length() && ++ans > 0) sb.append(a);
        Clock clock = Clock.systemDefaultZone();
        long start = clock.millis();
        while (clock.millis() - start < 100) {
            if (sb.indexOf(b) != -1) return ans;
            sb.append(a);
            ans++;
        }
        return -1;
    }

    //上下界
    public int repeatedStringMatch2(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < b.length() && ++ans > 0) sb.append(a);
        sb.append(a);
        int idx = sb.indexOf(b);
        if (idx == -1) return -1;
        return idx + b.length() > a.length() * ans ? ans + 1 : ans;
    }

    //KMP
    public int repeatedStringMatch3(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < b.length() && ++ans > 0) sb.append(a);
        sb.append(a);
        int idx = strStr(sb.toString(), b);
        if (idx == -1) return -1;
        return idx + b.length() > a.length() * ans ? ans + 1 : ans;
    }

    int strStr(String ss, String pp) {
        if (pp.isEmpty()) return 0;

        // 分别读取原串和匹配串的长度
        int n = ss.length(), m = pp.length();
        // 原串和匹配串前面都加空格，使其下标从 1 开始
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
        int[] next = new int[m + 1];
        // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && p[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++
            if (p[i] == p[j + 1]) j++;
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && s[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (s[i] == p[j + 1]) j++;
            // 整一段匹配成功，直接返回下标
            if (j == m) return i - m;
        }
        return -1;
    }

    //字符串hash
    public int repeatedStringMatch4(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < b.length() && ++ans > 0) sb.append(a);
        sb.append(a);
        int idx = strHash(sb.toString(), b);
        if (idx == -1) return -1;
        return idx + b.length() > a.length() * ans ? ans + 1 : ans;
    }

    int strHash(String ss, String b) {
        int P = 131;
        int n = ss.length(), m = b.length();
        String str = ss + b;
        int len = str.length();
        int[] h = new int[len + 10], p = new int[len + 10];
        h[0] = 0;
        p[0] = 1;
        for (int i = 0; i < len; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + str.charAt(i);
        }
        int l = len - m + 1;
        int target = h[len] - h[l - 1] * p[len - l + 1]; // b 的哈希值
        for (int i = 1; i <= n; i++) {
            int j = i + m - 1;
            int cur = h[j] - h[i - 1] * p[j - i + 1]; // 子串哈希值
            if (cur == target) return i - 1;
        }
        return -1;
    }
}
